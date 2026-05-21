package mainclasses;

import interfaces.AvailableWeapon;
import exceptions.*;

public class Independent extends Client implements AvailableWeapon {
	private int age;
	private LevelExperience experience;
	
	enum LevelExperience {
		NOVICE, MEDIUM, EXPERT
	}

	public Independent(int id, String name, int numberOfOrders, int age, LevelExperience experience) throws NegativeNumberException, UnderAgeException, EmptyStringException {
		super(id, name, numberOfOrders);
		this.age = age;
		this.experience = experience;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws UnderAgeException {
		if (age >= 18) {
			this.age = age;
		} else {
			throw new UnderAgeException();
		}
	}

	public LevelExperience getExperience() {
		return experience;
	}

	public void setExperience(LevelExperience experience) {
		this.experience = experience;
	}
	
	//Independent clients only receive a discount if they have purchased a weapon at least twice before
	@Override
	public double applyDiscount() {
		double discount = 0;
		
		if (super.getNumberOfOrders() >= 2) {
			discount = 0.1;
		}
		
		return discount;
	}
	
	/**
	 * Gives the user a recommendation based on its experience level
	 * @return String with a recommendation
	 */
	public String recommendation() {
		String possibleRec;
		
		if (experience == LevelExperience.EXPERT) {
			possibleRec = "Be free to choose anything you love!!";
		} else if (experience == LevelExperience.MEDIUM) {
			possibleRec = "Having a Medium Experience, guns and hammers might be a little too much, but try to give a chance to guns and heavy swords";
		} else {
			possibleRec = "Try to give a look to grenades, wands and light swords; they are usually the weapons of choice for those who are new to this magical experience";
		}
		
		return possibleRec;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | Age: " + age + " | Experience: " + experience.name();
	}
}
