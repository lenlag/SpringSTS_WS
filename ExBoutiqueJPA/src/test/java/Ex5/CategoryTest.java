package Ex5;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes=SmallApp.class)
public class CategoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository repo;	
    
    @Before
	public void setUp() {
		System.out.println("Before");
		Category cat = new Category(null, "GFF", "Test", "Cat1");
	/*
		cat.setCode("JHH");
		cat.setDescription("Test");
		cat.setName("Cat1");
		
	*/	
		entityManager.persist(cat);
		cat = new Category(null, "GFF", "Another test cat", "Cat2");
	/*	
		cat.setCode("KOJH");
		cat.setDescription("HVJHJF");
		cat.setName("Cat2");
		
	*/	
		entityManager.persist(cat);
	}
	@Test
	public void findList() {
		assertEquals(repo.findByName("Cat1").size(),1);
	}
	
	@Test
	public void insert() {
		Category cat = new Category(null, "MOJJ", "Test-test", "Cat3");
		/*
		cat.setName("Cat3");
		cat.setDescription("TestCat");
		cat.setCode("GFDG");
		*/
		repo.save(cat);
		assertEquals(3, repo.count());
	}
	
	
}

