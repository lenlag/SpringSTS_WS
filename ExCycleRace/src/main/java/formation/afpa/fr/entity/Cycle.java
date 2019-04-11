package formation.afpa.fr.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Cycle")
public class Cycle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "brand", nullable = false, length = 25)
	private String brand;
	
	@Column(name = "model", nullable = false, length = 25)
	private String model;
	
	@Column(name="nbGears", nullable = false, length = 2)
	private int nbGears;
	
	@Column(name="nbGearPlates", nullable = false, length = 2)
	private int nbGearPlates;
	
	
	@ManyToOne(cascade = { CascadeType.PERSIST})
	@JoinColumn(name="id_racer")
	private Racer racer;
	
	
	public Cycle() {
		
	}


	public Cycle(Long id, String brand, String model, int nbGears, int nbGearPlates) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.nbGears = nbGears;
		this.nbGearPlates = nbGearPlates;
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getNbGears() {
		return nbGears;
	}


	public void setNbGears(int nbGears) {
		this.nbGears = nbGears;
	}


	public int getNbGearPlates() {
		return nbGearPlates;
	}


	public void setNbGearPlates(int nbGearPlates) {
		this.nbGearPlates = nbGearPlates;
	}


	
	public Racer getRacer() {
		return racer;
	}


	public void setRacer(Racer racer) {
		this.racer = racer;
	}


	@Override
	public String toString() {
		return "Cycle : " + brand + " " +  model  + " " + nbGears + " " + nbGearPlates;
	}
	
		
}
