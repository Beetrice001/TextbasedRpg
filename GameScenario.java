import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.008
 * 
 *         Responsibilities of class: Manages the game's scenarios by loading
 *         them from a file and providing methods to retrieve and display
 *         scenarios. Handles progression through the game.
 * 
 */

public class GameScenario {

	/** List of all scenarios loaded from the file. */
	private List<String> scenarios;

	/**
	 * Purpose: Constructs a GameScenario object and loads scenarios from a text
	 * file.
	 */
	public GameScenario() {
		scenarios = loadScenarios("scenarios.txt");
	}

	/**
	 * Purpose: Reads scenarios from a specified file and stores them in a list.
	 * 
	 * @param fileName the name of the file containing the scenarios
	 * @return a list of strings, each representing a scenario
	 */
	private List<String> loadScenarios(String fileName) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading the scenario file: " + e.getMessage());
		}
		return lines;
	}

	/**
	 * Purpose: Retrieves a scenario from the list by its index.
	 * 
	 * @param index the index of the scenario to retrieve
	 * @return the scenario text if the index is valid, otherwise a 
	 * default error message
	 */
	public String getScenario(int index) {
		if (index >= 0 && index < scenarios.size()) {
			return scenarios.get(index);
		} else {
			return "Scenario not found!";
		}
	}

	/**
	 * Purpose: Displays the opening scenario using the GUI.
	 * 
	 * @param gui the GUI object used to display the scenario
	 */
	public void openingScenario(GUI gui) {
		gui.displayScenario(getScenario(0));
	}

	/**
	 * Purpose: Displays the next scenario and handles in-game events 
	 * such as health restoration or game-ending conditions. 
	 * Updates the player's progress and health status.
	 * 
	 * @param player the player object, which holds the current state of the player
	 * @param gui    the GUI object used to display scenarios and update game state
	 */
	public void nextScenario(Player player, GUI gui) {
		if (player.getHealth() <= 0) {
			gui.endGame(false);
			return;
		}

		int currentScenario = player.getCurrentScenario();
		String scenarioText = getScenario(currentScenario);

		if (scenarioText.contains("Health Potion")) {
			int healthRestored = 20;
			player.setHealth(player.getHealth() + healthRestored);
			gui.displayScenario("You find a Health Potion! It restores 20 health.");
			gui.updateStats();
		}

		if (currentScenario < scenarios.size() - 1) {
			player.setCurrentScenario(currentScenario + 1);
			gui.displayScenario(getScenario(player.getCurrentScenario()));
		} else {
			gui.endGame(true);
		}
	}
}