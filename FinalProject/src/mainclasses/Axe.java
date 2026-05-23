package mainclasses;

import interfaces.Throwable;
import interfaces.Carryable;
import exceptions.*;

public class Axe extends Weapon implements Throwable, Carryable {
	private int power;
	private TypeWeight weight;

	public Axe(int id, String name, double price, int damage, Dangerous danger, boolean isMagical, int guarantee, int power,
			TypeWeight weight) throws NegativeNumberException, OutOfRangeException, EmptyStringException {
		super(id, name, price, damage, danger, isMagical, guarantee);
		setPower(power);
		setWeight(weight);
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

	@Override
	public TypeWeight getWeight() {
		return weight;
	}

	public void setWeight(TypeWeight weight) {
		this.weight = weight;
	}
	
	@Override 
	public String obtainMaintenance() {
		return "Clean and sharpen your axe so you can chop... cleanly";
	}
	
	@Override
	public void calculateGuarantee() {
		try {
			if (super.getDanger() == Dangerous.SAFE) {
				super.setGuarantee(2);
			} else if (super.getDanger() == Dangerous.DANGEROUS) {
				super.setGuarantee(3);
			} else {
				super.setGuarantee(4);
			}
		} catch (NegativeNumberException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Based on the weapon's power, it predicts how long it will take for it to wear out
	 * @return A phrase related to its possible useful time
	 */
	public String predictWearing() {
		String possibleWearing;
		
		if (power <= 15) {
			possibleWearing = "Not a lot of power, but might this relationship last a really long, long time?";
		} else if (power <= 35){
			possibleWearing = "Don’t worry, with the right care, it’ll last you a long time";
		} else {
			possibleWearing = "Perhaps time is the price one pays for having so much power...";
		}
		
		return possibleWearing;
	}
	
	@Override
	public int calculateRange() {
		int range;
		
		if (power <= 25) {
			range = 4;
		} else {
			range = 8;
		}
		
		return range;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		return " | Power: " + power + " | Weight: " + weight.name() + " | Power: " + power + " | Weight: " + weight.name() + " | Case: " + suggestCase();
	}

}
