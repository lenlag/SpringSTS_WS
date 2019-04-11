package formation.afpa.fr.interfaceService;

import java.util.List;

import formation.afpa.fr.Specie;
import formation.afpa.fr.Exception.SpecieCommonNameNotValidException;
import formation.afpa.fr.Exception.SpecieLatinNameNotValidException;
import formation.afpa.fr.Exception.SpecieNotFoundException;


public interface ISpecieService extends IService<Specie, Exception> {

	public List<Specie> findByBeginLatinName(String latinName) throws SpecieLatinNameNotValidException, SpecieNotFoundException;
	
	public List<Specie> findByCommonName(String commonName) throws SpecieCommonNameNotValidException, SpecieNotFoundException;
	
	public List<Specie> findByLatinName(String latinName) throws SpecieLatinNameNotValidException, SpecieNotFoundException;
	
}
