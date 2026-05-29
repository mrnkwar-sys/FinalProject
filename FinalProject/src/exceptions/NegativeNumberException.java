package exceptions;

/**
 * An exception that is thrown when a negative number is entered
 * @author Raquel Nkwar
 * @version 1.0
 */
public class NegativeNumberException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "The number introduced muts have a positive value";
	}

}
