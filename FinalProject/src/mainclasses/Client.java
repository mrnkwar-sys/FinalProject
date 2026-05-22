package mainclasses;

import exceptions.*;

public abstract class Client {
	private int id;
	private String name;
	private int numberOfOrders;
	
	public Client(int id, String name, int numberOfOrders) throws NegativeNumberException, UnderAgeException, EmptyStringException {
		setId(id);
		setName(name);
		setNumberOfOrders(numberOfOrders);
	}
	
	public Client() {
		
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
	
	public boolean equals(Client anotherClient) {
		boolean sameClient = false;
		
		if (this.id == anotherClient.id) {
			sameClient = true;
		}
		
		return sameClient;
	}
	
	/**
	 * Applies a discount or not depending on the type of client
	 * @return The percentage of discount
	 */
	public abstract double applyDiscount(double finalPrice);
}
