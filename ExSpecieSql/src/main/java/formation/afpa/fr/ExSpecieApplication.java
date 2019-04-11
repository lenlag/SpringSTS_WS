package formation.afpa.fr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExSpecieApplication implements CommandLineRunner {
	
	private static Log log = LogFactory.getLog(ExSpecieApplication.class);
	
	
	@Autowired
	SpecieRepository rep;
	
	@Autowired
	AnimalRepository animalRep;
	
	@Autowired
	PersonRepository personRep;
	

	public static void main(String[] args) {
		SpringApplication.run(ExSpecieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/*	
		// ********** CRUD de SPECIE******************
		
		Specie chat = new Specie(null, "Chat", "Catus");
		Specie perroquet  = new Specie(null, "Perroquet", "Strigops habroptila");
		Specie marmotte = new Specie(null, "Marmotte", "Marmota");
		
		log.info(perroquet);
		
		// TEST CREATE
	//	rep.save(chat);
	//	rep.save(perroquet);
	//	rep.save(marmotte);
	
		// TEST COUNT
		log.info(rep.count());
		
		//TEST DELETE : soit try/catch, soit test -> existe?
		
			//var1
		try {
			rep.deleteById(10l);
		} catch (Exception e) {
	
		}	
			//var2
		if(rep.existsById(10l)) {
			rep.deleteById(10l);
		}

		
		// TEST LIST
		Iterable<Specie> myList = rep.findAll();
		log.info(myList);
		
		log.info(rep.existsById(10l)); // boolean
		
		//TEST FIND BY ID
		log.info(rep.findById(7l).get()); 
		log.info(rep.hashCode()); //291052422 => une valeur de hachage (en un entier signé 32-bit)
		
		//TEST FIND BY COMMON NAME
		List<Specie> specieList = rep.findByCommonName("tortue");
		log.info(specieList);
		
		//TEST UPDATE
		Specie specie1 = rep.findById(7l).get();
		specie1.setCommonName("Cheval");
		rep.save(specie1);

		
		
		// *************CRUD d'ANIMAL********************
		
		//COUNT
		log.info(animalRep.count());
	
		//CREATE
		Animal lucky = new Animal(null, rep.findById(8l).get(), "Lucky", "gray", "f");
	//	animalRep.save(lucky);
		
		
	//	animalRep.delete(lucky);
		
		
		//TEST DELETE
		try {
			animalRep.deleteById(9l); // au cas où l'id n'existe pas
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(animalRep.existsById(10l)) {
			animalRep.deleteById(10l); // au cas où l'id n'existe pas
		}
		
		//	animalRep.deleteById(9l); tester si l'id existe => voir plus haut, sinon erreurs!!! sinon faire un try/catch
		
		
		// COUNT
		log.info(animalRep.count());
		
		
		//TEST UPDATE
		Animal animal1 = animalRep.findById(5l).get();
		animal1.setCoatColor("white");
		animalRep.save(animal1);
		
		
		Animal anim = animalRep.findById(1L).get();
		anim.setCoatColor("Purple");
		animalRep.save(anim);
		
		// FIND BY ID
		log.info(animalRep.findById(7l).get());
		
		//FIND ALL	
		
	//	log.info(animalRep.findAll());
		
		
		List<Animal> l = (List<Animal>)animalRep.findAll();
		for (Animal an : l) {
			log.info(an.toString());
		}
		
		
		// ************CRUD de PERSON ******************
		
		//TEST FIND ALL
		List<Person> persList = (List<Person>)personRep.findAll();
		for (Person p : persList) {
			log.info(p);
		} // imprimera chaque élément de la liste à la ligne => bonnz visualisation!
		
 		log.info(personRep.findAll()); // cette option imprimera la liste avec des [...,.. ,etc ] => pas bonne visualisation
		
 		//TEST FIND BY ID
 		log.info(personRep.findById(19l).get());
 		
 		//TEST FIND BY...
 		log.info(personRep.findByFirstNameOrLastName("David", "Depp"));
 		log.info(personRep.findDictinctByLastName("MATHIEU"));
 		log.info(personRep.findByLastName("Lamarque"));
 		
 		//TEST CREATE
 		
 		Person maia = new Person (null, "Marianela", "Piccat", 34, new ArrayList<Animal>());
 		//maia.getAnimal().add(a);
 		personRep.save(maia);
 		maia = personRep.findById(maia.getId()).get();
 		Animal a = animalRep.findById(7L).get();
 		maia.getAnimal().add(a);
 		
 		
 		persList = (List<Person>)personRep.findAll();
		for (Person p : persList) {
			log.info(p);
		}
 	
 		// TEST UPDATE
		maia.setAge(25);
		personRep.save(maia);
			
		
		//TEST DELETE
		try {
			personRep.deleteById(30l); // au cas où l'id n'existe pas
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(personRep.existsById(30l)) {
			animalRep.deleteById(30l); // au cas où l'id n'existe pas
		}
		*/
	}
	

}

