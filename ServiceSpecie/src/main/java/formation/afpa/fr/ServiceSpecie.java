package formation.afpa.fr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.Exception.SpecieAlreadyExistsException;
import formation.afpa.fr.Exception.SpecieCommonNameNotValidException;
import formation.afpa.fr.Exception.SpecieLatinNameNotValidException;
import formation.afpa.fr.Exception.SpecieNotAvailableException;
import formation.afpa.fr.Exception.SpecieNotFoundException;
import formation.afpa.fr.Exception.SpecieNotValidException;
import formation.afpa.fr.interfaceService.ISpecieService;

@Service
public class ServiceSpecie implements ISpecieService {

	@Autowired
	SpecieRepository srepo;

	public List<Specie> findAll() throws SpecieNotAvailableException { // algorigramme ok
		List<Specie> findAll = (List<Specie>) srepo.findAll();

		if ((findAll == null) || (findAll.size() == 0)) {
			throw new SpecieNotAvailableException();
		}

		return findAll;
	}

	public List<Specie> findByBeginLatinName(String latinName) // algorigramme ok
			throws SpecieLatinNameNotValidException, SpecieNotFoundException {

		if (latinName == null) {
			throw new SpecieLatinNameNotValidException();
		}
		List<Specie> findByBeginLatinName = srepo.findByBeginLatinName(latinName);

		if ((findByBeginLatinName.size() == 0) || (findByBeginLatinName == null)) {
			throw new SpecieNotFoundException();
		}

		return findByBeginLatinName;
	}

	public List<Specie> findByCommonName(String commonName) // algorigramme ok
			throws SpecieCommonNameNotValidException, SpecieNotFoundException {
		if (commonName == null) {
			throw new SpecieCommonNameNotValidException();
		}

		List<Specie> findByCommon = srepo.findByCommonName(commonName);
		if ((findByCommon.size() == 0) || (findByCommon == null)) {
			throw new SpecieNotFoundException();
		}
		
		return findByCommon;
	}

	public List<Specie> findByLatinName(String latinName) // algorigramme ok
			throws SpecieLatinNameNotValidException, SpecieNotFoundException {
		if (latinName == null) {
			throw new SpecieLatinNameNotValidException();
		}
		List<Specie> findByLatinName = srepo.findByLatinName(latinName);

		if ((findByLatinName.size() == 0) || (findByLatinName == null)) {
			throw new SpecieNotFoundException();
		}

		return findByLatinName;
	}

	public Specie findById(Long id) throws SpecieNotFoundException, Exception { //++
		if (id == null) {
			throw new Exception("The id is invalid");
		}

		Specie sp = srepo.findById(id).get();
		
		if(sp == null) {
			throw new SpecieNotFoundException();
		}
		
		return sp;
	}

	public Specie create(Specie sp) throws SpecieNotValidException, SpecieAlreadyExistsException { // algorigramme ok

		if (sp == null) {
			throw new SpecieNotValidException();
		}

		if (sp.getId() != null) {
			Optional<Specie> specieOptional = srepo.findById(sp.getId());

			if (specieOptional.isPresent()) {
				throw new SpecieAlreadyExistsException();
			} else {
				throw new SpecieNotValidException();
			}
		}

		return srepo.save(sp);
	}

	public List<Specie> createAll(List<Specie> list) throws SpecieNotValidException, SpecieAlreadyExistsException { //ok
		if (list == null) {
			throw new SpecieNotValidException();
		}
		if (list.size() == 0) { 
			throw new SpecieNotValidException();
		}
		
		for (Specie specie : list) {
			if(specie == null) {
				throw new SpecieNotValidException();
			}
			
			if(specie.getId() != null) {
				Optional<Specie> specieFromDB = srepo.findById(specie.getId());

				if (specieFromDB.isPresent()) {
					throw new SpecieAlreadyExistsException();
				} else {
					throw new SpecieNotValidException();
				}
			}
		}
		return (List<Specie>) srepo.saveAll(list);
	}

	public Specie update(Specie sp) throws SpecieNotValidException, SpecieNotFoundException { // algorigramme ok
		if (sp == null){
			throw new SpecieNotValidException("Object specie is null");
		}
		
		if (sp.getId() == null) {
			throw new SpecieNotValidException("Id is null");
		}

		Long idAChercher = sp.getId();
		Optional<Specie> specieOptional = srepo.findById(idAChercher);

		if (!specieOptional.isPresent()) {
			throw new SpecieNotFoundException();
		}
		return srepo.save(sp);
	}
	
	
	public List<Specie> updateAll(List<Specie> list) throws SpecieNotFoundException, SpecieNotValidException { // ok
		if((list == null)||(list.size() == 0)) {
			throw new SpecieNotValidException();
		}
		
		for (Specie specie : list) {
			if((specie == null)||(specie.getId() == null)) {
				throw new SpecieNotValidException();
			} else {
				Optional<Specie> specieFromDB = srepo.findById(specie.getId());
				
				if(!specieFromDB.isPresent()) {
					throw new SpecieNotFoundException();
				}
			}
		}
		return (List<Specie>) srepo.saveAll(list);
	}

	public void deleteById(Long id) throws SpecieNotFoundException { //++
		if (id == null) {
			throw new SpecieNotFoundException();
		}
		
		Optional<Specie> specieOptional = srepo.findById(id);
		if (!specieOptional.isPresent()) {
			throw new SpecieNotFoundException("Specie with requested id does not exist");
		}

		srepo.deleteById(id);
	}

	public void delete(Specie sp) throws SpecieNotValidException, SpecieNotFoundException, SpecieNotAvailableException { // ok

		if (sp == null) {
			throw new SpecieNotValidException();
		} else if (sp.getId() == null) {
			throw new SpecieNotFoundException();
		}
		if (!findAll().contains(sp)) {
			throw new SpecieNotAvailableException();
		}

		srepo.delete(sp);
	}

	public void deleteAll(List<Specie> list) throws SpecieNotFoundException { // ok
		if ((list.size() == 0) || (list == null)) {
			throw new SpecieNotFoundException();
		}
		for (Specie specie : list) {
			if (specie.getId() == null) {
				throw new SpecieNotFoundException();
			}
		}
		srepo.deleteAll(list);
	}
}