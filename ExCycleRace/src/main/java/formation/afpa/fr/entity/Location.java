package formation.afpa.fr.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "City", nullable = false, length = 255)
	private String city;
	
	@ManyToOne
	@JoinColumn(name="id_country")
	private Country country;
	
	@OneToMany(mappedBy="location")
	private Set <Race> races;
	
	
	public Location() {
		
	}


	public Location(Long id, String city, Country country) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public Set<Race> getRaces() {
		return races;
	}


	public void setRaces(Set<Race> races) {
		this.races = races;
	}


	@Override
	public String toString() {
		return city + ", " + country;
	}



	
}
