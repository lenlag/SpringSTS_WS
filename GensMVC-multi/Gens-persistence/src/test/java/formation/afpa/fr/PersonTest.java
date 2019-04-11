package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.Person;
import formation.afpa.fr.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class PersonTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PersonRepository repo;

	// @BeforeClass=> chargé une fois pour toute la classe de TU, càd pas avant
	// chaque
	// test. Cela permettera de tester, par exemple, via id des objects(ils ne
	// sereont pas incrementés, mais resteront constants)

	// @Before=> chargé avant chaque test

	private Long idLastPerson = 0l;
	int initSize = 3;
	String firstName = "Natalia";
	Integer newAge = 18;

	@Before
	public void setUp() {
		Person natalia = new Person();
		natalia.setFirstName("Natalia");
		natalia.setLastName("Mathieu");
		natalia.setAge(29);

		entityManager.persist(natalia);

		Person camille = new Person();
		camille.setFirstName("Camille");
		camille.setLastName("Guyot");
		camille.setAge(34);

		entityManager.persist(camille);

		Person katja = new Person();
		katja.setFirstName("Katja");
		katja.setLastName("Grunert");
		katja.setAge(31);

		entityManager.persist(katja);

		idLastPerson = (Long) entityManager.persistAndGetId(katja);

	}

	@Test
	public void findAll() {
		assertEquals(repo.count(), initSize);
		
	}

	@Test
	public void findByFirstName() {
		try {
			assertEquals(repo.findByFirstName(firstName).size(), 1);

			List<Person> foundByFName = repo.findByFirstName(firstName);

			assertEquals(foundByFName.get(0).getLastName(), "Mathieu");
		} catch (Exception e) {
			Assert.fail("The exception was not expected");
		}
	}

	@Test
	public void create() {
		Person test = new Person();
		test.setFirstName("test");
		test.setLastName("last name");
		test.setAge(77);
		repo.save(test);

		assertEquals(repo.count(), initSize + 1);
	}

	@Test
	public void update() {
		try {
			Person p = repo.findById(idLastPerson).get();
			assertNotNull(p);
			assertNotEquals(p.getAge(), newAge);

			p.setAge(newAge);
			repo.save(p);
			assertEquals(p.getAge(), newAge);

		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void delete() {
		try {
			Person p = repo.findById(idLastPerson).get();

			repo.delete(p);
			assertEquals(repo.count(), initSize - 1);
			assertFalse(repo.findById(idLastPerson).isPresent());
		} catch (Exception e) {
			Assert.fail("The exception was not expected");
		}
	}

}
