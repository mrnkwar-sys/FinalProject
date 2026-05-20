package mainclasses;

import exceptions.*;
import interfaces.Throwable;

public class Hammer extends Weapon implements Throwable {
	private int strengthRequired;
	private TypeWeight weight;
	
	public Hammer(int id, String name, double price, int damage, dangerous danger, boolean isMagical, int guarantee,
			int strengthRequired, TypeWeight weight)
			throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical, guarantee);
		this.strengthRequired = strengthRequired;
		this.weight = weight;
	}
	
	public Hammer() {
		
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
	
	public String showShockWave() {
		
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		return "Strength Required: " + strengthRequired + " | Weight: " + weight.name();
	}
}
