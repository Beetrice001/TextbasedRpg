import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.005
 * 
 *         Responsibilities of class: The Main class serves as the entry point
 *         for the Text-Based RPG game. It prompts the user to select a player
 *         class and initializes the game GUI with the chosen player character.
 * 
 */

public class Main {
	/**
	 * The main method initializes the game by allowing the user to choose a class
	 * and starts the GUI with the selected player character. If an invalid class
	 * type is chosen or no class is selected, the program displays an error message
	 * and exits.
	 * 
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		// Prompt user to select a class
		String classType = JOptionPane.showInputDialog(null, "Choose your class (Mage, Warrior, Rogue, Archer):",
				"Class Selection", JOptionPane.QUESTION_MESSAGE);

		// Check if the user input is valid
		if (classType == null || classType.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You must choose a class to play!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		try {
			// Create player based on selected class and initialize GUI
			Player player = Player.createPlayer(classType);
			SwingUtilities.invokeLater(() -> new GUI(player));
		} catch (IllegalArgumentException e) {
			// Handle invalid class type
			JOptionPane.showMessageDialog(null, "Invalid class type selected!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
}