package formation.afpa.fr.Exception;

public class PersonLastNameNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonLastNameNotValidException() {
		super("The last name is invalid");
	}

}
