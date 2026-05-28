package management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mainclasses.*;
import exceptions.*;

public class InventoryManagement {
	private Map<Integer, Weapon> stock;
	
	public InventoryManagement(List<Weapon> initialWeapons) {
		stock = new HashMap<>();
		
		for (Weapon weapon : initialWeapons) {
			//Use the weapon's id as a unique key
			stock.put(weapon.getId(), weapon);
		}
	}
	
	/**
	 * Adds a new weapon to the shop
	 * @param weapon
	 */
	public void addWeapon(Weapon weapon) {
		stock.put(weapon.getId(), weapon);
	}
	
	/**
	 * Removes a weapon (searched by its id) from the shop 
	 * @param id
	 */
	public void removeWeapon(int id) throws WeaponNotFoundException {
		Weapon weapon = stock.remove(id);
		
		if (weapon == null) {
			throw new WeaponNotFoundException();
		}
	}
	
	public void updateWeaponNamePrice(int id, String name, double price) {
		Weapon weapon = stock.get(id);
		
		if (weapon != null) {
			try {
				weapon.setName(name);
				weapon.setPrice(price);
			} catch (EmptyStringException | NegativeNumberException e) {
				System.out.println(e.toString());
			}
		} else {
			System.out.println("It was not possible to modify the data because no weapon with that ID was found in the database");
		}
	}
	
	/**
	 * Searches a weapon by its id
	 * @param id The searched weapon's id
	 * @return The weapon searched
	 */
	public Weapon getWeapon(int id) throws WeaponNotFoundException {
		Weapon weapon = stock.get(id);
		
		if (weapon == null) {
			throw new WeaponNotFoundException();
		}
		
		return weapon;
	}
	
	/**
	 * A workaround for when transfering all inventory to DataPersistence is needed
	 * @return The list with all the weapons
	 */
	public List<Weapon> getAllWeapons(){
		return stock.values().stream().collect(Collectors.toList());
	}
	
	/**
	 * Filter the weapons by its danger level
	 * @param dangerLevel
	 * @return A list with the weapons that have he same exact level of danger searched
	 */
	public List<Weapon> filterByDanger(String dangerLevel){
		//Launch the Stream channel dedicated to Weapon items
		return stock.values().stream()
				//Filter by the Enum/String
				.filter(weapon -> weapon.getDanger().toString().equalsIgnoreCase(dangerLevel))
				//Wrap the result in a list
				.collect(Collectors.toList());
	}
	
	/**
	 * Obtains the most expensive weapon from the inventory
	 * @return Said weapon
	 */
	public Weapon getMostExpensiveWeapon() {
		return stock.values().stream()
				//Compare prices in pairs
				.max((firstWeapon, secondWeapon) -> Double.compare(firstWeapon.getPrice(), secondWeapon.getPrice()))
				//If the inventory is empty, returns null
				.orElse(null);
	}
	
	/**
	 * Only obtain magical weapons that deal more than a specified minimum amount of damage
	 * @param minDamage The minimum amount of damaged specified by the user
	 * @return A list with all the weapons that meet that criterion
	 */
	public List<Weapon> getPowerfulMagicWeapons(int minDamage){
		return stock.values().stream()
				//The equivalent to weapon -> weapon.isMagical() == true
				.filter(Weapon::isMagical)
				//Second filter for the damage
				.filter(weapon -> weapon.getDamage() > minDamage)
				.collect(Collectors.toList());
	}
	
	/**
	 * Applies a discount only to a specific type of weapon
	 * @param percentage The percentage that will be discounted
	 * @param weaponType The type of weapon that will suffer the discount
	 */
	public void applyDiscountByType(double percentage, String weaponType) {
		for (Weapon weapon : stock.values()) {
			if ("Axe".equalsIgnoreCase(weaponType) && weapon instanceof Axe) {
				weapon.applyUniqueDiscount(percentage);
			} else if ("Grenade".equalsIgnoreCase(weaponType) && weapon instanceof Grenade){
				weapon.applyUniqueDiscount(percentage);
			} else if ("Gun".equalsIgnoreCase(weaponType) && weapon instanceof Gun) {
				weapon.applyUniqueDiscount(percentage);
			} else if ("Hammer".equalsIgnoreCase(weaponType) && weapon instanceof Hammer) {
				weapon.applyUniqueDiscount(percentage);
			} else if ("Sword".equalsIgnoreCase(weaponType) && weapon instanceof Sword) {
				weapon.applyUniqueDiscount(percentage);
			} else if ("Wand".equalsIgnoreCase(weaponType) && weapon instanceof Wand) {
				weapon.applyUniqueDiscount(percentage);
			}
		}
	}
}
