package management;

import java.util.Scanner;
import mainclasses.*;

/**
 * In which the entire application runs and, essentially, displays the main menu
 * @author Raquel Nkwar
 * @version 1.0
 */
public class MainShop {

	public static void main(String[] args) {
		DataSaleManagement magicalShop = new DataSaleManagement();
		Scanner sc = new Scanner(System.in);
		
		//Automatic file upload
		magicalShop.initShop();
		
		int rol = 0;
		
		do {
			System.out.println("=========WELCOME TO MAGICAL GIRLS' SHOP=========");
			System.out.println("1. Log in as an ADMIN");
			System.out.println("2. Log in as a CLIENT");
			System.out.println("3. Close the shop (Save and leave)");
			System.out.println("Select an option: ");
			
			if (!sc.hasNextInt()) {
				System.out.println("Error: Introduce a valid number");
				sc.next();
				continue;
			}
			
			rol = sc.nextInt();
			sc.nextLine();
			
			switch (rol) {
			case 1 -> {
				magicalShop.adminMenu(sc);
			}
			case 2 -> {
				//Find out which customer is making the purchase
				Client activeClient = magicalShop.selectCreateClient(sc);
				
				//Go to the clients menu with that client
				if (activeClient != null) {
					magicalShop.clientMenu(sc, activeClient);
				} else {
					System.out.println("Access denied: Invalid customer data");
				}
			}
			case 3 -> {
				System.out.println("Closing the shop... Thanks for using the program :D");
			}
			default -> {
				System.out.println("Invalid option; Introduce an option from the menu (1-3)");
			}
			}
		} while (rol != 3);
		
		//Final automatic save
		magicalShop.closeShop();
		sc.close();
		System.out.println("BYE BYE! Data saved securely");

	}

}
