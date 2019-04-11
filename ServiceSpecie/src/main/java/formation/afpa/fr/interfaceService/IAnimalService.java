package formation.afpa.fr.interfaceService;

import java.util.List;

import formation.afpa.fr.Animal;
import formation.afpa.fr.Exception.AnimalCoatColorNotValidException;
import formation.afpa.fr.Exception.AnimalNameNotValidException;
import formation.afpa.fr.Exception.AnimalNotFoundException;
import formation.afpa.fr.Exception.AnimalSexOrNameNotValidException;

public interface IAnimalService extends IService<Animal, Exception> {

	public List<Animal> findByName(String name) throws AnimalNotFoundException, AnimalNameNotValidException;
	
	public List<Animal> findDictinctBycoatColor (String coatColor) throws AnimalNotFoundException, AnimalCoatColorNotValidException;
	
	public List<Animal> findBySexOrName(String sex,String name) throws AnimalNotFoundException, AnimalSexOrNameNotValidException;
	
	
}
