package interfaces;

/**
 * Calulates the range of a weapon that can be thrown
 * @author Raquel Nkwar
 * @version 1.0
 */
public interface Throwable {
	
	/**
	 * Calculates the range the weapon can reach when thrown, based on its power or weight 
	 * @return The range (meters)
	 */
	public int calculateRange();
}
