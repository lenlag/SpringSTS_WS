package formation.afpa.fr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import formation.afpa.fr.entity.Country;
import formation.afpa.fr.entity.Cycle;
import formation.afpa.fr.entity.Location;
import formation.afpa.fr.entity.Person;
import formation.afpa.fr.entity.Race;
import formation.afpa.fr.entity.Racer;
import formation.afpa.fr.entity.Team;
import formation.afpa.fr.repository.CountryRepository;
import formation.afpa.fr.repository.CycleRepository;
import formation.afpa.fr.repository.LocationRepository;
import formation.afpa.fr.repository.PersonRepository;
import formation.afpa.fr.repository.RaceRepository;
import formation.afpa.fr.repository.RacerRepository;
import formation.afpa.fr.repository.TeamRepository;


@SpringBootApplication
public class ExCycleRaceApplication implements CommandLineRunner {
		
	private static Log log = LogFactory.getLog(ExCycleRaceApplication.class);
	
	@Autowired
	CountryRepository countryRep;
	
	@Autowired
	CycleRepository cycleRep;
	
	@Autowired
	LocationRepository locRep;
	
	@Autowired
	PersonRepository persRep;
	
	@Autowired
	RaceRepository raceRep;
	
	@Autowired
	RacerRepository racerRep;
	
	@Autowired
	TeamRepository teamRep;
	
	

	public static void main(String[] args) {
		SpringApplication.run(ExCycleRaceApplication.class, args);
	}

	@Transactional	//used here to create a temporary Session when the entities are in LAZY mode
	public void run(String... args) throws Exception {
		
	//	initBDD();
		
		List<Race> races = (List<Race>) raceRep.findAll();
		for (Race race : races) {
			log.info(race);
		}
		
	//	***Test JQuery function******
		
		List<Cycle> cycles = cycleRep.findByTotalSpeeds(25);
		for (Cycle c : cycles) {
			log.info(c);
		}
		
		
		//***Test DELETE CYCLE****
	//	cycleRep.deleteById(8l);
		
		List<Cycle> c = (List<Cycle>) cycleRep.findAll();
		for (Cycle cycle : c) {
			log.info(cycle);
		}
		
		//****Test DELETE Racer*****
		
		racerRep.deleteById(5l); // doesn't work, as FK restriction
		
		List<Racer> r = (List<Racer>) racerRep.findAll();
		for (Racer racer : r) {
			log.info(racer);
		}
	}
	
	private void initBDD() {
		log.info("init");
	
		Country russia = new Country(null, "Russia", "RU");
		Country france = new Country(null, "France", "FR");
		Country italy = new Country(null, "Italy", "IT");
		Country spain = new Country(null, "Spain", "SP");
	
		countryRep.save(russia);
		countryRep.save(france);
		countryRep.save(italy);
		countryRep.save(spain);
				
		Location locRus = new Location(null, "Saint Petersburg", russia);
		Location locFr = new Location(null, "Lyon", france);
		Location locIt = new Location(null, "Milan", italy);
		Location locSp = new Location(null, "Ronda", spain);
		locRep.save(locRus);
		locRep.save(locFr);
		locRep.save(locIt);
		locRep.save(locSp);
		
		Cycle cycleFroome = new Cycle(null, "KTM", "KHH552", 7, 3);
		Cycle cycleRowe = new Cycle(null, "BMC", "SRL01", 8, 3);
		Cycle cycleUran = new Cycle(null, "Merida", "E120", 6, 3);
		Cycle cycleBardet = new Cycle(null, "Kuota", "Khan", 5, 2);
		Cycle cycleDomont = new Cycle(null, "Canyon", "Aeroad", 6, 2);
		Cycle cycleKristoff = new Cycle(null, "S-works", "SL6", 7, 3);
		Cycle cycleDemare = new Cycle(null, "Factor", "O2", 5, 3);
		Cycle cycleSagen = new Cycle(null, "Bora", "Tarmac", 7, 3);
		
		cycleRep.save(cycleFroome);
		cycleRep.save(cycleRowe);
		cycleRep.save(cycleUran);
		cycleRep.save(cycleBardet);
		cycleRep.save(cycleDomont);
		cycleRep.save(cycleKristoff);
		cycleRep.save(cycleDemare);
		cycleRep.save(cycleSagen);
	
		
		Person manager1 = new Person(null, "John", "Bon Jovi");
		Person manager2 = new Person(null, "Anton", "Loginov");
		Person manager3 = new Person(null, "Nikolay", "Melnikov");
		Person manager4 = new Person(null, "Angelika", "Vetrova");
		
		
		persRep.save(manager1);
		persRep.save(manager2);
		persRep.save(manager3);
		persRep.save(manager4);
		
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
		
		
		racerRep.save(racerFroome);
		racerRep.save(racerRowe);
		racerRep.save(racerUran);
		racerRep.save(racerBardet);
		racerRep.save(racerDomont);
		racerRep.save(racerKristoff);
		racerRep.save(racerDemare);
		racerRep.save(racerSagen);
		
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
				
		teamRep.save(teamSky);
		teamRep.save(teamWanty);
		teamRep.save(teamLotto);
		teamRep.save(teamTrel);
		
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
		
		raceRep.save(race1);
		raceRep.save(race2);
		raceRep.save(race3);
		raceRep.save(race4);
		
				
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

