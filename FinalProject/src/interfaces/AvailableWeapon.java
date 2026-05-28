package interfaces;

import mainclasses.Dangerous;
import mainclasses.LevelExperience;
import mainclasses.Weapon;

public interface AvailableWeapon {
	LevelExperience getIndependentExperience();
	
	/**
	 * Checks if the experience of the client coincides with the danger of the weapon
	 * @param anyWeapon 
	 * @return True if SAFE matches NOVICE, MEDIUM matches SAFE or DANGEROUS, and EXPERT matches SAFE, DANGEROUS or VERY_DANGEROUS
	 */
	default boolean appropiateExperience(Weapon anyWeapon) {
//		boolean buyable = false;
		
//		if ("SAFE".equals(anyWeapon.getDanger().toString()) && this.getIndependentExperience() == LevelExperience.NOVICE) {
//			buyable = true;
//		} else if (("DANGEROUS".equals(anyWeapon.getDanger().toString()) && this.getIndependentExperience() == LevelExperience.MEDIUM) ||
//				("SAFE".equals(anyWeapon.getDanger().toString()) && this.getIndependentExperience() == LevelExperience.MEDIUM)) {
//			buyable = true;
//		} else if ("VERY_DANGEROUS".equals(anyWeapon.getDanger().toString()) && this.getIndependentExperience() == LevelExperience.EXPERT ||
//				"DANGEROUS".equals(anyWeapon.getDanger().toString()) && this.getIndependentExperience() == LevelExperience.EXPERT ||
//				"SAFE".equals(anyWeapon.getDanger().toString()) && this.getIndependentExperience() == LevelExperience.EXPERT) {
//			buyable = true;
//		}
		
		LevelExperience exp = this.getIndependentExperience();
		
		Dangerous danger = anyWeapon.getDanger();
		
//		if (exp == LevelExperience.EXPERT) {
//			buyable = true;
//		} 
//		
//		if (exp == LevelExperience.MEDIUM) {
//			buyable = (danger == Dangerous.SAFE || danger == Dangerous.DANGEROUS);
//		}
//		
//		if (exp == LevelExperience.NOVICE) {
//			buyable = danger == Dangerous.SAFE;
//		}
//		
//		return buyable;
		
		return switch (exp) {
        case EXPERT -> true;
        case MEDIUM -> (danger == Dangerous.SAFE || danger == Dangerous.DANGEROUS);
        case NOVICE -> (danger == Dangerous.SAFE);
        default -> false;
		};
	}
}
