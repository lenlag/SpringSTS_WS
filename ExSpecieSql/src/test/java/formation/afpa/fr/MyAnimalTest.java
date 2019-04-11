package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import formation.afpa.fr.AnimalRepository;
import formation.afpa.fr.Specie;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class MyAnimalTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AnimalRepository repo;

	
	private long idLapinou = 0l;
	private long idRabbitRoger = 0l;
	private long idLola = 0l;

	
	@Before
	public void setUp() {
		Animal an = new Animal();
		an.setName("Lapinou");
		an.setCoatColor("white");
		an.setSex("f");
		// an.setSpecie(new Specie(null, "Lapin", "Oryctolagus cuniculus")); // ERREUR!
		// il faut d'abord sauvegarder la nouvelle Specie, et après l'attribuer à un
		// animal
		Specie sp = new Specie(null, "Lapin", "Oryctolagus cuniculus");
		entityManager.persist(sp);
		an.setSpecie(sp);
		idLapinou = (long) entityManager.persistAndGetId(an);

		an = new Animal();
		an.setName("Rabbit Roger");
		an.setCoatColor("gray");
		an.setSex("m");
		an.setSpecie(sp);
		idRabbitRoger = (long) entityManager.persistAndGetId(an);

		an = new Animal();
		an.setName("Lola Bunny");
		an.setCoatColor("gray");
		an.setSex("f");
		an.setSpecie(sp);
		idLola = (long) entityManager.persistAndGetId(an);
	}

	@Test
	public void findList() {
		String name = "Lapinou";
		assertEquals(repo.findByName(name).size(), 1);
	}

	@Test
	public void findByName() {
		String name = "Rabbit Roger";
		List<Animal> list = repo.findByName(name);
		assertEquals(list.size(), 1);

		Animal testAnimal = list.get(0);
		assertEquals(testAnimal.getName(), name);
	}

	@Test
	public void findDistinctByCoatColor() {
		String coatColor = "gray";
		List<Animal> list = repo.findDictinctBycoatColor(coatColor);
		assertEquals(list.size(), 2);
	}

	@Test
	public void findBySexOrName() {
		String sex = "m";
		String name = "Lola Bunny";
		List<Animal> list = repo.findBySexOrName(sex, name);
		assertEquals(list.size(), 2); // 1m + 1 Lola Bunny

		String name2 = "";
		list = repo.findBySexOrName(sex, name2);
		assertEquals(list.size(), 1); // car 1m

		String sex2 = "f";
		list = repo.findBySexOrName(sex2, name2);
		assertEquals(list.size(), 2); // car 2f;

		String name3 = "Hare";
		String sex3 = "n";
		list = repo.findBySexOrName(sex3, name3);
		assertEquals(list.size(), 0);

	}

	@Test
	public void insert() {
		Animal an = new Animal();
		an.setName("Lucky");
		an.setSex("f");

		Specie sp = new Specie(null, "Chat", "Felis Catus");
		entityManager.persist(sp);
		an.setSpecie(sp);

		repo.save(an);
		assertEquals(repo.count(), 4);

	}
	
	@Test
	public void update() {
		
		try {
			String newName = "Bugs Bunny";
			Animal an1 = repo.findById(idRabbitRoger).get();
			
			assertNotNull(an1);
			assertNotEquals(newName, an1.getName());
			
			an1.setName(newName);
			repo.save(an1);
			
			Animal animalFromDB = repo.findById(an1.getId()).get();
			assertEquals(animalFromDB.getName(), newName);
			
		} catch (Exception e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	public void delete() {
		
		try {
			Animal an = repo.findById(idLola).get();
			Animal an2 = repo.findById(idLapinou).get();
			
			assertNotNull(an);
			assertNotNull(an2);
			
			repo.delete(an);
			repo.delete(an2);
			
			assertEquals(repo.count(), 1);
		/*	
			try {
				repo.findById(an.getId()).get();
				assertTrue(false);
				
				repo.findById(an2.getId()).get();
				assertTrue(false);	
				
			} catch (Exception e) {
				assertTrue(true); // car ces anim n'existent plus après la supp
			}
		*/
			
			assertFalse(repo.findById(idLola).isPresent());
			assertFalse(repo.findById(idLapinou).isPresent());
			
		} catch (Exception e) {
			assertTrue(false); // car ces ids existent avant la supp
		}
		 
	}

}
