package mainclasses;

import interfaces.Throwable;
import exceptions.*;

public class Axe extends Weapon implements Throwable {
	private int power;
	private typeWeight weight;
	
	enum typeWeight {
		LIGHT, HEAVY
	}

	public Axe(int id, String name, double price, int damage, dangerous danger, boolean isMagical, int guarantee, int power,
			typeWeight weight) throws NegativeNumberException, OutOfRangeException, EmptyStringException {
		super(id, name, price, damage, danger, isMagical, guarantee);
		this.power = power;
		this.weight = weight;
	}
	
	public Axe() {
		
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) throws OutOfRangeException {
		if (power > 0 && power <= 50) {
			this.power = power;
		} else {
			throw new OutOfRangeException();
		}
	}

	public typeWeight getWeight() {
		return weight;
	}

	public void setWeight(typeWeight weight) {
		this.weight = weight;
	}
	
	@Override 
	public String obtainMaintenance() {
		return "Clean and sharpen your axe so you can chop... cleanly";
	}
	
	@Override
	public void calculateGuarantee() {
		try {
			if (super.getDanger() == Weapon.dangerous.SAFE) {
				super.setGuarantee(2);
			} else if (super.getDanger() == Weapon.dangerous.DANGEROUS) {
				super.setGuarantee(3);
			} else {
				super.setGuarantee(4);
			}
		} catch (NegativeNumberException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * If the weapon is heavy, a case for it will be suggested to the client
	 * @return True if the weapon is heavy
	 */
	public boolean suggestCase() {
		boolean needsCase = false;
		
		if (weight == typeWeight.HEAVY) {
			needsCase = true;
		}
		
		return needsCase;
	}
	
	@Override
	public String toString() {
		String aCase;
		
		if (suggestCase()) {
			aCase = "You should use a case with this one";
		} else {
			aCase = "You do not need a case for this one, but it will be cute";
		}
		
		return super.toString() + " | Power: " + power + " | Weight: " + weight.name() + " | Power: " + power + " | Weight: " + weight.name() + " | Case: " + aCase;
	}

}
