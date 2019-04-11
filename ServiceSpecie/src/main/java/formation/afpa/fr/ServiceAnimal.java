package formation.afpa.fr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.Exception.AnimalAlreadyExistsException;
import formation.afpa.fr.Exception.AnimalCoatColorNotValidException;
import formation.afpa.fr.Exception.AnimalNameNotValidException;
import formation.afpa.fr.Exception.AnimalNotAvailableException;
import formation.afpa.fr.Exception.AnimalNotFoundException;
import formation.afpa.fr.Exception.AnimalNotValidException;
import formation.afpa.fr.Exception.AnimalSexOrNameNotValidException;
import formation.afpa.fr.interfaceService.IAnimalService;

@Service
public class ServiceAnimal implements IAnimalService {
	
	@Autowired
	AnimalRepository animRep;
	
	public ServiceAnimal() {
		
	}
	
	public List<Animal> findAll()  throws AnimalNotAvailableException { //ok
		List<Animal> findAll = (List<Animal>) animRep.findAll();
		
		if(findAll == null || findAll.size() == 0) {
			throw new AnimalNotAvailableException();
		}
		
		return findAll;
	}
	
	public List<Animal> findByName(String name) // ok
			throws AnimalNotFoundException, AnimalNameNotValidException {
		if(name == null) {
			throw new AnimalNameNotValidException();
		}
		
		List<Animal> findByName = animRep.findByName(name);
		if ((findByName == null) ||(findByName.size() == 0)) {
			throw new AnimalNotFoundException();
		}
		
		return animRep.findByName(name);
	}
	
	public List<Animal> findDictinctBycoatColor (String coatColor) // ok
			throws AnimalNotFoundException, AnimalCoatColorNotValidException { 
		if(coatColor == null) {
			throw new AnimalCoatColorNotValidException();
		}
		List<Animal> foundDistByCoatColor = animRep.findDictinctBycoatColor(coatColor);
		if((foundDistByCoatColor.size() == 0)||(foundDistByCoatColor == null)) {
			throw new AnimalNotFoundException();
		}
		return foundDistByCoatColor;
	}
	
	public List<Animal> findBySexOrName(String sex,String name) //ok
			throws AnimalNotFoundException, AnimalSexOrNameNotValidException {
		if((sex==null) || (name==null)) {
			throw new AnimalSexOrNameNotValidException();
		}
		
		List<Animal> animalList = animRep.findBySexOrName(sex, name);
		if((animalList == null)||(animalList.size() == 0)) {
			throw new AnimalNotFoundException();
		}
		return animalList;
	}
	
	public Animal findById(Long id) throws AnimalNotFoundException, Exception { // ++
		if(id == null) {
			throw new Exception("The id is invalid");
		}
		Animal an = animRep.findById(id).get();
		if(an == null) {
			throw new AnimalNotFoundException();
		}
		
		return an;
	}
	
	public Animal create(Animal an) throws AnimalNotValidException, AnimalAlreadyExistsException { //ok
		
		if(an == null) {
			throw new AnimalNotValidException();
		}
		
		if (an.getId() != null) {

			Optional<Animal> animalFromDB = animRep.findById(an.getId());

			if (animalFromDB.isPresent()) {
				throw new AnimalAlreadyExistsException();
			} else {
				throw new AnimalNotValidException();
			}
		}
		
		return animRep.save(an);
	}
	
	public List<Animal> createAll(List<Animal> list) throws AnimalNotValidException, AnimalAlreadyExistsException { //ok
		if((list == null)||(list.size()==0)) {
			throw new AnimalNotValidException();
		}
		
		for (Animal animal : list) {
			if(animal == null) {
				throw new AnimalNotValidException();
			}
			if(animal.getId() != null) {
				
				Optional<Animal> animalFromDB = animRep.findById(animal.getId());

				if (animalFromDB.isPresent()) {
					throw new AnimalAlreadyExistsException();
				} else {
					throw new AnimalNotValidException();
				}
			}
		}
		return (List<Animal>) animRep.saveAll(list);
	}
	
	public void deleteById(Long id) throws AnimalNotFoundException { // ++
		if(id == null) {
			throw new AnimalNotFoundException();
		}
		Optional <Animal> animalOptional = animRep.findById(id);
		if(!animalOptional.isPresent()) {
			throw new AnimalNotFoundException("Animal with requested id does not exist");
		}
		animRep.deleteById(id);
	}
	
	public void delete(Animal an) throws AnimalNotValidException, AnimalNotFoundException, AnimalNotAvailableException  { //ok ++
		if(an == null) {
			throw new AnimalNotValidException();	
		} else if(an.getId() == null) {
			throw new AnimalNotFoundException();
		}
		if (!findAll().contains(an)) {
			throw new AnimalNotAvailableException();
		}

		animRep.delete(an);
	}
	
	public void deleteAll(List<Animal> list) throws AnimalNotFoundException { //ok
		if((list == null)||(list.size() == 0)) {
			throw new AnimalNotFoundException();
		}
		for (Animal animal : list) {
			if (animal.getId() == null) {
				throw new AnimalNotFoundException();
			}
		}
			
		animRep.deleteAll(list);
	}

	@Override
	public Animal update(Animal an) throws AnimalNotValidException, AnimalNotFoundException { //ok
		if (an == null) {
			throw new AnimalNotValidException();
		}

		if (an.getId() == null) {
			throw new AnimalNotValidException();
		} else {

			Optional<Animal> specieFromDB = animRep.findById(an.getId());

			if (!specieFromDB.isPresent()) {
				throw new AnimalNotFoundException();
			}
		}

		return animRep.save(an);
	}

	
	public List<Animal> updateAll(List<Animal> list) throws AnimalNotFoundException, AnimalNotValidException { //ok
		if((list == null)||(list.size() == 0)) {
			throw new AnimalNotValidException();
		}
		
		for (Animal animal : list) {
			if((animal.getId() == null)||(animal == null)) {
				throw new AnimalNotValidException();
			} else {
				Optional<Animal> animalFromDB = animRep.findById(animal.getId());
				
				if(!animalFromDB.isPresent()) {
					throw new AnimalNotFoundException();
				}
			}
		}
		return (List<Animal>) animRep.saveAll(list);
	}
	
	
	
}
