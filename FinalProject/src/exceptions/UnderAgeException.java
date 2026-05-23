package exceptions;

public class UnderAgeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "If you are under 18, you cannot access the store";
	}

}
