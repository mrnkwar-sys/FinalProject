package interfaces;

/**
 * Calculate the percentage of magic consumed by a weapon
 * @author Raquel Nkwar
 * @version 1.0
 */
public interface ConsumableMagic {
	
	/**
	 * Calculate the percentage of magic consumed by a weapon based on its damaged
	 * @return The percentage
	 */
	public int magicConsumed();
}
