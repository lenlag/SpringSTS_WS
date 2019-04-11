package formation.afpa.fr.Exception;

public class PersonFirstNameOrLastNameNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonFirstNameOrLastNameNotValidException() {
		super("The first name and/or the last name is invalid");
	}

}
