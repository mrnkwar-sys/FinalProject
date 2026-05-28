package management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.*;
import mainclasses.*;

public class DataSaleManagement {
	private InventoryManagement inventoryManager;
	private List<Client> activeClients;
	private SaleManagement saleManager;
	
	/**
	 * Load all text files
	 */
	public void initShop() {
		ArrayList<Weapon> loadedWeapons = DataPersistence.loadInventory();
		activeClients = DataPersistence.loadClients();
		
		inventoryManager = new InventoryManagement(loadedWeapons);
		saleManager = new SaleManagement();
	}
	
	/**
	 * Save all the changes and data in the files again
	 */
	public void closeShop() {
		//Retrieve the inventory list and use an ArrayList to pass it to the BufferedWriter
		ArrayList<Weapon> weaponsToSave = new ArrayList<>(inventoryManager.getAllWeapons());
		
		//Save all the data
		DataPersistence.saveInventory(weaponsToSave);
		DataPersistence.saveClients(new ArrayList<>(activeClients));
	}
	
	/**
	 * Shows the available options for the admin
	 * @param sc
	 */
	public void adminMenu(Scanner sc) {
		int option = 0;
		
		do {
			try {
				System.out.println("=========ADMINISTRATION PANEL=========");
				System.out.println("1. See inventory");
				System.out.println("2. See details of an specific weapon");
				System.out.println("3. Apply discount by weapon's type");
				System.out.println("4. Add new weapon to the catalogue");
				System.out.println("5. See list of clients registered");
				System.out.println("6. Update the name and price of a weapon");
				System.out.println("7. Remove a weapon from the catalogue");
				System.out.println("8. Back to principal Menu");
				System.out.println("Select an option: ");
				
				option = sc.nextInt();
				sc.nextLine();
				
				switch (option) {
				case 1 -> {
					System.out.println("---Stock in warehouse---");
					inventoryManager.getAllWeapons().forEach(weapon -> System.out.println("♥ " + weapon.toString() + "\n"));
				}
				case 2 -> {
					System.out.println("Introduce the weapon's id: ");
					int id = sc.nextInt();
					sc.nextLine();
					
					Weapon weaponFound = inventoryManager.getWeapon(id);
					
					if (weaponFound != null) {
						System.out.println("---BASIC DETAILS---");
						System.out.println(weaponFound.toString());
						System.out.println("\n---SPECIFIC DETAILS---");
						System.out.println(weaponFound.toStringAdditionalData()); 
					} else {
						System.out.println("ERROR: No weapon found with that ID");
					}
				}
				case 3 -> {
					System.out.println("Type of weapon: ");
					String type = sc.nextLine();
					System.out.println("Discount percentage: ");
					double percentage = sc.nextDouble();
					sc.nextLine();
					
					inventoryManager.applyDiscountByType(percentage, type);
					System.out.println("Discount applied successfully");
				}
				case 4 -> {
					int newId = inventoryManager.getAllWeapons().size() + 1;
					Weapon newWeapon = WeaponFactory.registerNewWeapon(newId, sc);
					if (newWeapon != null) {
						inventoryManager.addWeapon(newWeapon);
						System.out.println("The weapon has been added successfully");
					} else {
						System.out.println("Skipping adding to inventory due to invalid data");
					}
				}
				case 5 -> {
					System.out.println("---Clients registered in the data base---");
					activeClients.forEach(client -> System.out.println(client.toString()));
				}
				case 6 -> {
					System.out.println("Introduce the ID of the weapon you want to update: ");
					int id = sc.nextInt();
					sc.nextLine();
					
					Weapon updateWeapon = inventoryManager.getWeapon(id);
					
					if (updateWeapon != null) {
						boolean correctData = false;
						
						do {
							try {
								System.out.println("Introduce the new weapon's name: ");
								String name = sc.nextLine();
								
								System.out.println("Introduce the new weapon's price");
								double price = sc.nextDouble();
								sc.nextLine();
								
								if(name.trim().isEmpty()) throw new EmptyStringException();
								
								updateWeapon.setName(name);
								updateWeapon.setPrice(price);
								correctData = true;
								System.out.println("The weapon has been updated");
							} catch (EmptyStringException | NegativeNumberException e) {
								System.out.println(e.toString());
								System.out.println("Try it again");
							}
						} while (!correctData);
					}
				}
				case 7 -> {
					System.out.println("Introduce the ID of the weapon you want to delete: ");
					int id = sc.nextInt();
					sc.nextLine();
					
					if (inventoryManager.getWeapon(id) != null) {
						inventoryManager.removeWeapon(id);
						System.out.println("The weapon has been removed from the database");
					} else {
						System.out.println("ERROR: A weapon with that id is not in the database");
					}
				}
				case 8 -> {
					System.out.println("Logging out of the admin pannel...");
				}
				default -> {
					System.out.println("Invalid option; introduce a valid option (1-5)");
				}
				}
			} catch (Exception e) {
				System.out.println("Administration error: " + e.getMessage());
				sc.nextLine();
			}
		} while (option != 8);
	}
	
