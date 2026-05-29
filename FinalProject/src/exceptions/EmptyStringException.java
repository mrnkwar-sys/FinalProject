package exceptions;

/**
 * An exception that is thrown when an empty string or a string consisting solely of spaces is entered
 * @author Raquel Nkwar
 * @version 1.0
 */
public class EmptyStringException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "A word must be introduced";
	}
}
