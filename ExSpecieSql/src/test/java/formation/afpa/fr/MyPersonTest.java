package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.Animal;
import formation.afpa.fr.Person;
import formation.afpa.fr.PersonRepository;
import formation.afpa.fr.Specie;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class MyPersonTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PersonRepository repo;
	
	private long idAnge = 0l;
	private long idNatalia = 0l;
	private long idCamille = 0l;
		
	
	@Before
	public void setUp() {
		Specie sp = new Specie(null, "Lapin", "Oryctolagus cuniculus");
		entityManager.persist(sp);
		
		Animal an = new Animal();
		an.setName("Rabbit Roger");
		an.setCoatColor("gray");
		an.setSex("m");
		an.setSpecie(sp);
	//	entityManager.persist(an);
		List <Animal> animalList = new ArrayList<>();
		animalList.add(an);
		
		Animal an2 = new Animal();
		an2.setName("Lola Bunny");
		an2.setCoatColor("gray");
		an2.setSex("f");
		an2.setSpecie(sp);
	//	entityManager.persist(an2);
		List <Animal> animalList2 = new ArrayList<>();
		animalList2.add(an2);
		
		
		Person p1 = new Person();
		p1.setFirstName("Natalia");
		p1.setLastName("MARTIN");
		p1.setAge(29);
		p1.setAnimal(animalList);
		idNatalia = (long)entityManager.persistAndGetId(p1);
		
		Person p2 = new Person();
		p2.setFirstName("Camille");
		p2.setLastName("GUYOT");
		p2.setAge(33);
		p2.setAnimal(animalList2);
		idCamille = (long)entityManager.persistAndGetId(p2);
		
		Person p3 = new Person();
		p3.setFirstName("Angelina");
		p3.setLastName("MARTIN");
		p3.setAge(2);
		p3.setAnimal(animalList);
		idAnge = (long)entityManager.persistAndGetId(p3);
			
	}
	
	@Test
	public void findList() {
		
		List <Person> persons = (List<Person>) repo.findAll();
		assertNotNull(persons);
		assertEquals(persons.size(), 3);
	}
	
	@Test
	public void findByLastName() {
		String lastName = "GUYOT";
		List <Person> list = repo.findByLastName(lastName);
	
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getLastName(), lastName);
		
	}
	
	@Test
	public void findDictinctByLastName() {
		String lastName = "MARTIN";
		List <Person> persons = repo.findDictinctByLastName(lastName);
		assertEquals(persons.size(), 2);
		
	}
	
	@Test
	public void findByFirstNameOrLastName() {
		String firstName = "Camille";
		String lastName = "GUYOT";
		String lastName2 = "MARTIN";
		String lastName3 = "";
		
		
		List <Person> list = repo.findByFirstNameOrLastName(firstName, lastName3);
		assertNotNull(list);
		assertEquals(list.size(), 1);
		
		
		list = repo.findByFirstNameOrLastName(firstName, lastName);
		assertNotNull(list);
		assertEquals(list.size(), 1);
		
		list = repo.findByFirstNameOrLastName(firstName, lastName2);
		assertNotNull(list);
		assertEquals(list.size(), 3);
	}
	
	@Test
	public void update() {
		try {
			
			String newLastName = "DIAZ";
			Person p = repo.findById(idCamille).get();
			assertNotNull(p);
			assertNotEquals(p.getLastName(), newLastName);
						
			p.setLastName(newLastName);
			repo.save(p);
			assertEquals(p.getLastName(), newLastName);
			
			Person personFromDB = repo.findById(p.getId()).get();
			assertEquals(personFromDB.getLastName(), newLastName);
			
						
		} catch (Exception e) {
			assertTrue(false); // car ces ids existent avant la supp
		}
		
	}
	
	@Test
	public void delete() {
		
		try {
			Person p = repo.findById(idNatalia).get();
			assertNotNull(p);
			
			repo.delete(p);
			assertEquals(repo.count(), 2);
		/*	
			try {
				repo.findById(p.getId()).get();
				assertTrue(false);
			} catch (Exception e) {
				assertTrue(true); // car cette personne n'existe plus après la supp
			}
		*/
		  
		  assertFalse(repo.findById(idNatalia).isPresent());
		 
			
		} catch (Exception e) {
			assertTrue(false); // car ces ids existent avant la supp
		}
	}
	
}
