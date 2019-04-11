package fr.afpa.formation.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.afpa.formation.entity.Person;

@Component
public class PersonDao {
	
	private List<Person> personList = new ArrayList<>();
	
	public List<Person> list() {
		personList.add(new Person("Natalia", "MATHIEU"));
		personList.add(new Person("Kiab", "THAO"));
		personList.add(new Person("Olivier", "BUJEAUD"));
		personList.add(new Person("Vona", "CHUM"));
		
		return personList;
		
	}

}
