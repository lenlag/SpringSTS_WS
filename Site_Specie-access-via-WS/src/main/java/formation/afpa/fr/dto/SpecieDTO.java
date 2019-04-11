package formation.afpa.fr.dto;


public class SpecieDTO { //copie d'une classe Specie sans annotations, sert à transférer l'objet

	private Long id;
	
	private	String commonName;
	
	private	String latinName;
	
	
	public SpecieDTO(Long id, String commonName, String latinName) {
		this.id = id;
		this.commonName = commonName;
		this.latinName = latinName;
	}

	
	public SpecieDTO () {
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCommonName() {
		return commonName;
	}


	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}


	public String getLatinName() {
		return latinName;
	}


	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}


	@Override
	public String toString() {
		return id + ", " + commonName + ", " + latinName;
	}
	
	
	
	
}
