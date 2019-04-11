package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.entity.MyOrder;
import formation.afpa.fr.repository.MyOrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class MyOrderTest extends TestFather {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MyOrderRepository repo;
/*
	@Test
	public void List() {
		try {
			List<MyOrder> list = (List<MyOrder>) repo.findAll();
			assertNotNull(list);
			assertEquals(list.size(), 5);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	

	@Test
	public void add() {
		try {
			MyOrder newOrder = new MyOrder(null, new Date());
			repo.save(newOrder);

			assertNotNull(newOrder);
			assertEquals(repo.count(), 6);

		} catch (Exception e) {
			assertTrue(false);
		}
	}


	
	@Test
	public void delete() {
		MyOrder newOrder = new MyOrder(null, new Date());
		repo.save(newOrder);		
		
		repo.delete(repo.findById(6l).get());
		assertEquals(repo.count(), 5);

		
	}
	
*/
}
