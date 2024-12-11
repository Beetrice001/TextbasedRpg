/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.006
 * 
 *         Responsibilities of class: Defines base traits of characters in the
 *         game. This is an abstract class that serves as a blueprint for player
 *         characters and enemies.
 * 
 */

public abstract class Character {

	//The name of the character. 
	protected String name;

	//The health points of the character. 
	protected int health;

	// The stamina points of the character, used for physical actions.
	protected int stamina;

	// The mana points of the character, used for magical actions. 
	protected int mana;

	/**
	 * Purpose: Constructs a Character with specified attributes.
	 * 
	 * @param name    the name of the character
	 * @param health  the health points of the character
	 * @param stamina the stamina points of the character
	 * @param mana    the mana points of the character
	 */
	public Character(String name, int health, int stamina, int mana) {
		this.name = name;
		this.health = health;
		this.stamina = stamina;
		this.mana = mana;
	}

	/**
	 * Purpose: Gets the name of the character.
	 * 
	 * @return the name of the character
	 */
	public String getName() {
		return name;
	}

	/**
	 * Purpose: Gets the health points of the character.
	 * 
	 * @return the health points of the character
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Purpose: Sets the health points of the character. If the specified health is
	 * negative, it will be set to 0.
	 * 
	 * @param health the new health value for the character
	 */
	public void setHealth(int health) {
		this.health = Math.max(health, 0);
	}

	/**
	 * Purpose: Gets the stamina points of the character.
	 * 
	 * @return the stamina points of the character
	 */
	public int getStamina() {
		return stamina;
	}

	/**
	 * Purpose: Sets the stamina points of the character.
	 * 
	 * @param stamina the new stamina value for the character
	 */
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	/**
	 * Purpose: Gets the mana points of the character.
	 * 
	 * @return the mana points of the character
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * Purpose: Sets the mana points of the character.
	 * 
	 * @param mana the new mana value for the character
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}

	/**
	 * Purpose: Executes an attack action for the character. The specific
	 * implementation is defined in subclasses.
	 * 
	 * @return the damage dealt by the character's attack
	 */
	public abstract int attack();
}
