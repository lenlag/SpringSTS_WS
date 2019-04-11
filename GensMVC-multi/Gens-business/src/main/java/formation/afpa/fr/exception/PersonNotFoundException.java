package formation.afpa.fr.exception;

public class PersonNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException() {
		super("Person is not found");
	}
	
	public PersonNotFoundException(String message) {
		super(message);
	}

}
