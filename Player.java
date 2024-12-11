/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.006
 * 
 *         Responsibilities of class: The Player class is an abstract superclass
 *         representing a player character in the game. It defines common
 *         attributes and methods for different player classes such as Warrior,
 *         Mage, Rogue, and Archer.
 * 
 */

public abstract class Player extends Character {
	protected int strength;
	protected int agility;
	protected int intelligence;
	protected int luck;
	protected Inventory inventory;
	private int currentScenario;

	/**
	 * Constructor for the Player class.
	 * 
	 * @param name         the name of the player
	 * @param health       the health of the player
	 * @param stamina      the stamina of the player
	 * @param mana         the mana of the player
	 * @param strength     the strength attribute of the player
	 * @param agility      the agility attribute of the player
	 * @param intelligence the intelligence attribute of the player
	 * @param luck         the luck attribute of the player
	 */
	public Player(String name, int health, int stamina, int mana, int strength, int agility, int intelligence,
			int luck) {
		super(name, health, stamina, mana);
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.luck = luck;
		this.inventory = new Inventory();
		this.currentScenario = 0; // Initialize to the first scenario
	}

	/**
	 * Retrieves the player's inventory.
	 * 
	 * @return the inventory of the player
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Retrieves the current scenario index the player is in.
	 * 
	 * @return the current scenario index
	 */
	public int getCurrentScenario() {
		return currentScenario;
	}

	/**
	 * Sets the current scenario index for the player.
	 * 
	 * @param currentScenario the new scenario index
	 */
	public void setCurrentScenario(int currentScenario) {
		this.currentScenario = currentScenario;
	}

	/**
	 * Abstract method to calculate the player's attack damage.
	 * 
	 * @return the calculated attack damage
	 */
	public abstract int attack();

	/**
	 * Factory method to create a player of a specific class type.
	 * 
	 * @param classType the class type of the player ("mage", "warrior", "rogue",
	 *                  "archer")
	 * @return a new Player instance of the specified class type
	 * @throws IllegalArgumentException if the class type is invalid
	 */
	public static Player createPlayer(String classType) {
		switch (classType.toLowerCase()) {
		case "mage":
			return new Mage("Hero");
		case "warrior":
			return new Warrior("Hero");
		case "rogue":
			return new Rogue("Hero");
		case "archer":
			return new Archer("Hero");
		default:
			throw new IllegalArgumentException("Invalid class type.");
		}
	}
}

/**
 * The Warrior class represents the Warrior player type. It inherits from the
 * Player class and specializes the attack behavior.
 */
class Warrior extends Player {
	public Warrior(String name) {
		super(name, 100, 50, 30, 15, 10, 5, 5);
	}

	@Override
	public int attack() {
		return strength * 2;
	}
}

/**
 * The Mage class represents the Mage player type. It inherits from the Player
 * class and specializes the attack behavior.
 */
class Mage extends Player {
	public Mage(String name) {
		super(name, 80, 40, 60, 5, 10, 20, 10);
	}

	@Override
	public int attack() {
		return intelligence * 2;
	}
}

/**
 * The Rogue class represents the Rogue player type. It inherits from the Player
 * class and specializes the attack behavior.
 */
class Rogue extends Player {
	public Rogue(String name) {
		super(name, 90, 60, 40, 10, 15, 10, 10);
	}

	@Override
	public int attack() {
		return agility * 2;
	}
}

/**
 * The Archer class represents the Archer player type. It inherits from the
 * Player class and specializes the attack behavior.
 */
class Archer extends Player {
	public Archer(String name) {
		super(name, 85, 55, 35, 10, 20, 5, 15);
	}

	@Override
	public int attack() {
		return agility * 2;
	}
}