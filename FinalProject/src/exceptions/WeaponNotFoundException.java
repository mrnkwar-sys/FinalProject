package exceptions;

public class WeaponNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "The weapon with the ID you entered could not be found; please make sure the ID is correct";
	}

}
