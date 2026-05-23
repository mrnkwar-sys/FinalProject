package exceptions;

public class NotEnoughMembersException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "A union must have, at least, 5 members to be considered a union";
	}

}
