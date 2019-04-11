package formation.afpa.fr.Exception;

public class SpecieNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpecieNotValidException () {
		super("Specie is invalid");

	}
	
	public SpecieNotValidException (String message) {
		super(message);
	}
	
}
