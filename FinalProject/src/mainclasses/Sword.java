package mainclasses;

import exceptions.*;
import interfaces.Carryable;

public class Sword extends Weapon implements Carryable {
	private Hands type;
	private TypeWeight weight;
	
	//Standard constructor
	public Sword(int id, String name, double price, int damage, Dangerous danger, boolean isMagical,
			Hands type, TypeWeight weight) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical);
		setType(type);
		setWeight(weight);
	}
	
	//Empty constructor
	public Sword() {
		
	}
	
	//Copy constructor
	public Sword(Sword anotherSword) throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(anotherSword);
		setType(anotherSword.type);
		setWeight(anotherSword.weight);
	}

	public Hands getType() {
		return type;
	}

	public void setType(Hands type) {
		this.type = type;
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
		return "Steel your heart, sharpen its edge";
	}
	
	@Override 
	public int calculateGuarantee() {
		int guarantee;
		
		if (super.getDanger() == Dangerous.SAFE) {
			guarantee = 3;
		} else if (super.getDanger() == Dangerous.DANGEROUS) {
			guarantee = 4;
		} else {
			guarantee = 5;
		}
		
		return guarantee;
	}
	
	/**
	 * Based on its weight, it tells the user if it will be easier or not to draw the sword
	 * @return String with a recommendation
	 */
	public String speedDraw() { //draw == desenfundar
		String draw;
		
		if (weight == TypeWeight.HEAVY) {
			draw = "You better be agile if you want to be fast";
		} else {
			draw = "Heads will roll before you even blink";
		}
		
		return draw;
	}
	
	/**
	 * Based on its damage (and assuming that a higher damage means better materials), 
	 * it shows if the sword can easily block an attack without breaking or ruining it
	 * @return String with a recommendation
	 */
	public String blockAttacks() {
		String blocked;
		
		if (super.getDamage() <= 5) {
			blocked = "Efficient, but be careful not to ask for more than it can deliver";
		} else {
			blocked = "This one always delivers";
		}
		
		return blocked;
	}
	
	/**
	 * Based on its type, it tells the user if available to use a shield with the sword
	 * @return String ith recommendation
	 */
	public String recommendShield() {
		String shield;
		
		if (type == Hands.ONE_HAND) {
			shield = "Go on and use it; better safe than sorry";
		} else if (type == Hands.HAND_AND_A_HALF) {
			shield = "I mean, if you find the way to do it, try it";
		} else {
			shield = "You have more than two hands??!!";
		}
		
		return shield;
	}
	
	@Override
	public String toCSV() {
		return "Sword," + super.toCSV() + "," + type.toString() + "," + weight.toString();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		String normalString = type.name().replace("_", " ");
		
		return "Type: " + normalString + " Weight: " + weight.name()
		+ "\nMaintenance: " + obtainMaintenance()
		+ "\nGuarantee: " + calculateGuarantee()
		+ "\nDraw speed: " + speedDraw()
		+ "\nAbility to block attacks: " + blockAttacks()
		+ "\nShield recommendation: " + recommendShield();
	} 
}
