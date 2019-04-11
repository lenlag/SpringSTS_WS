package formation.afpa.fr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.Exception.ItemAlreadyExistsException;
import formation.afpa.fr.Exception.ItemNotAvailableException;
import formation.afpa.fr.Exception.ItemNotFoundException;
import formation.afpa.fr.Exception.ItemNotValidException;
import formation.afpa.fr.InterfaceService.IService;
import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;

@Service
public class ServiceBoutiqueItem implements IService<BoutiqueItem, Exception> {

	@Autowired
	BoutiqueItemRepository repo;

	@Override
	public List findAll() throws ItemNotAvailableException {
		List<BoutiqueItem> findAll = (List<BoutiqueItem>) repo.findAll();

		if ((findAll == null) || (findAll.size() == 0)) {
			throw new ItemNotAvailableException();
		}

		return findAll;
	}

	@Override
	public BoutiqueItem findById(Long id) throws ItemNotFoundException {
		if (id == null) {
			throw new ItemNotFoundException();
		}

		BoutiqueItem it = repo.findById(id).get();

		if (it == null) {
			throw new ItemNotFoundException();
		}

		return it;
	}

	@Override
	public BoutiqueItem create(BoutiqueItem it) throws ItemAlreadyExistsException, ItemNotValidException {

		if (it == null) {
			throw new ItemNotValidException();
		}

		if (it.getId() != null) {
			Optional<BoutiqueItem> itemOptional = repo.findById(it.getId());

			if (itemOptional.isPresent()) {
				throw new ItemAlreadyExistsException();
			} else {
				throw new ItemNotValidException();
			}
		}

		return repo.save(it);

	}

	@Override
	public BoutiqueItem update(BoutiqueItem it) throws ItemNotValidException, ItemNotFoundException {
		if ((it == null) || (it.getId() == null)) {
			throw new ItemNotValidException();
		}

		Long idAChercher = it.getId();
		Optional<BoutiqueItem> itemOptional = repo.findById(idAChercher);

		if (!itemOptional.isPresent()) {
			throw new ItemNotFoundException();
		}
		return repo.save(it);
	}

	@Override
	public void deleteById(Long id) throws ItemNotFoundException {
		if (id == null) {
			throw new ItemNotFoundException();
		}

		Optional<BoutiqueItem> itemOptional = repo.findById(id);
		if (!itemOptional.isPresent()) {
			throw new ItemNotFoundException("Item with requested id does not exist");
		}

		repo.deleteById(id);
	}

	@Override
	public void delete(BoutiqueItem it) throws ItemNotValidException, ItemNotFoundException, ItemNotAvailableException {
		if (it == null) {
			throw new ItemNotValidException();
		} else if (it.getId() == null) {
			throw new ItemNotFoundException();
		}
		if (!findAll().contains(it)) {
			throw new ItemNotAvailableException();
		}

		repo.delete(it);

	}
	
	public BoutiqueItem findByCode(String code) {
		BoutiqueItem bi = repo.findByCode(code);
		return bi;
		
	}

}
