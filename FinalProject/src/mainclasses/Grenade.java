package mainclasses;

import interfaces.Throwable;
import exceptions.*;
import interfaces.ConsumableMagic;

public class Grenade extends Weapon implements Throwable, ConsumableMagic {
	private int power;
	private int delay;
	private boolean isIncendiary;
	
	public Grenade(int id, String name, double price, int damage, dangerous danger, boolean isMagical, int guarantee,
			int power, int delay, boolean isIncendiary)
			throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical, guarantee);
		setPower(power);
		setDelay(delay);
		setIncendiary(isIncendiary);
	}
	
	public Grenade() {
		
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
	public void calculateGuarantee() {
		try {
			if (super.getDanger() == Weapon.dangerous.SAFE) {
				super.setGuarantee(1);
			} else if (super.getDanger() == Weapon.dangerous.DANGEROUS) {
				super.setGuarantee(2);
			} else {
				super.setGuarantee(3);
			}
		} catch (NegativeNumberException e) {
			System.out.println(e.toString());
		}
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
		
		return "Power: " + power + " | Delay before detonation: " + delay + " | Incendiary: " + causeFire;
	}

}
