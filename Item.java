/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.005
 * 
 *         Responsibilities of class:The Item class represents an item that can
 *         be used or stored in a player's inventory. Each item has a name and
 *         an effect value, which determines its impact on the player or game.
 *         
 */

public class Item {
	private String name;
	private int effect;

	/**
	 * Constructs an Item with the specified name and effect value.
	 * 
	 * @param name   the name of the item
	 * @param effect the effect value of the item (e.g., the amount of health
	 *               restored)
	 */
	public Item(String name, int effect) {
		this.name = name;
		this.effect = effect;
	}

	/**
	 * Retrieves the name of the item.
	 * 
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the effect value of the item.
	 * 
	 * @return the effect value of the item
	 */
	public int getEffect() {
		return effect;
	}
}