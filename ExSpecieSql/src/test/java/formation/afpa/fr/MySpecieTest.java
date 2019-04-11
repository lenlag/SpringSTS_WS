package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import formation.afpa.fr.SmallApp;
import formation.afpa.fr.Specie;
import formation.afpa.fr.SpecieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class MySpecieTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SpecieRepository repo;

	// @BeforeClass chargé une fois pour toute la classe de TU, càd pas avant chaque
	// test. Cela permettera de tester, par exemple, via id des objects(ils ne
	// sereont pas incrementés, mais resteront constants)

	// @Before chargé avant chaque test

	private long id = 0l;

	@Before
	public void setUp() {
		Specie sp = new Specie();
		sp.setCommonName("COMMON");
		sp.setLatinName("LATIN");
		entityManager.persist(sp);
		
		sp = new Specie();
		sp.setCommonName("lapin");
		sp.setLatinName("LAPINUS");
		id = (long) entityManager.persistAndGetId(sp); // pour extraire l'id d'un objet créé
	}

	@Test
	public void findList() {
		assertEquals(repo.findByLatinName("LATIN").size(), 1); // càd dans la liste il y a 1 élément avec latin_name
																// "LATIN"
	}

	@Test
	public void findByLatinName() {
		String latinName = "LAPINUS";
		List<Specie> list = repo.findByLatinName(latinName);
		assertEquals(list.size(), 1);
		Specie sp = list.get(0);
		assertEquals(sp.getLatinName(), latinName);

	}

	@Test
	public void findBeginList() {
		assertEquals(repo.findByBeginLatinName("LA").size(), 2); //2 éléments commençants par "LA" 
	}

	@Test
	public void insert() {
		Specie sp = new Specie();
		sp.setCommonName("Guzzu");
		sp.setLatinName("GuzzuNus");
		repo.save(sp);
		assertEquals(3, repo.count());
	}

	@Test
	public void update() {
		String newName = "New commmon name";
		List<Specie> list = repo.findByCommonName("COMMON");
		
		assertEquals(list.size(), 1); // pour tester s'il y en a bien 1 element
		
		Specie sp = list.get(0); // on extrait le 1er élément
		sp.setCommonName(newName);
		repo.save(sp);
		
		assertEquals(sp.getCommonName(), newName);
	}
	
	
	@Test
	public void update2() {
		String newLatinName = "Catus Felis";
		Specie sp = repo.findById(id).get();
		
		assertNotNull(sp);
		assertNotEquals(sp.getLatinName(), newLatinName);
		
		sp.setLatinName(newLatinName);
		repo.save(sp);
		
		Specie specieFromDB = repo.findById(sp.getId()).get();
		
		assertEquals(specieFromDB.getLatinName(), newLatinName);
				
		
	}
	

	@Test
	public void delete() { // delete via id !
		try { 		//mettre try/catch à chaque test contenant findById, car si id n'existe pas, ça plantera le prog
		assertNotNull(id);
		repo.delete(repo.findById(id).get());
		assertEquals(repo.count(), 1);

		try {
			repo.findById(id).get(); // pour tester que l'élem avec cet id n'existe plus
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true); // pour planter le test en cas d'erreur
		}
		
		} catch (Exception e) {
			assertTrue(false);
		}

	}

	@Test
	public void delete2() {
		try {
			Specie sp = repo.findById(id).get();
			assertNotNull(sp);
			repo.delete(sp);
/*
			try {
				repo.findById(id).get();
				assertTrue(false);
			} catch (Exception e) {
				assertTrue(true);
			}
*/
			
			assertFalse(repo.findById(id).isPresent());
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}
