package management;

import mainclasses.*;
import interfaces.*;

/**
 * Process the customer's purchase
 * @author Raquel Nkwar
 * @version 1.0
 */
public class SaleManagement {
	
	/**
	 * Checks that the customer meets all the requirements to make a purchase 
	 * @param client
	 * @param cart
	 * @param moneyIntroduced
	 * @return True if the pruchase was succesful, false if any of the requirements were not met
	 */
	public boolean processPurchase(Client client, Cart<Weapon> cart, double moneyIntroduced) {
		//If the cart is empty, the customer cannot make a purchase
		if (cart.getItems().isEmpty()) {
			System.out.println("ERROR: You cannot complete a purchase with an empty cart");
			return false;
		}
		
		//Check whether the client enforces the experience restrictions
		if (client instanceof AvailableWeapon) {
			AvailableWeapon clientWithFilters = (AvailableWeapon) client;
			
			//Go through the cart's weapons to see if the user can use them all
			for (Weapon weapon : cart.getItems()) {
				if (!clientWithFilters.appropiateExperience(weapon)) {
					System.out.println("SALE DENIED: The client " + client.getName() + " does not have the appropiate experience level for the weapon " + weapon.getName());
					//Stop the sale for security
					return false;
				}
			}
		}
		
		//Calculate the final price
		double subtotal = cart.calculateSubtotal();
		double discount = client.applyDiscount(subtotal);
		double moneyToPay = subtotal - discount;
		
		//Check if the money introduced is sufficient to pay for ther purchase
		if (moneyIntroduced < moneyToPay) {
			System.out.println("SALE DENIED: Not enough money");
			System.out.println("Total amount due: " + moneyToPay);
			
			return false;
		}
		
		//If everything is okay, calculate the change and update the numbers of orders made by the user 
		double change = moneyIntroduced - moneyToPay;
		client.increaseOrders();
		
		System.out.println("Purchase successfully processed!!");
		System.out.println("Purchase's total: " + moneyToPay);
		System.out.println("Amount of money given: " + moneyIntroduced);
		
		//If the client has overpaid, we will give them the change
		if (change > 0) {
			System.out.println("Your change: " + change);
		}
		
		//Empty the cart
		cart.clearCart();
		return true;
	}
}