package mainclasses;

import java.util.ArrayList;
import java.util.List;

public class Cart <T extends Weapon> {
	private List<T> items;
	
	public Cart() {
		this.items = new ArrayList<>();
	}
	
	/**
	 * Adds an item to the cart
	 * @param item
	 */
	public void addItem(T item) {
		items.add(item);
	}
	
	/**
	 * Removes an item from the cart
	 * @param item
	 * @return True if the item exists and has been removed, or false if it was not in the cart
	 */
	public boolean removeItem(T item) {
		return items.remove(item);
	}
	
	/**
	 * Clear the shopping cart completely
	 */
	public void clearCart() {
		items.clear();
	}
	
	/**
	 *  
	 * @return The list of elements from the cart
	 */
	public List<T> getItems() {
		return this.items;
	}
	
	/**
	 * It maps each generic item ‘T’ to its price and automatically adds them all up
	 * @return The total price of the items from the cart
	 */
	public double calculateSubtotal() {
		return items.stream()
				//Extract each item's price
				.mapToDouble(Weapon::getPrice)
				//Sum all the prices
				.sum();
	}
}