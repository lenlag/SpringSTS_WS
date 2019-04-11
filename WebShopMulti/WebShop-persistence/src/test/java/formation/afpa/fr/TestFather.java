package formation.afpa.fr;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.entity.MyOrder;
import formation.afpa.fr.entity.OrderItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;
import formation.afpa.fr.repository.MyOrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class TestFather {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BoutiqueItemRepository itemRepo;

	@Autowired
	private MyOrderRepository orderRepo;

	public Long idLastItem = 0l;
	public Long idLastMyOrder = 0l;
	public Long idLastOrderDetail = 0l;

	@Before
	public void setUp() {
		initBdd();
	}

	@Test
	public void test() { // we should create at least 1 test, otherwise the prog will not compile
		assertTrue(true);
	}

	public void initBdd() {

		BoutiqueItem item1 = new BoutiqueItem(null, "a", "robe", 200);
		BoutiqueItem item2 = new BoutiqueItem(null, "b", "chemise", 150);
		BoutiqueItem item3 = new BoutiqueItem(null, "c", "pull", 80);
		BoutiqueItem item4 = new BoutiqueItem(null, "d", "chaussures", 120);
		BoutiqueItem item5 = new BoutiqueItem(null, "e", "jean", 110);

		entityManager.persist(item1);
		entityManager.persist(item2);
		entityManager.persist(item3);
		entityManager.persist(item4);

		idLastItem = (Long) entityManager.persistAndGetId(item5);

		MyOrder myorder1 = new MyOrder(null, new Date());
		MyOrder myorder2 = new MyOrder(null, new Date());
		MyOrder myorder3 = new MyOrder(null, new Date());
		MyOrder myorder4 = new MyOrder(null, new Date());
		MyOrder myorder5 = new MyOrder(null, new Date());

		entityManager.persist(myorder1);
		entityManager.persist(myorder2);
		entityManager.persist(myorder3);
		entityManager.persist(myorder4);

		idLastMyOrder = (Long) entityManager.persistAndGetId(myorder5);
/*
		
		OrderDetail detail1 = new OrderDetail();
		detail1.setItem(item1);
		detail1.setQuantity(2);
		detail1.setUnitPrice(0f);
		detail1.setOrder(myorder1);
		
		OrderDetail detail2 = new OrderDetail();
		detail1.setItem(item2);
		detail1.setQuantity(5);
		detail1.setUnitPrice(0f);
		detail1.setOrder(myorder2);
		
		OrderDetail detail3 = new OrderDetail();
		detail1.setItem(item3);
		detail1.setQuantity(7);
		detail1.setUnitPrice(0f);
		detail1.setOrder(myorder3);
		
		OrderDetail detail4 = new OrderDetail();
		detail1.setItem(item4);
		detail1.setQuantity(4);
		detail1.setUnitPrice(0f);
		detail1.setOrder(myorder4);
		
		OrderDetail detail5 = new OrderDetail();
		detail1.setItem(item5);
		detail1.setQuantity(15);
		detail1.setUnitPrice(0f);
		detail1.setOrder(myorder5);
		
		entityManager.persist(detail1);
		entityManager.persist(detail2);
		entityManager.persist(detail3);
		entityManager.persist(detail4);

		idLastOrderDetail = (Long) entityManager.persistAndGetId(detail5);
		*/
	}

}
