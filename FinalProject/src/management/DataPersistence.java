package management;

import java.io.*;
import java.util.ArrayList;

import exceptions.*;
import mainclasses.*;

public class DataPersistence {
	//Save the files pathes in a variable
	private static final String STOCK_FILE = "../persistence/StockData";
	private static final String CLIENTS_FILE = "../persistence/ClientData";
	
	/**
	 * Saves the inventory in the file
	 * It retrieves the complete list of weapons from the store and prints them line by line 
	 * @param inventory
	 */
	public static void saveInventory(ArrayList<Weapon> inventory) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(STOCK_FILE))) {
			
			for (Weapon weapon : inventory) {
				//Write in the file by calling each weapon's toString method
				writer.write(weapon.toCSV());
				writer.newLine();
			}
			
			System.out.println("Stock saved successfully in the file");
		} catch (IOException e) {
			System.out.println("Error while trying to save to the file: " + e.getMessage());
		}
	}
	
	/**
	 * Loads the inventory from the file
	 * Reads the file line by line and reconstructs the objects
	 * @return The list with all the weapons from the inventory
	 */
	public static ArrayList<Weapon> loadInventory() {
		ArrayList<Weapon> loadedWeapons = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(STOCK_FILE))){
			String line;
			
			//It reads line by line until we reach the end of the file (null)
			while ((line = reader.readLine()) != null) {
				//If the line is empty, we skip it for safety reason
				if (line.trim().isEmpty()) {
					continue;
				}
				
				//We break the line into parts using a comma as a separator
				String[] parts = line.split(",");
				
				//The first word of the line will always be the weapon's type (Sword, Hammer, Axe...)
				String type = parts[0]; 
				
				//Extract the common atributes that all weapons have
				int id = Integer.parseInt(parts[1]);
				String name = parts[2];
				double price = Double.parseDouble(parts[3]);
				int damage = Integer.parseInt(parts[4]);
				Dangerous danger = Dangerous.valueOf(parts[5]);
				boolean isMagical = Boolean.parseBoolean(parts[6]);
				int guarantee = Integer.parseInt(parts[7]);
				
				//We determine which subclass it is in order to read its specific attributes and instantiate it
				switch(type) {
				case "Axe" -> {
					int power = Integer.parseInt(parts[8]);
					TypeWeight axeWeight = TypeWeight.valueOf(parts[9]);
					
					try {
						Axe axe = new Axe(id, name, price, damage, danger, isMagical, guarantee, power, axeWeight);
						
						loadedWeapons.add(axe);
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (OutOfRangeException o) {
						System.out.println(o.toString());
					}
				}
				case "Grenade" -> {
					int power = Integer.parseInt(parts[8]);
					int delay = Integer.parseInt(parts[9]);
					boolean isIncendiary = Boolean.parseBoolean(parts[10]);
					
					try {
						Grenade grenade = new Grenade(id, name, price, damage, danger, isMagical, guarantee, power, delay, isIncendiary);
						
						loadedWeapons.add(grenade);
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (OutOfRangeException o) {
						System.out.println(o.toString());
					}
				}
				case "Gun" -> {
					int power = Integer.parseInt(parts[8]);
					Noisy noiseGun = Noisy.valueOf(parts[9]);
					
					try {
						Gun gun = new Gun(id, name, price, damage, danger, isMagical, guarantee, power, noiseGun);
						
						loadedWeapons.add(gun);
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (OutOfRangeException o) {
						System.out.println(o.toString());
					}
				}
				case "Hammer" -> {
					int strengthRequired = Integer.parseInt(parts[8]);
					TypeWeight hammerWeight = TypeWeight.valueOf(parts[9]);
					
					try {
						Hammer hammer = new Hammer(id, name, price, damage, danger, isMagical, guarantee, strengthRequired, hammerWeight);
						
						loadedWeapons.add(hammer);
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (OutOfRangeException o) {
						System.out.println(o.toString());
					}
				}
				case "Sword" -> {
					Hands swordHands = Hands.valueOf(parts[8]);
					TypeWeight swordWeight = TypeWeight.valueOf(parts[9]);
					
					try {
						Sword hammer = new Sword(id, name, price, damage, danger, isMagical, guarantee, swordHands, swordWeight);
						
						loadedWeapons.add(hammer);
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (OutOfRangeException o) {
						System.out.println(o.toString());
					}
				}
				case "Wand" -> {
					boolean isExclusive = Boolean.parseBoolean(parts[8]);
					int ownerBond = Integer.parseInt(parts[9]);
					TypeMagic wandMagic = TypeMagic.valueOf(parts[10]);
					
					try {
						Wand wand = new Wand(id, name, price, damage, danger, isMagical, guarantee, isExclusive, ownerBond, wandMagic);
						
						loadedWeapons.add(wand);
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (OutOfRangeException o) {
						System.out.println(o.toString());
					}
				}
				default -> {
					System.out.println("Unkown weapon in the file: " + type);
				}
				}
			}
		} catch (IOException e) {
			/*
			 * If the file does not exist yet (the first time the program is opened),
			 * it returns the empty list
			 */
			System.out.println("No previous inventory was found");
		}
		
		return loadedWeapons;
	}
	
	/**
	 * Saves the clients in the file
	 * It retrieves the store's customer list and writes it to the file 
	 * @param clients
	 */
	public static void saveClients(ArrayList<Client> clients) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTS_FILE))){
			
			for (Client client : clients) {
				writer.write(client.toCSV());
				writer.newLine();
			}
			
			System.out.println("List of clients saved successfully");
		} catch (IOException e) {
			System.out.println("Error while trying to save to the file: " + e.getMessage());
		}
	}
	
	/**
	 * Loads the clients from the file
	 * Reads the file and fills the loadClients ArrayList
	 * @return List with all the clients
	 */
	public static ArrayList<Client> loadClients(){
		ArrayList<Client> loadedClients = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTS_FILE))){
			String line;
			
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue;
				}
				
				String[] parts = line.split(",");
				
				//The first word of the line will always be the client's type (Independent or Union)
				String typeOfClient = parts[0];
				
				//Extract the common atributes that all clients have
				int id = Integer.parseInt(parts[1]);
				String name = parts[2];
				int numberOfOrders = Integer.parseInt(parts[3]);
				
				//We determine which subclass it is in order to read its specific attributes and instantiate it
				if ("Independent".equalsIgnoreCase(typeOfClient)) {
					int age = Integer.parseInt(parts[4]);
					LevelExperience experience = LevelExperience.valueOf(parts[5]);
					
					try {
						Independent ind = new Independent(id, name, numberOfOrders, age, experience);
						
						loadedClients.add(ind);
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (UnderAgeException u) {
						System.out.println(u.toString());
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					}
				} else if ("Union".equalsIgnoreCase(typeOfClient)) {
					String type = parts[4];
					int members = Integer.parseInt(parts[5]);
					
					try {
						Union union = new Union(id, name, numberOfOrders, type, members);
						
						loadedClients.add(union);
					} catch (NegativeNumberException n) {
						System.out.println(n.toString());
					} catch (NotEnoughMembersException o) {
						System.out.println(o.toString());
					} catch (EmptyStringException e) {
						System.out.println(e.toString());
					}
				}
			}
		} catch (IOException e) {
			System.out.println("No previous customer history was found");
		}
		
		return loadedClients;
	}
}