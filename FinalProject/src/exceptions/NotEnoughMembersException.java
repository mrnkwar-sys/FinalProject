package exceptions;

/**
 * An exception that is thrown when a union does not have the required number of members to be considered a union
 * @author Raquel Nkwar
 * @version 1.0
 */
public class NotEnoughMembersException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "A union must have, at least, 5 members to be considered a union";
	}

}
