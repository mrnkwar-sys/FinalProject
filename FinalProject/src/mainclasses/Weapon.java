package mainclasses;

import java.util.Objects;

import exceptions.*;

/**
 * Represents a weapon that is sold in the shop
 * @author Raquel Nkwar
 * @version 1.0
 */
public abstract class Weapon {
	private int id;
	private String name;
	private double price;
	//Range of damage: 1-10, where 1 is the least damage a weapon can cause, and 10 the most 
	private int damage;
	private Dangerous danger;
	private boolean isMagical;

	//Standar constructor
	public Weapon(int id, String name, double price, int damage, Dangerous danger, boolean isMagical) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		setId(id);
		setName(name);
		setPrice(price);
		setDamage(damage);
		setDanger(danger);
		setMagical(isMagical);
	}
	
	//Empty constructor
	public Weapon() {
		
	}
	
	//Copy constructor
	public Weapon(Weapon anotherWeapon) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		setId(anotherWeapon.id);
		setName(anotherWeapon.name);
		setPrice(anotherWeapon.price);
		setDamage(anotherWeapon.damage);
		setDanger(anotherWeapon.danger);
		setMagical(anotherWeapon.isMagical);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws NegativeNumberException {
		if (id >= 0) {
			this.id = id;
		} else {
			throw new NegativeNumberException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws EmptyStringException {
		if (!(name.isBlank() || name.isEmpty())) {
			this.name = name;
		} else {
			throw new EmptyStringException();
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws NegativeNumberException {
		if (price > 0) {
			this.price = price;
		} else {
			throw new NegativeNumberException();
		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) throws OutOfRangeException {
		if (damage >= 1 || damage <= 10) {
			this.damage = damage;
		} else {
			throw new OutOfRangeException();
		}
	}

	public Dangerous getDanger() {
		return danger;
	}

	public void setDanger(Dangerous danger) {
		this.danger = danger;
	}

	public boolean isMagical() {
		return isMagical;
	}

	public void setMagical(boolean isMagical) {
		this.isMagical = isMagical;
	}
	
	/**
	 * Tells if two weapons are, in fact, the same weapon because they have the same id
	 * @param anotherWeapon
	 * @return True if they are
	 */
	public boolean equals(Weapon anotherWeapon) {
		boolean sameWeapon = false;
		
		if (anotherWeapon.id == this.id) {
			sameWeapon = true;
		}
		
		return sameWeapon;
	}
	
	/**
	 * Returns an integer that serves as the weapon's unique identifier
	 */
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	 * Depening on the weapon, it tells the client how to keep their weapon in good condition
	 * @return A String with the instructions for the weapon's maintenance
	 */
	public abstract String obtainMaintenance();
	
	/**
	 * Depending on its danger, the weapons will have different guarantees
	 * @param danger
	 * @return How many years will last that guarantee
	 */
	public abstract int calculateGuarantee();
	
	/**
	 * Returns a String with the additional data (power, type...) for each weapon
	 * @return String
	 */
	public abstract String toStringAdditionalData();
	
	/**
	 * Applies a discount on the weapon
	 * @param percentage
	 */
	public void applyUniqueDiscount(double percentage) {
		price = price*(1 - percentage / 100);
	}
	
	/**
	 * Returns the information in a format that can be easily read and written to the file
	 * @return
	 */
	public String toCSV() {
		return id + "," + name + "," + price + "," + damage + "," + danger + "," + isMagical;
	}
	
	/**
	 * Returns a String with all the weapon's data
	 */
	public String toString() {
		String magicWeapon;
		
		if (isMagical) {
			magicWeapon = "This weapon requires magic skills";
		} else {
			magicWeapon = "This weapon does not require magic skills";
		}
		
		return "ID: " + id + " | Name: " + name + " | Price:" + price + " | Damage: " + damage + " | Danger: " + danger.name() + " | " + magicWeapon;
	}
}
