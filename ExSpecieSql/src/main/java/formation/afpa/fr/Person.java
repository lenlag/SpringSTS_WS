package formation.afpa.fr;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="person")
public class Person {
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
	
	@Column(name="firstname")
	String firstName;
	
	
	@Column(name="lastname")
	String lastName;
	
	@Column(name="age")
	int age;
	
	
	//ManyToMany => car une personne peut avoir plusieurs animaux, 1 animal peut avoir plusieurs maitres => List<Animal> pour Person & List<Person> pour Animal sont possibles
	
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.ALL}) // ALL = PERSIST
	@JoinTable(name="person__animal",joinColumns=@JoinColumn(name="PERSON_ID"),inverseJoinColumns=@JoinColumn(name="ANIMAL_ID"))
	List<Animal> animal = new ArrayList<>();
	
	
	public Person() {
		
	}


	public Person(Long id, String firstName, String lastName, int age, List<Animal> animal) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.animal = animal;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public List<Animal> getAnimal() {
		return animal;
	}


	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}


	@Override
	public String toString() {
		return id + ", " + firstName + ", " + lastName + ", " + age + ", [" + animal.toString() + "]";
	}
	
	
	
	

}
