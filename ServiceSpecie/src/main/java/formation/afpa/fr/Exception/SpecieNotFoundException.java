package formation.afpa.fr.Exception;

public class SpecieNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpecieNotFoundException() {
		super("Specie is not found");
	}
	
	public SpecieNotFoundException(String message) {
		super(message);
	}

}
