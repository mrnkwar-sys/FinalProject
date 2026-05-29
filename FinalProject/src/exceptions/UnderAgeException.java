package exceptions;

/**
 * An exception that is thrown when the customer does not meet the minimum age requirement
 * @author Raquel Nkwar
 * @version 1.0
 */
public class UnderAgeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "If you are under 18, you cannot access the store";
	}

}
