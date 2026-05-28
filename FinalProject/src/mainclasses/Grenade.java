package mainclasses;

import interfaces.Throwable;
import exceptions.*;
import interfaces.ConsumableMagic;

public class Grenade extends Weapon implements Throwable, ConsumableMagic {
	private int power;
	private int delay;
	private boolean isIncendiary;
	
	//Standard constructor
	public Grenade(int id, String name, double price, int damage, Dangerous danger, boolean isMagical,
			int power, int delay, boolean isIncendiary)
			throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical);
		setPower(power);
		setDelay(delay);
		setIncendiary(isIncendiary);
	}
	
	//Empty constructor
	public Grenade() {
		
	}
	
	//Copy constructor
	public Grenade(Grenade anotherGrenade) throws NegativeNumberException, EmptyStringException, OutOfRangeException{
		super(anotherGrenade);
		setPower(anotherGrenade.power);
		setDelay(anotherGrenade.delay);
		setIncendiary(anotherGrenade.isIncendiary);
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) throws OutOfRangeException {
		if (power > 0 && power <= 120) {
			this.power = power;
		} else {
			throw new OutOfRangeException();
		}
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) throws NegativeNumberException {
		if (delay >= 0) {
			this.delay = delay;
		} else {
			throw new NegativeNumberException();
		}
	}

	public boolean isIncendiary() {
		return isIncendiary;
	}

	public void setIncendiary(boolean isIncendiary) {
		this.isIncendiary = isIncendiary;
	}
	
	@Override
	public String obtainMaintenance() {
		return "Keep it cool, away from the heat, otherwise... KABLOW, KABOOM!!";
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
	 * Tells the user what to do if the grenade causes a fire 
	 * @return String with the explanation
	 */
	public String incendiaryProtocol() {
		String protocol;
		
		if (isIncendiary) {
			protocol = "CALM DOWN and look for a fire extinguisher (call the fire brigade if things get really bad; you are a magical girl, not a firefighter)";
		} else {
			protocol = "Do not worry, it is not incendiary";
		}
		
		return protocol;
	}
	
	@Override
	public int magicConsumed() {
		int percentageMagic;
		
		if (super.getDamage() <= 3) {
			percentageMagic = 15;
		} else if (super.getDamage() <= 7) {
			percentageMagic = 20;
		} else {
			percentageMagic = 25;
		}
		
		return percentageMagic;
	}
	
	@Override
	public int calculateRange() {
		int range;
		
		if (power <= 40) {
			range = 10;
		} else if (power <= 80) {
			range = 20;
		} else {
			range = 60;
		}
		
		return range;
	}
	
	@Override
	public String toCSV() {
		return "Grenade," + super.toCSV() + "," + power + "," + delay + "," + isIncendiary;
	}
	
	@Override 
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		String causeFire;
		
		if (isIncendiary) {
			causeFire = "Sets everything on fire";
		} else {
			causeFire = "It burns, but nothing burst into flames";
		}
		
		return "Power: " + power + " | Delay before detonation: " + delay + " | Incendiary: " + causeFire
				+ "\nMaintenance: " + obtainMaintenance()
				+ "\nGuarantee: " + calculateGuarantee()
				+ "\nIncendiary protocol: " + incendiaryProtocol()
				+ "\nPercentage of magic that the grenade consumes: " + magicConsumed()
				+ "\nPossible maximum range: " + calculateRange();
	}

}
