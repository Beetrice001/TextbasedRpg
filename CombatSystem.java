/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.008
 * 
 * 
 *         Responsibilities of class: Handles turn-based combat mechanics
 *         between the player and the enemy. It also interacts with the GUI to
 *         display the combat flow and updates.
 * 
 */

public class CombatSystem {

	/** The player participating in the combat. */
	private Player player;

	/** The enemy participating in the combat. */
	private Enemy enemy;

	/** The GUI for displaying combat scenarios and updates. */
	private GUI gui;

	/**
	 * Purpose: Constructs a CombatSystem to manage the battle between the player
	 * and the enemy.
	 * 
	 * @param player the player character in the combat
	 * @param enemy  the enemy character in the combat
	 * @param gui    the GUI for displaying combat updates
	 */
	public CombatSystem(Player player, Enemy enemy, GUI gui) {
		this.player = player;
		this.enemy = enemy;
		this.gui = gui;
	}

	/**
	 * Purpose: Starts and processes a round of combat based on the player's action.
	 * The player's action determines whether they attack or defend, and the enemy
	 * will counterattack if still alive. Updates the GUI to reflect changes in
	 * health and combat outcomes.
	 * 
	 * @param action the player's action ("attack" or "defend")
	 */
	public void startCombatRound(String action) {
		if (action.equals("attack")) {

			// Player attacks the enemy
			int playerDamage = player.attack();
			enemy.setHealth(enemy.getHealth() - playerDamage);
			gui.displayScenario(
					player.getName() + " attacks " + enemy.getName() + " and deals " + playerDamage + " damage.");

			// Check if the enemy is defeated
			if (enemy.getHealth() <= 0) {
				gui.displayScenario(enemy.getName() + " defeated!");
				gui.setEnemy(null);
				gui.updateStats();
				gui.displayScenario("Victory! Press 'Continue' to proceed.");
				return;
			}
		} else if (action.equals("defend")) {

			// Player defends to reduce incoming damage
			int reducedDamage = 10;
			player.setHealth(player.getHealth() - reducedDamage);
			gui.displayScenario(player.getName() + " defends! Only " + reducedDamage + " damage taken.");
		}

		// Enemy's turn to attack if still alive
		if (enemy.getHealth() > 0) {
			int enemyDamage = enemy.attack();
			if (action.equals("defend")) {
				gui.displayScenario(enemy.getName() + " attacks but you are defending!");
			} else {
				player.setHealth(player.getHealth() - enemyDamage);
				gui.displayScenario(
						enemy.getName() + " attacks " + player.getName() + " and deals " + enemyDamage + " damage.");
			}
		}

		// Check if the player is defeated
		if (player.getHealth() <= 0) {
			gui.endGame(false);
		}

		// Update the GUI to reflect health and combat updates
		gui.updateStats();
	}
}
