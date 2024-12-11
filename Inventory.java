import java.util.ArrayList;

/**
 * Lead Author(s):
 * 
 * @author Beatrice
 * 
 * 
 * 
 *         Version/date: ver.005
 * 
 *         Responsibilities of class: Manages the player's inventory, including
 *         adding items and checking if specific items are present. Although this
 *         class isn't in use.
 * 
 */

public class Inventory {
	/**
	 * A list to store all the items in the inventory.
	 */
	private ArrayList<Item> items;

	/**
	 * Purpose: Initializes an empty inventory.
	 */
	public Inventory() {
		items = new ArrayList<>();
	}

	/**
	 * Purpose: Adds an item to the inventory.
	 * 
	 * @param item the item to add to the inventory
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * Purpose: Checks if the inventory contains an item with the specified name.
	 * 
	 * @param itemName the name of the item to check for
	 * @return true if the item is present in the inventory, false otherwise
	 */
	public boolean hasItem(String itemName) {
		return items.stream().anyMatch(item -> item.getName().equals(itemName));
	}
}
