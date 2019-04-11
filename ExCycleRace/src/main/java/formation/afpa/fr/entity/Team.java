package formation.afpa.fr.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Team")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name="name", nullable = false, length = 25)
	private String name;
	
	@OneToOne
	@JoinColumn(name="manager")
	private Person manager;
	
	@OneToMany(mappedBy="team")
	private Set<Racer> racers;
	
	@ManyToMany(mappedBy="teams")
	private Set<Race> races;

	
	public Team() {
		
	}


	public Team(Long id, String name, Person manager, Set<Racer> racers) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.racers = racers;
	
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


	public Person getManager() {
		return manager;
	}


	public void setManager(Person manager) {
		this.manager = manager;
	}


	public Set<Racer> getRacers() {
		return racers;
	}


	public void setRacers(Set<Racer> racers) {
		this.racers = racers;
	}


	public Set<Race> getRaces() {
		return races;
	}


	public void setRaces(Set<Race> races) {
		this.races = races;
	}


	@Override
	public String toString() {
		return "Team name : " + name  + ", manager : " +  manager + ", racers : " + racers;
	}
	
	
	
}
