package mainclasses;

import exceptions.*;
import interfaces.Throwable;

/**
 * Represents a hammer
 * @author Raquel Nkwar
 * @version 1.0
 */
public class Hammer extends Weapon implements Throwable {
	private int strengthRequired;
	private TypeWeight weight;
	
	//Standard constructor
	public Hammer(int id, String name, double price, int damage, Dangerous danger, boolean isMagical,
			int strengthRequired, TypeWeight weight)
			throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical);
		setStrengthRequired(strengthRequired);
		setWeight(weight);
	}
	
	//Empty constructor
	public Hammer() {
		
	}
	
	//Copy constructor
	public Hammer(Hammer anotherHammer) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(anotherHammer);
		setStrengthRequired(anotherHammer.strengthRequired);
		setWeight(anotherHammer.weight);
	}

	public int getStrengthRequired() {
		return strengthRequired;
	}

	public void setStrengthRequired(int strengthRequired) throws OutOfRangeException {
		if (strengthRequired >= 1 && strengthRequired <= 5) {
			this.strengthRequired = strengthRequired;
		} else {
			throw new OutOfRangeException();
		}
	}

	public TypeWeight getWeight() {
		return weight;
	}

	public void setWeight(TypeWeight weight) {
		this.weight = weight;
	}
	
	@Override
	public String obtainMaintenance() {
		return "Keep it in a dry place and... hit it, hit everything";
	}
	
	@Override 
	public int calculateGuarantee() {
		int guarantee;
		
		if (super.getDanger() == Dangerous.SAFE) {
			guarantee = 1;
		} else if (super.getDanger() == Dangerous.DANGEROUS) {
			guarantee = 2;
		} else {
			guarantee = 3;
		}
		
		return guarantee;
	}
	
	/**
	 * Based on its weight, it tells the user the radius of the shock wave
	 * @return String with that information
	 */
	public String showShockWave() {
		String shockWave;
		
		if (weight == TypeWeight.HEAVY) {
			shockWave = "This hammer should cause a shock wave of approximately 1 km";
		} else {
			shockWave = "This hammer should cause a shock wave of approximately 250 m";
		}
		
		return shockWave;
	}
	
	/**
	 * Based on its damage, tells what the hammer is capable of breaking and destroying
	 * @return String with the information
	 */
	public String breakingStrength() {
		String breakStrength;
		
		if (super.getDamage() <= 3) {
			breakStrength = "It’s perfectly capable of breaking wood, ice, glass... it might cause a few bruises";
		} else if (super.getDamage() <= 6) {
			breakStrength = "This tenderness can break through walls, stone, metal... be careful not to throw it at someone";
		} else {
			breakStrength = "Nothing can withstand this hammer (expect the worst if you hit someone with it)";
		}
		
		return breakStrength;
	}
	
	@Override
	public int calculateRange() {
		int range;
		
		if (weight == TypeWeight.HEAVY) {
			range = 10;
		} else {
			range = 20;
		}
		
		return range;
	}
	
	@Override
	public String toCSV() {
		return "Hammer," + super.toCSV() + "," + strengthRequired + "," + weight.toString();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		return "Strength Required: " + strengthRequired + " | Weight: " + weight.name()
		+ "\nMaintenance: " + obtainMaintenance()
		+ "\nGuarantee: " + calculateGuarantee()
		+ "\nRange of the shock wave: " + showShockWave()
		+ "\nBreaking strength: " + breakingStrength()
		+ "\nPossible maximum range: " + calculateRange();
	}
}
