package formation.afpa.fr.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	
	@Column(name = "Name", nullable = false, length = 255)
	private String name;
	
	@Column(name = "Code", nullable = false, length = 10)
	private String code;
	
	@OneToMany(cascade= {CascadeType.PERSIST},
			mappedBy="country") 						//remove EAGER & put @Transactional into MainTest
	private Set<Location> locations;
	
	public Country() {
		
	}

	public Country(Long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Set<Location> getLocations() {
		return locations;
	}


	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}


	@Override
	public String toString() {
		return name;
	}
	
	
	
}
