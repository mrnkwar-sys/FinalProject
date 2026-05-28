package mainclasses;

import interfaces.Throwable;
import interfaces.Carryable;
import exceptions.*;

public class Axe extends Weapon implements Throwable, Carryable {
	private int power;
	private TypeWeight weight;

	//Standard constructor
	public Axe(int id, String name, double price, int damage, Dangerous danger, boolean isMagical, int power,
			TypeWeight weight) throws NegativeNumberException, OutOfRangeException, EmptyStringException {
		super(id, name, price, damage, danger, isMagical);
		setPower(power);
		setWeight(weight);
	}
	
	//Empty constructor
	public Axe() {
		
	}
	
	//Copy constructor
	public Axe(Axe anotherAxe) throws NegativeNumberException, OutOfRangeException, EmptyStringException {
		super(anotherAxe);
		setPower(anotherAxe.power);
		setWeight(anotherAxe.weight);
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
	public int calculateGuarantee() {
		int guarantee;
		
		if (super.getDanger() == Dangerous.SAFE) {
			guarantee = 2;
		} else if (super.getDanger() == Dangerous.DANGEROUS) {
			guarantee = 3;
		} else {
			guarantee = 4;
		}
		
		return guarantee;
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
	public String toCSV() {
		return "Axe," + super.toCSV() + "," + power + "," + weight.toString();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		return " | Power: " + power + " | Weight: " + weight.name() + " | Power: " + power + " | Weight: " + weight.name() + " | Case: " + suggestCase()
			+ "Maintenance: " + obtainMaintenance()
			+ "\n Wearing prediction: " + predictWearing()
			+ "\n Possible maximum range: " + calculateRange() + " meters"
			+ "\n Guarantee: " + calculateGuarantee();
	}

}
