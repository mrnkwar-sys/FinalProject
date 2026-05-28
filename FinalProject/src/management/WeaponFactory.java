package management;

import java.util.Scanner;

import exceptions.EmptyStringException;
import exceptions.NegativeNumberException;
import exceptions.OutOfRangeException;
import mainclasses.Weapon;

public class WeaponFactory {
	/**
	 * Adds a new weapon to the database
	 * @param newId
	 * @param sc
	 * @return the new weapon
	 * @throws NegativeNumberException
	 * @throws OutOfRangeException
	 * @throws EmptyStringException
	 */
	public static Weapon registerNewWeapon(int newId, Scanner sc) throws NegativeNumberException, OutOfRangeException, EmptyStringException {
		System.out.println("---Registration of a new Weapon in the Catalogue---");
		
		//First, we save the common atribute for all the weapons
		
		System.out.println("Introduce the weapon's name: ");
		String name = sc.nextLine();
		
		System.out.println("Introduce the weapon's price: ");
		double price = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Introduce the base damage points (range 1-10): ");
		int damage = sc.nextInt();
		sc.nextLine();
		
		System.out.println("How dangerous is this weapon?");
		System.out.println("1. SAFE");
        System.out.println("2. DANGEROUS");
        System.out.println("3. VERY DANGEROUS");
        System.out.print("Option: ");
		int dangerOption = sc.nextInt();
		sc.nextLine();
		
		// Turn the numeric option into an option from the enum
        mainclasses.Dangerous danger = mainclasses.Dangerous.SAFE;
        if (dangerOption == 1) {
        	danger = mainclasses.Dangerous.SAFE;
        } else if (dangerOption == 2) {
        	danger = mainclasses.Dangerous.DANGEROUS;
        } else if (dangerOption == 3) {
        	danger = mainclasses.Dangerous.VERY_DANGEROUS;
        }

		
		System.out.println("Is it a magical weapon? Introduce true or false: ");
		boolean isMagical = sc.nextBoolean();
		sc.nextLine();
		
		//Now the user has to tell the weapon's type
		System.out.println("What kind of weapon are you going to introduce in the database?");
		System.out.println("1. Axe");
		System.out.println("2. Grenade");
		System.out.println("3. Gun");
		System.out.println("4. Hammer");
		System.out.println("5. Sword");
		System.out.println("6. Wand");
		System.out.println("Select an option from the list: ");
		int weaponType = sc.nextInt();
		sc.nextLine();
		
		Weapon newWeapon = null;
		
		try {
			switch (weaponType) {
			case 1 -> {
				System.out.println("Introduce the power of the axe (range -> 0-50): ");
				int power = sc.nextInt();
				sc.nextLine();
				
				mainclasses.TypeWeight newWeight = MenuHelper.establishWeight(sc);
	            
	            Weapon newAxe = new mainclasses.Axe(newId, name, price, damage, danger, isMagical, power, newWeight);
	            
	            //Add the weapon to the list
	            return newAxe;
			}
			case 2 -> {
				System.out.println("Introduce the power of the grenade (range -> 0-120): ");
				int power = sc.nextInt();
				
				System.out.println("Enter the time it takes for the grenade to explode (seconds without decimals): ");
				int delay = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Is it incendiary? (True/False)");
				boolean isIncendiary = sc.nextBoolean();
				sc.nextLine();
				
				Weapon newGrenade = new mainclasses.Grenade(newId, name, price, damage, danger, isMagical, power, delay, isIncendiary);
				
				return newGrenade;
			}
			case 3 -> {
				System.out.println("Introduce the power of the gun (range -> 0-90): ");
				int power = sc.nextInt();
				sc.nextLine();
				
				mainclasses.Noisy newNoise = MenuHelper.establishNoise(sc);
				
				Weapon newGun = new mainclasses.Gun(newId, name, price, damage, danger, isMagical, power, newNoise);
				
				return newGun;
			}
			case 4 -> {
				System.out.println("Introduce the level of strength required to use the hammer: ");
				int strengthRequired = sc.nextInt();
				sc.nextLine();
				
				mainclasses.TypeWeight newWeight = MenuHelper.establishWeight(sc);
				
				Weapon newHammer = new mainclasses.Hammer(newId, name, price, damage, danger, isMagical, strengthRequired, newWeight);
				
				return newHammer;
			}
			case 5 -> {
				mainclasses.Hands newHands = MenuHelper.establishHandsType(sc);
				
				mainclasses.TypeWeight newWeight = MenuHelper.establishWeight(sc);
				
				Weapon newSword = new mainclasses.Sword(newId, name, price, damage, danger, isMagical, newHands, newWeight);
				
				return newSword;
			}
			case 6 -> {
				System.out.println("Is it exclusive for an specific type of magic? (True/False)");
				boolean isExclusive = sc.nextBoolean();
				sc.nextLine();
				
				System.out.println("Introduce the level bonding that the wand requires (range 1-3): ");
				int levelBonding = sc.nextInt();
				sc.nextLine();
				
				mainclasses.TypeMagic newMagic = MenuHelper.establishMagic(sc);
				
				Weapon newWand = new mainclasses.Wand(newId, name, price, damage, danger, isMagical, isExclusive, levelBonding, newMagic);
				
				return newWand;
			}
			default -> {
				throw new IllegalArgumentException("Invalid weapon type selection.");
			}
			}
		} catch (NegativeNumberException | EmptyStringException | OutOfRangeException e) {
			System.out.println(e.toString());
		}
		
		return newWeapon;
	}
}
