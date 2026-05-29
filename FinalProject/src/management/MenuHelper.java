package management;

import java.util.Scanner;

import mainclasses.Hands;
import mainclasses.Noisy;
import mainclasses.TypeMagic;
import mainclasses.TypeWeight;

/**
 * Retrieves and assigns the enums selected by the user
 * @author Raquel Nkwar
 * @version 1.0
 */
public class MenuHelper {
	/**
	 * Shows the differents options for the TypeMagic enum and lets the user choose one
	 * @param sc
	 * @return The enum option chosen
	 */
	public static TypeMagic establishMagic (Scanner sc) {
		int magicOption;
		
		do {
			System.out.println("Select a type of magic:");
			System.out.println("1. WHITE MAGIC");
	        System.out.println("2. BLACK MAGIC");
	        System.out.println("3. ILUSIONISM");
	        System.out.println("4. SPIRITUAL");
	        System.out.print("Option: ");
	        magicOption = sc.nextInt();
	        sc.nextLine();
		} while (magicOption < 1 || magicOption > 4);
        
        // Turn the numeric option into an option from the enum
        mainclasses.TypeMagic type = mainclasses.TypeMagic.WHITE_MAGIC;
        if (magicOption == 1) {
        	type = mainclasses.TypeMagic.WHITE_MAGIC;
        } else if (magicOption == 2) {
        	type = mainclasses.TypeMagic.BLACK_MAGIC;
        } else if (magicOption == 3) {
        	type = mainclasses.TypeMagic.ILUSIONISM;
        } else if (magicOption == 4) {
        	type = mainclasses.TypeMagic.SPIRITUAL;
        }
        
        return type;
	}
	
	/**
	 * Shows the differents options for the hands enum and lets the user choose one
	 * @param sc
	 * @return The enum option chosen
	 */
	public static Hands establishHandsType(Scanner sc) {
		int handsOption;
		
		do {
			System.out.println("Select a type of sword:");
			System.out.println("1. ONE HAND");
	        System.out.println("2. HAND AND A HALF");
	        System.out.println("3. TWO HAND");
	        System.out.print("Option: ");
	        handsOption = sc.nextInt();
	        sc.nextLine();
		} while (handsOption < 1 || handsOption > 3);
        
        // Turn the numeric option into an option from the enum
        mainclasses.Hands type = mainclasses.Hands.HAND_AND_A_HALF;
        if (handsOption == 1) {
        	type = mainclasses.Hands.ONE_HAND;
        } else if (handsOption == 2) {
        	type = mainclasses.Hands.HAND_AND_A_HALF;
        } else if (handsOption == 3) {
        	type = mainclasses.Hands.TWO_HAND;
        }
        
        return type;
	}
	
	/**
	 * Shows the differents options for the noise and lets the user choose one
	 * @param sc
	 * @return The enum option chosen
	 */
	public static Noisy establishNoise(Scanner sc) {
		int noiseOption;
		
		do {
			System.out.println("Select a type of noise for the gun:");
			System.out.println("1. SILENT");
	        System.out.println("2. NOISY");
	        System.out.print("Option: ");
	        noiseOption = sc.nextInt();
	        sc.nextLine();
		} while (noiseOption < 1 || noiseOption > 2);
        
        // Turn the numeric option into an option from the enum
        mainclasses.Noisy noise = mainclasses.Noisy.SILENT;
        if (noiseOption == 1) {
        	noise = mainclasses.Noisy.SILENT;
        } else if (noiseOption == 2) {
        	noise = mainclasses.Noisy.NOISY;
        }
        
        return noise;
	}
	
	/**
	 * Shows the differents options for the weight and lets the user choose one
	 * @param sc
	 * @return The enum option chosen
	 */
	public static TypeWeight establishWeight(Scanner sc) {
		int weightOption;
		
		do {
			System.out.println("Select a type of weight:");
			System.out.println("1. LIGHT");
	        System.out.println("2. HEAVY");
	        System.out.print("Option: ");
	        weightOption = sc.nextInt();
	        sc.nextLine();
		} while (weightOption < 1 || weightOption > 2);
        
        // Turn the numeric option into an option from the enum
        mainclasses.TypeWeight weight = mainclasses.TypeWeight.LIGHT;
        if (weightOption == 1) {
        	weight = mainclasses.TypeWeight.LIGHT;
        } else if (weightOption == 2) {
        	weight = mainclasses.TypeWeight.HEAVY;
        }
        
        return weight;
	}
}
