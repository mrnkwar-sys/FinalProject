package mainclasses;

import exceptions.*;
import interfaces.ConsumableMagic;

public class Gun extends Weapon implements ConsumableMagic {
	private int power;
	private noisy noise;
	
	enum noisy {
		SILENT, NOISY
	}

	public Gun(int id, String name, double price, int damage, dangerous danger, boolean isMagical, int guarantee,
			int power, noisy noise) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical, guarantee);
		setPower(power);
		setNoise(noise);
	}
	
	public Gun() {
		
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) throws OutOfRangeException {
		if (power > 0 && power <= 90) {
			this.power = power;
		} else {
			throw new OutOfRangeException();
		}
	}

	public noisy getNoise() {
		return noise;
	}

	public void setNoise(noisy noise) {
		this.noise = noise;
	}
	
	@Override 
	public String obtainMaintenance() {
		return "You really should read the manual. There are too many steps to explain in a single sentence.";
	}
	
	@Override
	public void calculateGuarantee() {
		try {
			if (super.getDanger() == Weapon.dangerous.SAFE) {
				super.setGuarantee(1);
			} else if (super.getDanger() == Weapon.dangerous.DANGEROUS) {
				super.setGuarantee(3);
			} else {
				super.setGuarantee(2);
			}
		} catch (NegativeNumberException e) {
			System.out.println(e.toString());
		}
	}
	
	public String showLevel() {
		String levelNoise;
		
		if (noise == noisy.NOISY) {
			levelNoise = "Quiet as a cat's step";
		} else {
			levelNoise = "Noisy like exploding fireworks";
		}
		
		return levelNoise;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		return "Power: " + power + " | Level of noise it produces: " + noise.name();
	}
}
