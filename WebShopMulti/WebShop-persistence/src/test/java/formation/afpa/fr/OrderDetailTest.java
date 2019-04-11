package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
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
import formation.afpa.fr.entity.OrderItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;
import formation.afpa.fr.repository.MyOrderRepository;
import formation.afpa.fr.repository.OrderItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class OrderDetailTest extends TestFather {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BoutiqueItemRepository itemRepo;

	@Autowired
	private OrderItemRepository ODRepo;

	@Autowired
	private MyOrderRepository myOrderRepo;
/*
	@Test
	public void List() {
		try {
			List<BoutiqueItem> list = (List<BoutiqueItem>) ODRepo.findAll();
			assertNotNull(list);
			assertEquals(list.size(), 5);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void findById() {
		try {
			int quantity = 9;
			OrderDetail orderDetail1 = ODRepo.findById(idLastOrderDetail).get();
			orderDetail1.setQuantity(quantity);
			assertNotNull(orderDetail1);
			assertEquals(orderDetail1.getQuantity(), quantity);

		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void add() {
		try {
			OrderDetail orderDetail1 = new OrderDetail();
			orderDetail1.setItem(itemRepo.findById(1l).get());
			orderDetail1.setOrder(myOrderRepo.findById(3l).get());
			orderDetail1.setUnitPrice(itemRepo.findById(1l).get().getPrice());
			orderDetail1.setQuantity(7);
			ODRepo.save(orderDetail1);

			assertNotNull(orderDetail1);
			assertEquals(ODRepo.count(), 6);

		} catch (Exception e) {
			// assertTrue(false);
		}
	}

	@Test
	public void update() {
		try {
			assertNotNull(idLastOrderDetail);

			int newQuantity = 4;
			OrderDetail orderDetail = ODRepo.findById(idLastOrderDetail).get();
			orderDetail.setQuantity(newQuantity);
			assertEquals(orderDetail.getQuantity(), newQuantity);
			

		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void delete() {
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setQuantity(7);
		ODRepo.save(orderDetail1);
		
		assertEquals(ODRepo.count(), 6);
		
		ODRepo.delete(orderDetail1);
		assertEquals(ODRepo.count(), 5);

		
	}
	*/
	
}
