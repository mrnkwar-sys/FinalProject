package exceptions;

/**
 * An exception that is thrown when a number outside the specified range is entered
 * @author Raquel Nkwar
 * @version 1.0
 */
public class OutOfRangeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "The value introduced must be within the specified range";
	}

}
