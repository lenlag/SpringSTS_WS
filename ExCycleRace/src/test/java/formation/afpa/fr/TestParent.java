package formation.afpa.fr;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.Country;
import formation.afpa.fr.entity.Cycle;
import formation.afpa.fr.entity.Location;
import formation.afpa.fr.entity.Person;
import formation.afpa.fr.entity.Race;
import formation.afpa.fr.entity.Racer;
import formation.afpa.fr.entity.Team;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class TestParent {

	@Autowired
	private TestEntityManager entityManager;
	
	public long idLastCountry = 0l;
	public long idLastCycle = 0l;
	public long idLastLocation = 0l;
	public long idLastPerson = 0l;
	public long idLastRace = 0l;
	public long idLastRacer = 0l;
	public long idLastTeam = 0l;
	public long idLastManager = 0l;
	
	@Before()
	public void setUp() {
		initBdd();
	}
	
	
	@Test
	public void test() { // we should create at least 1 test, otherwise the prog will not compile	
		assertTrue(true);
	}
	
	public void initBdd() {
		
		Country russia = new Country(null, "Russia", "RU");
		Country france = new Country(null, "France", "FR");
		Country italy = new Country(null, "Italy", "IT");
		Country spain = new Country(null, "Spain", "SP");
	
		entityManager.persist(russia);
		entityManager.persist(france);
		entityManager.persist(italy);
		idLastCountry = (Long) entityManager.persistAndGetId(spain);
				
		Location locRus = new Location(null, "Saint Petersburg", russia);
		Location locFr = new Location(null, "Lyon", france);
		Location locIt = new Location(null, "Milan", italy);
		Location locSp = new Location(null, "Ronda", spain);
		entityManager.persist(locRus);
		entityManager.persist(locFr);
		entityManager.persist(locIt);
		idLastLocation = (Long)entityManager.persistAndGetId(locSp);
		
		Cycle cycleFroome = new Cycle(null, "KTM", "KHH552", 7, 3);
		Cycle cycleRowe = new Cycle(null, "BMC", "SRL01", 8, 3);
		Cycle cycleUran = new Cycle(null, "Merida", "E120", 6, 3);
		Cycle cycleBardet = new Cycle(null, "Kuota", "Khan", 5, 2);
		Cycle cycleDomont = new Cycle(null, "Canyon", "Aeroad", 6, 2);
		Cycle cycleKristoff = new Cycle(null, "S-works", "SL6", 7, 3);
		Cycle cycleDemare = new Cycle(null, "Factor", "O2", 5, 3);
		Cycle cycleSagen = new Cycle(null, "Bora", "Tarmac", 7, 3);
		
		entityManager.persist(cycleFroome);
		entityManager.persist(cycleRowe);
		entityManager.persist(cycleUran);
		entityManager.persist(cycleBardet);
		entityManager.persist(cycleDomont);
		entityManager.persist(cycleKristoff);
		entityManager.persist(cycleDemare);
		idLastCycle = (Long)entityManager.persistAndGetId(cycleSagen);
	
		
		Person manager1 = new Person(null, "John", "Bon Jovi");
		Person manager2 = new Person(null, "Anton", "Loginov");
		Person manager3 = new Person(null, "Nikolay", "Melnikov");
		Person manager4 = new Person(null, "Angelika", "Vetrova");
		
		
		entityManager.persist(manager1);
		entityManager.persist(manager2);
		entityManager.persist(manager3);
		idLastManager = (Long)entityManager.persistAndGetId(manager4);
		
		Set<Cycle> cyclesFroome = new HashSet<>();
		cyclesFroome.add(cycleFroome);
		
		Set<Cycle> cyclesRowe = new HashSet<>();
		cyclesRowe.add(cycleRowe);
		
		Set<Cycle> cyclesUran = new HashSet<>();
		cyclesUran.add(cycleUran);
		
		Set<Cycle> cyclesBardet = new HashSet<>();
		cyclesBardet.add(cycleBardet);
		
		Set<Cycle> cyclesDomont = new HashSet<>();
		cyclesDomont.add(cycleDomont);
		
		Set<Cycle> cyclesKristoff = new HashSet<>();
		cyclesKristoff.add(cycleKristoff);
		
		Set<Cycle> cyclesDemare = new HashSet<>();
		cyclesDemare.add(cycleDemare);
		
		Set<Cycle> cyclesSagen = new HashSet<>();
		cyclesSagen.add(cycleSagen);
		
		Racer racerFroome = new Racer(null, "Chris", "Froome", cyclesFroome);
		Racer racerRowe = new Racer(null, "Luke", "Rowe", cyclesRowe);
		Racer racerUran = new Racer(null, "Rigoberto", "Uran", cyclesUran);
		Racer racerBardet = new Racer(null,"Romain", "Bardet", cyclesBardet);
		Racer racerDomont = new Racer(null,"Axel", "Domont", cyclesDomont);
		Racer racerKristoff = new Racer(null, "Alex", "Kristoff", cyclesKristoff);
		Racer racerDemare = new Racer(null, "Arnaud", "Demare", cyclesDemare);
		Racer racerSagen = new Racer(null, "Peter", "Sagen", cyclesSagen);
		
		
		entityManager.persist(racerFroome);
		entityManager.persist(racerRowe);
		entityManager.persist(racerUran);
		entityManager.persist(racerBardet);
		entityManager.persist(racerDomont);
		entityManager.persist(racerKristoff);
		entityManager.persist(racerDemare);
		idLastRacer = (Long) entityManager.persistAndGetId(racerSagen);
		
		cycleBardet.setRacer(racerBardet);
		cycleDemare.setRacer(racerDemare);
		cycleDomont.setRacer(racerDomont);
		cycleFroome.setRacer(racerFroome);
		cycleKristoff.setRacer(racerKristoff);
		cycleRowe.setRacer(racerRowe);
		cycleSagen.setRacer(racerSagen);
		cycleUran.setRacer(racerUran);
		
		Set<Racer> racers1 = new HashSet<>();
		racers1.add(racerFroome);
		racers1.add(racerRowe);
			
		Set<Racer> racers2 = new HashSet<>();
		racers2.add(racerUran);
		racers2.add(racerBardet);
		
		Set<Racer> racers3 = new HashSet<>();
		racers3.add(racerDomont);
		racers3.add(racerKristoff);
		racers3.add(racerDemare);
		
		Set<Racer> racers4 = new HashSet<>();
		racers4.add(racerSagen);
	
		Team teamSky = new Team(null, "Sky", manager1, racers1);
		Team teamWanty = new Team(null, "Wanty", manager2, racers2);
		Team teamLotto = new Team(null, "Lotto", manager3, racers3);
		Team teamTrel = new Team(null, "Trel", manager4, racers4);
				
		entityManager.persist(teamSky);
		entityManager.persist(teamWanty);
		entityManager.persist(teamLotto);
		idLastTeam = (Long) entityManager.persistAndGetId(teamTrel);
		
		Set<Team> teams1 = new HashSet<>();
		teams1.add(teamSky);
		teams1.add(teamTrel);
		
		
		Set<Team> teams2 = new HashSet<>();
		teams2.add(teamSky);
		teams2.add(teamWanty);
		teams2.add(teamLotto);
		teams2.add(teamTrel);
		
		Set<Team> teams3 = new HashSet<>();
		teams3.add(teamTrel);
		teams3.add(teamLotto);
		teams3.add(teamWanty);
		
		
		Race race1 = new Race(null, "Espelette", locFr, teams1);
		Race race2 = new Race(null, "SPB", locRus, teams2);
		Race race3 = new Race(null, "Bella vita", locIt, teams3);
		Race race4 = new Race(null, "Carpe Diem", locSp, teams2);
		
		entityManager.persist(race1);
		entityManager.persist(race2);
		entityManager.persist(race3);
		idLastRace = (Long) entityManager.persistAndGetId(race4);
		
				
		racerFroome.setTeam(teamSky);
		racerRowe.setTeam(teamSky);
		racerKristoff.setTeam(teamLotto);
		racerDomont.setTeam(teamLotto);
		racerDemare.setTeam(teamLotto);
		racerUran.setTeam(teamWanty);
		racerBardet.setTeam(teamWanty);
		racerSagen.setTeam(teamTrel);
		
		
	}
	
}
