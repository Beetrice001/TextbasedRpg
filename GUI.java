import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.009
 * 
 *         Responsibilities of class: Provides a graphical user interface for
 *         the Text-Based RPG game, allowing the player to interact with the
 *         game, view stats, and handle combat scenarios.
 * 
 */

public class GUI {
	/** The main JFrame for the game's GUI. */
	private JFrame frame;

	/** The text area for displaying game scenarios and messages. */
	private JTextArea textArea;

	/** Buttons for continuing the game, attacking, and defending. */
	private JButton continueButton, attackButton, defendButton;

	/** Labels to display player and enemy stats. */
	private JLabel healthLabel, staminaLabel, enemyHealthLabel;

	/** The player character in the game. */
	private Player player;

	/** The current enemy being fought, if any. */
	private Enemy enemy;

	/** The object managing the game's scenarios. */
	private GameScenario gameScenario;

	/**
	 * Purpose: Constructs a GUI for the Text-Based RPG game.
	 * 
	 * @param player the player object representing the current player in the game
	 */
	public GUI(Player player) {
		this.player = player;
		this.gameScenario = new GameScenario();

		Font customFont = new Font("Times New Roman", Font.PLAIN, 16);

		// Initialize JFrame and components
		frame = new JFrame("Text-Based RPG");
		textArea = new JTextArea(10, 40);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(customFont);
		textArea.setMargin(new Insets(10, 10, 10, 10));

		continueButton = new JButton("Continue");
		attackButton = new JButton("Attack");
		defendButton = new JButton("Defend");
		healthLabel = new JLabel();
		staminaLabel = new JLabel();
		enemyHealthLabel = new JLabel();

		// Apply font to all components
		continueButton.setFont(customFont);
		attackButton.setFont(customFont);
		defendButton.setFont(customFont);
		healthLabel.setFont(customFont);
		staminaLabel.setFont(customFont);
		enemyHealthLabel.setFont(customFont);

		// Setup JFrame layout and components
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		textArea.setEditable(false);
		frame.add(new JScrollPane(textArea));

		JPanel playerStatsPanel = new JPanel();
		playerStatsPanel.setLayout(new GridLayout(1, 2));
		playerStatsPanel.add(healthLabel);
		playerStatsPanel.add(staminaLabel);
		frame.add(playerStatsPanel);

		JPanel enemyStatsPanel = new JPanel();
		enemyStatsPanel.setLayout(new GridLayout(1, 1));
		enemyStatsPanel.add(enemyHealthLabel);
		frame.add(enemyStatsPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(attackButton);
		buttonPanel.add(defendButton);
		buttonPanel.add(continueButton);
		frame.add(buttonPanel);

		// Add action listeners for buttons
		continueButton.addActionListener(this::handleContinue);
		attackButton.addActionListener(this::handleAttack);
		defendButton.addActionListener(this::handleDefend);

		frame.pack();
		frame.setVisible(true);

		toggleCombatButtons(false);
		updateStats();
		gameScenario.openingScenario(this);
	}

	/**
	 * Purpose: Displays a scenario or message in the text area.
	 * 
	 * @param text the text to display
	 */
	public void displayScenario(String text) {
		textArea.append("\n" + text + "\n");

		if (text.contains("Dragon Attack!")) {
			initiateCombat();
		}
	}

	/**
	 * Purpose: Sets the current enemy in combat and updates the enemy health label.
	 * 
	 * @param enemy the enemy object to set
	 */
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
		if (enemy != null) {
			enemyHealthLabel.setText("Enemy Health: " + enemy.getHealth());
			toggleCombatButtons(true);
		} else {
			enemyHealthLabel.setText("");
			toggleCombatButtons(false);
		}
	}

	/**
	 * Purpose: Updates the player and enemy stats displayed in the GUI.
	 */
	public void updateStats() {
		healthLabel.setText("Health: " + player.getHealth());
		staminaLabel.setText("Stamina: " + player.getStamina());
		if (enemy != null) {
			enemyHealthLabel.setText("Enemy Health: " + enemy.getHealth());
		}
	}

	/**
	 * Purpose: Ends the game and displays a success or failure message.
	 * 
	 * @param success whether the player successfully completed the game
	 */
	public void endGame(boolean success) {
		String message = success ? "Congratulations! You made it home safely." : "You have fallen on your journey.";
		JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	/**
	 * Purpose: Handles the Continue button press. Advances the game or displays
	 * combat options.
	 * 
	 * @param e the action event triggered by the button press
	 */
	private void handleContinue(ActionEvent e) {
		if (enemy != null && enemy.isAlive()) {
			displayScenario("You are in combat! Choose an action.");
		} else {
			gameScenario.nextScenario(player, this);
		}
	}

	/**
	 * Purpose: Handles the Attack button press. Initiates an attack combat round.
	 * 
	 * @param e the action event triggered by the button press
	 */
	private void handleAttack(ActionEvent e) {
		if (enemy != null) {
			CombatSystem combat = new CombatSystem(player, enemy, this);
			combat.startCombatRound("attack");
		}
	}

	/**
	 * Purpose: Handles the Defend button press. Initiates a defend combat round.
	 * 
	 * @param e the action event triggered by the button press
	 */
	private void handleDefend(ActionEvent e) {
		if (enemy != null) {
			CombatSystem combat = new CombatSystem(player, enemy, this);
			combat.startCombatRound("defend");
		} else {
			displayScenario("No enemy to defend against!");
		}
	}

	/**
	 * Purpose: Toggles the combat buttons (Attack, Defend) based on the combat
	 * state.
	 * 
	 * @param inCombat true if the player is in combat, false otherwise
	 */
	private void toggleCombatButtons(boolean inCombat) {
		attackButton.setEnabled(inCombat);
		defendButton.setEnabled(inCombat);
		continueButton.setEnabled(!inCombat);
	}

	/**
	 * Purpose: Starts a combat round and checks the combat state after the round.
	 */
	public void startCombatRound() {
		if (enemy != null && enemy.isAlive()) {
			CombatSystem combat = new CombatSystem(player, enemy, this);
			combat.startCombatRound("attack");
			updateStats();

			if (!enemy.isAlive()) {
				displayScenario("You have defeated the enemy!");
				toggleCombatButtons(false);
			} else if (player.getHealth() <= 0) {
				endGame(false);
			}
		} else {
			displayScenario("No enemy to fight!");
		}
	}

	/**
	 * Purpose: Initiates combat with a predefined enemy (Dragon).
	 */
	private void initiateCombat() {
		enemy = new Enemy("Dragon", 100, 20);
		setEnemy(enemy);
		displayScenario("A " + enemy.getName() + " appears! Combat begins!");
	}
}