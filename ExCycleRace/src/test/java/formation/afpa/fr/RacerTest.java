package formation.afpa.fr;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import formation.afpa.fr.entity.Cycle;
import formation.afpa.fr.entity.Racer;
import formation.afpa.fr.entity.Team;
import formation.afpa.fr.repository.CycleRepository;
import formation.afpa.fr.repository.RacerRepository;
import formation.afpa.fr.repository.TeamRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)

public class RacerTest extends TestParent {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RacerRepository racerRep;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private CycleRepository cycRep;
	
	@Test
	public void List() {
		List<Racer> racers = (List<Racer>)racerRep.findAll();
		assertNotNull(racers);
		assertEquals(racers.size(), 8);
	}
	
		
		@Test
		public void findById() {
			try {
				String firstName = "Peter";
				assertNotNull(idLastRacer);
				
				Racer racer = racerRep.findById(idLastRacer).get();
				assertEquals(racer.getFirstName(), firstName);
				
			} catch (Exception e) {
				assertTrue(false);
			}
		}
		
		@Test
		public void add() {
			Racer racer = new Racer(null, "Adam", "Delheys", null);
			racerRep.save(racer);
			
			assertNotNull(racer);
			assertEquals(racerRep.count(), 9);
		}
		
		@Test
		public void update() {
			try {
				assertNotNull(idLastRacer);
				
				Racer r = racerRep.findById(idLastRacer).get();
				String firstName = "Paul";
								
				assertNotEquals(r.getFirstName(), firstName);
				
				r.setFirstName(firstName);
				racerRep.save(r);
				assertEquals(r.getFirstName(), firstName);
				
			} catch (Exception e) {
				assertTrue(false);
			}
		}
		
		@Test
		public void delete() {
			try {
				assertNotNull(idLastRacer);
				Racer r = racerRep.findById(idLastRacer).get();
				
				assertNotNull(r);
				
				Team team = teamRepo.findById(idLastTeam).get();
				team.getRacers().remove(r);											// FK deletion
				
				List<Cycle> cycles = (java.util.List<Cycle>) cycRep.findAll();
				for (Cycle c : cycles) {
					if(c.getRacer().getId() == r.getId()) {
						c.setRacer(null);  											// FK deletion
					}
				}
				racerRep.deleteById(idLastRacer);
				
				assertEquals(racerRep.count(), 7);
				assertFalse(racerRep.findById(idLastRacer).isPresent());
			} catch (Exception e) {
				assertTrue(false);
			}
		}
}
