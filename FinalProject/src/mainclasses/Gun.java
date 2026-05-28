package mainclasses;

import exceptions.*;
import interfaces.ConsumableMagic;

public class Gun extends Weapon implements ConsumableMagic {
	private int power;
	private Noisy noise;

	//Standard constructor
	public Gun(int id, String name, double price, int damage, Dangerous danger, boolean isMagical,
			int power, Noisy noise) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical);
		setPower(power);
		setNoise(noise);
	}
	
	//Empty constructor
	public Gun() {
		
	}
	
	//Copy constructor
	public Gun(Gun anotherGun) throws NegativeNumberException, EmptyStringException, OutOfRangeException{
		super(anotherGun);
		setPower(anotherGun.power);
		setNoise(anotherGun.noise);
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

	public Noisy getNoise() {
		return noise;
	}

	public void setNoise(Noisy noise) {
		this.noise = noise;
	}
	
	@Override 
	public String obtainMaintenance() {
		return "You really should read the manual. There are too many steps to explain in a single sentence.";
	}
	
	@Override
	public int calculateGuarantee() {
		int guarantee; 
		
		if (super.getDanger() == Dangerous.SAFE) {
			guarantee = 1;
		} else if (super.getDanger() == Dangerous.DANGEROUS) {
			guarantee = 3;
		} else {
			guarantee = 2;
		}
		
		return guarantee;
	}
	
	public String showLevel() {
		String levelNoise;
		
		if (noise == Noisy.NOISY) {
			levelNoise = "Quiet as a cat's step";
		} else {
			levelNoise = "Noisy like exploding fireworks";
		}
		
		return levelNoise;
	}
	
	@Override
	public int magicConsumed() {
		int percentageMagic;
		
		if (super.getDamage() >= 5) {
			percentageMagic = 15;
		} else {
			percentageMagic = 7;
		}
		
		return percentageMagic;
	}
	
	@Override
	public String toCSV() {
		return "Gun," + super.toCSV() + "," + power + "," + noise.toString();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		return "Power: " + power + " | Level of noise it produces: " + noise.name()
		+ "\nMaintenance: " + obtainMaintenance()
		+ "\nGuarantee: " + calculateGuarantee()
		+ "\nLevel of noise produced by the weapon: " + showLevel()
		+ "\nPercentage of magic that the gun consumes: " + magicConsumed();
	}
}
