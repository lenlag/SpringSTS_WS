package formation.afpa.fr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.Cycle;
import formation.afpa.fr.repository.CycleRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class CycleTest extends TestParent {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CycleRepository cycRep;
	
	@Test
	public void List() {
		List<Cycle> cycles = (java.util.List<Cycle>) cycRep.findAll();
		assertNotNull(cycles);
		assertEquals(cycles.size(), 8);
	}
	
		@Test
		public void findByTotalSpeeds() {
			List<Cycle> cyc = cycRep.findByTotalSpeeds(15);
			String model = "Khan";
			assertNotNull(cyc);
			assertEquals(cyc.size(), 2);
			assertEquals(cyc.get(0).getModel(), model);
		}
		
		@Test
		public void findById() {
			try {
				String model = "Tarmac";
				assertNotNull(idLastCycle);
				
				Cycle cycle = cycRep.findById(idLastCycle).get();
				assertEquals(cycle.getModel(), model);
				
			} catch (Exception e) {
				assertTrue(false);
			}
		}
		
		@Test
		public void add() {
			Cycle newCycle = new Cycle(null, "Barbie", "BBB", 3, 2);
			cycRep.save(newCycle);
			
			assertNotNull(newCycle);
			assertEquals(cycRep.count(), 9);
		}
		
		@Test
		public void update() {
			try {
				assertNotNull(idLastCycle);
				
				Cycle cyc = cycRep.findById(idLastCycle).get();
				String model = "Tarmac XXL";
				
				assertNotEquals(cyc.getModel(), model);
				
				cyc.setModel(model);
				cycRep.save(cyc);
				assertEquals(cyc.getModel(), model);
				
			} catch (Exception e) {
				assertTrue(false);
			}
		}
		
		@Test
		public void delete() {
			try {
				assertNotNull(idLastCycle);
				Cycle c = cycRep.findById(idLastCycle).get();
				
				assertNotNull(c);
				
				cycRep.delete(c);
				
				assertEquals(cycRep.count(), 7);
				assertFalse(cycRep.findById(idLastCycle).isPresent());
			} catch (Exception e) {
				assertTrue(false);
			}
		}
}
