package formation.afpa.fr.Exception;

public class AnimalSexOrNameNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnimalSexOrNameNotValidException() {
		super("The requested sex or/and name is invalid");
	}

}
