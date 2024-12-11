/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.006
 * 
 *         Responsibilities of class: Represents an enemy character in the game
 *         with a name, health, and attack power. Provides methods for managing
 *         the enemy's health, attacks, and status.
 * 
 */

public class Enemy {

	// The name of the enemy.
	private String name;

	// The health of the enemy. 
	private int health;

	// The attack power of the enemy.
	private int attackPower;

	/**
	 * Purpose: Constructs an Enemy object with a specified name, health, and attack
	 * power.
	 * 
	 * @param name        the name of the enemy
	 * @param health      the initial health of the enemy
	 * @param attackPower the attack power of the enemy
	 */
	public Enemy(String name, int health, int attackPower) {
		this.name = name;
		this.health = health;
		this.attackPower = attackPower;
	}

	/**
	 * Purpose: Retrieves the name of the enemy.
	 * 
	 * @return the name of the enemy
	 */
	public String getName() {
		return name;
	}

	/**
	 * Purpose: Retrieves the current health of the enemy.
	 * 
	 * @return the health of the enemy
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Purpose: Reduces the enemy's health by a specified damage amount. Ensures
	 * that the health does not drop below zero.
	 * 
	 * @param damage the amount of damage to inflict on the enemy
	 */
	public void takeDamage(int damage) {
		health -= damage;
		if (health < 0)
			health = 0;
	}

	/**
	 * Purpose: Checks if the enemy is still alive (health > 0).
	 * 
	 * @return true if the enemy is alive, false otherwise
	 */
	public boolean isAlive() {
		return health > 0;
	}

	/**
	 * Purpose: Returns the attack power of the enemy.
	 * 
	 * @return the attack power of the enemy
	 */
	public int attack() {
		return attackPower;
	}

	/**
	 * Purpose: Sets the health of the enemy to a specific value. If the provided
	 * value is less than 0, the health is set to 0.
	 * 
	 * @param health the new health value for the enemy
	 */
	public void setHealth(int health) {
		if (health < 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}
}