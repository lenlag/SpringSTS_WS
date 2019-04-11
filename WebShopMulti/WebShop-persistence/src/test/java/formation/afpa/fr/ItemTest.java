package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class ItemTest extends TestFather {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BoutiqueItemRepository repo;
	
	@Test
	public void List() {
		try {
			List<BoutiqueItem> list = (List<BoutiqueItem>) repo.findAll();
			assertNotNull(list);
			assertEquals(list.size(), 5);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void findById() {
		try {
			String label = "jean";
			BoutiqueItem item1 = repo.findById(idLastItem).get();
			
			assertNotNull(item1);
			assertEquals(item1.getLabel(), label);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void add() {
		try {
			BoutiqueItem item1 = new BoutiqueItem(null, "g", "socks", 25);
			repo.save(item1);
			
			assertNotNull(item1);
			assertEquals(repo.count(), 6);
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void update() {
		try {
			assertNotNull(idLastItem);
			
			String newLabel = "jupe";
			BoutiqueItem item1 = repo.findById(idLastItem).get();
			
			assertNotEquals(item1.getLabel(), newLabel);
			
			item1.setLabel(newLabel); //we modify the object
			repo.save(item1); // and we should save the object
			// but the test will work w/o saving the object, as well! weird, should be related to the TEST h2 database specificity, as in the real database it would fail
			
			
			BoutiqueItem itemFromDB = repo.findById(idLastItem).get();
			assertEquals(itemFromDB.getLabel(), newLabel);
			
			
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void delete() {
		BoutiqueItem item1 = new BoutiqueItem(null, "k", "test", 5);
		repo.save(item1);
		assertEquals(repo.count(), 6);
		
		repo.delete(item1);
		assertEquals(repo.count(), 5);

		
	}
	
	
	
}
