package formation.afpa.fr.Exception;

public class AnimalNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnimalNotFoundException() {
		super("Animal is not found");
	}
	
	public AnimalNotFoundException(String message) {
		super(message);
	}

}