	/**
	 * Shows the available options for the customer
	 * @param sc
	 * @param currentClient
	 */
	public void clientMenu(Scanner sc, Client currentClient) {
		int option = 0;
		Cart<Weapon> currentCart = new Cart<>();
		
		System.out.println("Welcome to our Magic Girls’ Weapons Shop");
		
		do {
			try {
				System.out.println("=========SHOPPING MENU=========");
				System.out.println("1. Explore weapon's catalogue");
				System.out.println("2. Add a weapon to the cart by its ID");
				System.out.println("3. Check the cart and its subtotal");
				System.out.println("4. Go to the till and pay");
				System.out.println("5. Leave the shop (Empty the car and log out)");
				System.out.println("What do you want to do, magical girl?");
				
				option = sc.nextInt();
				sc.nextLine();
				
				switch (option) {
				case 1 -> {
					inventoryManager.getAllWeapons().forEach(weapon -> System.out.println("♥ " + weapon.toString() + "\n"));
				}
				case 2 -> {
					System.out.println("Introduce the weapon's ID: ");
					int weaponId = sc.nextInt();
					sc.nextLine();
					
					Weapon weap = inventoryManager.getWeapon(weaponId);
					if (weap != null) {
						currentCart.addItem(weap);
						System.out.println("The weapon has been added to your cart");
					}
				}
				case 3 -> {
					System.out.println("Your cart's content");
					if (currentCart.getItems().isEmpty()) {
						System.out.println("Your cart is empty :(");
					} else {
						currentCart.getItems().forEach(item -> System.out.println("♡ " + item.getName() + item.getPrice()));
						System.out.println("Subtotal: " + currentCart.calculateSubtotal());
					}
				}
				case 4 -> {
					double subtotal = currentCart.calculateSubtotal();
					double discount = currentClient.applyDiscount(subtotal);
					double total = subtotal - discount;
					
					System.out.println("Total amount to be paid (after discounts): " + total);
					System.out.println("Introduce the amount of money you wish to pay: ");
					double payment = sc.nextDouble();
					sc.nextLine();
					
					saleManager.processPurchase(currentClient, currentCart, payment);
				}
				case 5 -> {
					System.out.println("Thank you for visiting us");
				}
				default -> {
					System.out.println("Invalid option; introduce a valid option (1-5)");
				}
				}
			} catch (Exception e) {
				System.out.println("Error during the purchase: " + e.getMessage());
				sc.nextLine();
			}
		} while (option != 5);
	}
	
	/**
	 * Lets the user decide if they want to use an existent "account" or create a new one
	 * If the database is empty, a new create will have to be created
	 * @param sc
	 * @return An active client if the database is not empty, or a new one is that option has been chosen or the database is empty
	 */
	public Client selectCreateClient(Scanner sc) {
		System.out.println("=========ACCESS TO SHOP'S SYSTEM=========");
		System.out.println("1. Select an existent client");
		System.out.println("2. Register a new client (Magical Girl or Union)");
		System.out.println("Select an option: ");
		
		int option = sc.nextInt();
		sc.nextLine();
		
		if (option == 1) {
			if (activeClients.isEmpty()) {
				System.out.println("There are no clients in the database. You must register a new one");
				return registerNewClient(sc);
			}
			
			System.out.println("---Registered clients---");
			for (int i = 0; i < activeClients.size(); i++) {
				Client c = activeClients.get(i);
				String type = (c instanceof mainclasses.Independent) ? "Magical Girl" : "Union";
				System.out.println((i+1) + ". " + type + ". " + c.toString());
			}
			
			System.out.println("Select the client number: ");
			int index = sc.nextInt() - 1;
			sc.nextLine();
			
			if (index >= 0 && index < activeClients.size()) {
				System.out.println("Session started as " + activeClients.get(index).getName());
				return activeClients.get(index);
			} else {
				System.out.println("Invalid session. The system will log in with the first client by default");
				return activeClients.get(0);
			}
		} else {
			return registerNewClient(sc);
		}
	}
	
	/**
	 * Registers and adds a new client to the database
	 * @param sc
	 * @return A new independent/union client
	 */
	public Client registerNewClient(Scanner sc) {
		System.out.println("---New Customer Registration---");
		System.out.println("Introduce the name: ");
		String name = sc.nextLine();
		
		//Generate an autoincremental ID based on the list current size
		int newId = activeClients.size() + 1;
		int initialOrders = 0;
		
		System.out.println("What kind of client are you?");
		System.out.println("1. Independent (Magical Girl)");
		System.out.println("2. Union");
		int clientType = sc.nextInt();
		sc.nextLine();
		
		Client newClient = null;
		
		if (clientType == 1) {
			System.out.println("Introduce your age: ");
			int age = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Select your experience level: ");
			System.out.println("1. NOVICE");
			System.out.println("2. MEDIUM");
			System.out.println("3. EXPERT");
			int experienceLevel = sc.nextInt();
			sc.nextLine();
			
			mainclasses.LevelExperience exp = mainclasses.LevelExperience.NOVICE;
			
			if (experienceLevel == 1) {
				exp = mainclasses.LevelExperience.NOVICE;
			} else if (experienceLevel == 2) {
				exp = mainclasses.LevelExperience.MEDIUM;
			} else if (experienceLevel == 3) {
				exp = mainclasses.LevelExperience.EXPERT;
			}
			
			try {
				Client newInd = new mainclasses.Independent(newId, name, initialOrders, age, exp);
				
				activeClients.add(newInd);
				System.out.println("Magical girl added successfully");
				return newInd;
			} catch (NegativeNumberException | EmptyStringException | UnderAgeException e) {
				System.out.println("Registration failed: " + e.toString());
			}
			
		} else {
			System.out.println("Introduce the number of members of the Union: ");
			int members = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Introduce the union's type: ");
	        String unionType = sc.nextLine();
	        
	        try {
	        	Client newUni = new mainclasses.Union(newId, name, initialOrders, unionType, members);
	        	
	        	activeClients.add(newUni);
	        	System.out.println("Union registered successfully");
	        	return newUni;
	        } catch (NegativeNumberException | EmptyStringException | NotEnoughMembersException e) {
	        	System.out.println("Registration failed: " + e.toString());
	        }
		}
		
		return newClient;
	}
}