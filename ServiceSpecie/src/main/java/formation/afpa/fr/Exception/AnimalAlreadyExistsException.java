package formation.afpa.fr.Exception;

public class AnimalAlreadyExistsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnimalAlreadyExistsException() {
		super("Animal already exists");
	}
}
