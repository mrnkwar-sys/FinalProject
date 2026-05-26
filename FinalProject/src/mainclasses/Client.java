package mainclasses;

import java.util.Objects;

import exceptions.*;

public abstract class Client {
	private int id;
	private String name;
	private int numberOfOrders;
	
	//Standard constructor
	public Client(int id, String name, int numberOfOrders) throws NegativeNumberException, EmptyStringException {
		setId(id);
		setName(name);
		setNumberOfOrders(numberOfOrders);
	}
	
	//Empty constructor
	public Client() {
		
	}
	
	//Copy constructor
	public Client(Client anotherClient) throws NegativeNumberException, EmptyStringException {
		setId(anotherClient.id);
		setName(anotherClient.name);
		setNumberOfOrders(anotherClient.numberOfOrders);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) throws NegativeNumberException {
		if (id > 0) {
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
	
	public int getNumberOfOrders() {
		return numberOfOrders;
	}
	
	public void setNumberOfOrders(int numberOfOrders) throws NegativeNumberException {
		if (numberOfOrders >= 0) {
			this.numberOfOrders = numberOfOrders;
		} else {
			throw new NegativeNumberException();
		}
	}
	
	public String toString() {
		return "ID: " + id + " | Name: " + name;
	}
	
	public String toCSV() {
		return id + "," + name + "," + numberOfOrders;
	}
	
	/**
	 * Tells if two clients are, in fact, the same client because they have the same id and name
	 * @param anotherClient
	 * @return True if they are
	 */
	public boolean equals(Client anotherClient) {
		boolean sameClient = false;
		
		if (this.id == anotherClient.id && this.name.equalsIgnoreCase(anotherClient.name)) {
			sameClient = true;
		}
		
		return sameClient;
	}
	
	/**
	 * Returns an integer that serves as the client's unique identifier
	 */
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	/**
	 * Applies a discount or not depending on the type of client
	 * @return The percentage of discount
	 */
	public abstract double applyDiscount(double finalPrice);
	
	/**
	 * It increases the numbers of orders placed by a user
	 */
	public void increaseOrders() {
		numberOfOrders = numberOfOrders + 1;
	}
}
