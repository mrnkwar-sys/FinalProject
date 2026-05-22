package mainclasses;

import exceptions.*;

public class Union extends Client {
	private String type;
	private int members;
	
	public Union(int id, String name, int numberOfOrders, String type, int members)
			throws NegativeNumberException, UnderAgeException, EmptyStringException {
		super(id, name, numberOfOrders);
		this.type = type;
		this.members = members;
	}
	
	public Union() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws EmptyStringException {
		if (!(type.isBlank() || type.isEmpty())) {
			this.type = type;
		} else {
			throw new EmptyStringException();
		}
	}

	public int getMembers() {
		return members;
	}

	//A union should have, at least, 5 members
	public void setMembers(int members) throws NotEnoughMembersException {
		if (members > 5) {
			this.members = members;
		} else {
			throw new NotEnoughMembersException();
		}
	}
	
	/*
	 * If the union has placed less than two orders, the discount will be 5%
	 * By contrast, the discount will be 15%
	 */
	@Override
	public double applyDiscount(double finalPrice) {
		double discount = finalPrice*0.05;
		
		if (super.getNumberOfOrders() >= 2) {
				discount = finalPrice*0.15;
		}
		
		return discount;
	}
	
	//If the union has more than 50 members, the will have to pay 30 coins less
	public double discountPerMembers(double finalPrice) {
		
		if (members >= 50) {
			finalPrice = finalPrice - 30;
		}
		
		return finalPrice;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Type: " + type + " | Number of members: " + members;
	}
}
