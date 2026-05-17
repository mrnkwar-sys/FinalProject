package mainclasses;

import interfaces.Throwable;
import exceptions.*;

public class Axe extends Weapon implements Throwable {
	private int power;
	private typeWeight weight;
	
	enum typeWeight {
		LIGHT, HEAVY
	}

	public Axe(int id, String name, double price, int damage, dangerous danger, boolean isMagical, int power,
			typeWeight weight) throws NegativeNumberException, OutOfRangeException, EmptyStringException {
		super(id, name, price, damage, danger, isMagical);
		this.power = power;
		this.weight = weight;
	}
	
	public Axe() {
		
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public typeWeight getWeight() {
		return weight;
	}

	public void setWeight(typeWeight weight) {
		this.weight = weight;
	}

}
