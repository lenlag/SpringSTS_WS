package formation.afpa.fr;


import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.entity.MyOrder;
import formation.afpa.fr.entity.OrderItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;
import formation.afpa.fr.repository.MyOrderRepository;
import formation.afpa.fr.repository.OrderItemRepository;

@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {

	private static Log log = LogFactory.getLog(WebShopApplication.class);

	@Autowired
	BoutiqueItemRepository itemRepo;

	@Autowired
	MyOrderRepository orderRepo;

	@Autowired
	OrderItemRepository orderItemRepo;

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

	@Transactional
	public void run(String... args) throws Exception {
		
	//	initBDD();
		
	}
	
	
	 private void initBDD() {
		 
		 BoutiqueItem item1 = new BoutiqueItem(null, "a", "robe", 200);
		 BoutiqueItem item2 = new BoutiqueItem(null, "b", "chemise", 150);
		 BoutiqueItem item3 = new BoutiqueItem(null, "c", "pull", 80);
		 BoutiqueItem item4 = new BoutiqueItem(null, "d", "chaussures", 120);
		 BoutiqueItem item5 = new BoutiqueItem(null, "e", "jean", 110);
		 
		 itemRepo.save(item1);
		 itemRepo.save(item2);
		 itemRepo.save(item3);
		 itemRepo.save(item4);
		 itemRepo.save(item5);
		 
	/*	 
		MyOrder myorder1 = new MyOrder(null, new Date());
		MyOrder myorder2 = new MyOrder(null, new Date());
		MyOrder myorder3 = new MyOrder(null, new Date());
		MyOrder myorder4 = new MyOrder(null, new Date());
		MyOrder myorder5 = new MyOrder(null, new Date());
		 
		
		orderRepo.save(myorder1);
		orderRepo.save(myorder2);
		orderRepo.save(myorder3);
		orderRepo.save(myorder4);
		orderRepo.save(myorder5);
		
		*/

		 
	 }

}
