package formation.afpa.fr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="animal")
public class Animal {
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
			)
			@GenericGenerator(
			name = "native",
			strategy = "native"
			)
	Long id;
	
	// id_specie
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_specie")
	Specie specie; // Many To One, car 1 animal = 1 specie; 1 specie => plusieurs animaux
	
	
	@Column(name="name")
	String name;
	
	@Column(name="coatcolor")
	String coatColor;
	
	@Column(name="sex")
	String sex;
	
	
	
	public Animal() {
		
	}
	
	
	public Animal(Long id, Specie specie, String name, String coatColor, String sex) {
		this.id = id;
		this.specie = specie;
		this.name = name;
		this.coatColor = coatColor;
		this.sex = sex;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}





	public Specie getSpecie() {
		return specie;
	}


	public void setSpecie(Specie specie) {
		this.specie = specie;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCoatColor() {
		return coatColor;
	}


	public void setCoatColor(String coatColor) {
		this.coatColor = coatColor;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	@Override
	public String toString() {
		return id + ",[" + specie.toString() + "], " + name + "," + coatColor + ", " + sex;
	}
	
	
	
}
