package formation.afpa.fr.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "Racer")
public class Racer extends Person {
	
	/*	// id comes from the parent class
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	*/
	@OneToMany(mappedBy="racer")
	private Set<Cycle> cycles;
	
	@ManyToOne(cascade = { CascadeType.PERSIST})		
	@JoinColumn(name="id_team")
	private Team team;
	
	public Racer() {
		
	}

	public Racer(Long id, String firstName, String lastName, Set<Cycle> cycles) {
		super(id, firstName, lastName);
		this.cycles = cycles;
	
	}
/*
	public Long getId() { // id comes from the parent class
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
*/
	public Set<Cycle> getCycles() {
		return cycles;
	}

	public void setCycles(Set<Cycle> cycles) {
		this.cycles = cycles;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return super.toString() + ", cycles : " + cycles;
	}
	
	

}
