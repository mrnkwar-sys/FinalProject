package mainclasses;

import exceptions.*;
import interfaces.ConsumableMagic;

public class Wand extends Weapon implements ConsumableMagic {
	//It requires a specific kind of magic
	private boolean isExclusive;
	//If the level of bonding is low, it can be used by many people, but if it is high, it can only be used by its owner
	private int ownerBond;
	private TypeMagic magic;
	
	enum TypeMagic {
		WHITE_MAGIC, BLACK_MAGIC, ILUSIONISM, SPIRITUAL
	}

	public Wand(int id, String name, double price, int damage, Dangerous danger, boolean isMagical, int guarantee,
			boolean isExclusive, int ownerBond, TypeMagic magic)
			throws NegativeNumberException, EmptyStringException, OutOfRangeException {
		super(id, name, price, damage, danger, isMagical, guarantee);
		setExclusive(isExclusive);
		setOwnerBond(ownerBond);
		setMagic(magic);
	}

	public boolean isExclusive() {
		return isExclusive;
	}

	public void setExclusive(boolean isExclusive) {
		this.isExclusive = isExclusive;
	}

	public int getOwnerBond() {
		return ownerBond;
	}

	public void setOwnerBond(int ownerBond) throws OutOfRangeException {
		if (ownerBond >= 1 && ownerBond <= 3) {
			this.ownerBond = ownerBond;
		} else {
			throw new OutOfRangeException();
		}
	}

	public TypeMagic getMagic() {
		return magic;
	}

	public void setMagic(TypeMagic magic) {
		this.magic = magic;
	}
	
	@Override
	public String obtainMaintenance() {
		return "Polish it every day and don’t leave it on its own for too long. A wand is not only a weapon, but a companion";
	}
	
	@Override 
	public void calculateGuarantee() {
		try {
			if (super.getDanger() == Dangerous.SAFE) {
				super.setGuarantee(3);
			} else if (super.getDanger() == Dangerous.DANGEROUS) {
				super.setGuarantee(4);
			} else {
				super.setGuarantee(2);
			}
		} catch (NegativeNumberException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Suggest some spells depending on the kind of magic that the wand manages better
	 * @return String with the suggestions
	 */
	public String listPossibleSpells() {
		String spells;
		
		if (magic == TypeMagic.BLACK_MAGIC) {
			spells = "You could try put a course on someone, a love spell, to control someone's actions... you should get a black cat (just a personal advice)";
		} else if (magic == TypeMagic.ILUSIONISM) {
			spells = "Put someone into a dream, make them believe they’re living one, fulfil someone’s biggest fantasies or nightmares (is there really a limit?)";
		} else if (magic == TypeMagic.SPIRITUAL) {
			spells = "Talk to the dead, look into people’s souls, try to see their past and their future (please don’t bring anyone back to life)";
		} else {
			spells = "Heal and soothe wounds, bring abundance and prosperity to those you love, calm their fears, be the good person you are";
		}
		
		return spells;
	}
	
	@Override
	public int magicConsumed() {
		int percentageMagic;
		
		if (super.getDamage() <= 3) {
			percentageMagic = 40;
		} else if (super.getDamage() <= 5) {
			percentageMagic = 45;
		} else if (super.getDamage() <= 8) {
			percentageMagic = 50;
		} else {
			percentageMagic = 55;
		}
		
		return percentageMagic;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String toStringAdditionalData() {
		String exclusive;
		
		if (isExclusive) {
			exclusive = "Be sure to check if the wand is compatible with your type of magic";
		} else {
			exclusive = "This wand is made for everyone";
		}
		
		return exclusive + " | Level of bonding needed: " + ownerBond + " | It works better (or only) with this type of magic: " + magic.name();
	}
}
