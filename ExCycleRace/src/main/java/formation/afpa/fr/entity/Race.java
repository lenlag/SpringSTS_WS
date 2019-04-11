package formation.afpa.fr.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // parent class
@Table(name = "Race")
public class Race {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name="Name", nullable = false, length = 25)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="id_Location")
	private Location location;

	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(name="team_race",joinColumns=@JoinColumn(name="TEAM_ID"),inverseJoinColumns=@JoinColumn(name="RACE_ID"))
	private Set<Team> teams;
	
	public Race() {
		
	}

	public Race(Long id, String name, Location location, Set<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.teams = teams;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return 
				"\n\t Race : " + 
	
				"\n\t Name => " + name  + 
				"\n\t Location => " +  location +  
				
				"\n\t Teams => " + teams;
	}
	
	
	
	
}
