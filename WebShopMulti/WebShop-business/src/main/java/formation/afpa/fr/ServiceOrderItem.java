package formation.afpa.fr;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.Exception.MyOrderAlreadyExistsException;
import formation.afpa.fr.Exception.OrderDetailAlreadyExistsException;
import formation.afpa.fr.Exception.OrderDetailNotFoundException;
import formation.afpa.fr.Exception.OrderDetailNotFoundException;
import formation.afpa.fr.Exception.OrderDetailNotAvailableException;
import formation.afpa.fr.Exception.OrderDetailNotFoundException;
import formation.afpa.fr.InterfaceService.IService;
import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.entity.MyOrder;
import formation.afpa.fr.entity.OrderItem;
import formation.afpa.fr.repository.OrderItemRepository;



@Service
public class ServiceOrderItem implements IService<OrderItem, Exception> {
	
	@Autowired
	OrderItemRepository repo;

	@Override
	public List<OrderItem> findAll() throws OrderDetailNotAvailableException {
		List<OrderItem> findAll = (List<OrderItem>) repo.findAll();

		if ((findAll == null) || (findAll.size() == 0)) {
			throw new OrderDetailNotAvailableException();
		}

		return findAll;
	}

	@Override
	public OrderItem findById(Long id) throws OrderDetailNotFoundException {
		if (id == null) {
			throw new OrderDetailNotFoundException();
		}

		OrderItem oi = repo.findById(id).get();
		
		if(oi == null) {
			throw new OrderDetailNotFoundException();
		}
		
		return oi;
	}

	@Override
	public OrderItem create(OrderItem oi) throws OrderDetailNotFoundException, OrderDetailAlreadyExistsException {
		if (oi == null) {
			throw new OrderDetailNotFoundException();
		}

		if (oi.getId() != null) {
			Optional<OrderItem> oiOptional = repo.findById(oi.getId());

			if (oiOptional.isPresent()) {
				throw new OrderDetailAlreadyExistsException();
			}
		}

		return repo.save(oi);
	}

	@Override
	public OrderItem update(OrderItem oi) throws OrderDetailNotFoundException, OrderDetailNotFoundException {
		if ((oi == null) || (oi.getId() == null)) {
			throw new OrderDetailNotFoundException();
		}

		Long idAChercher = oi.getId();
		Optional<OrderItem> oiOptional = repo.findById(idAChercher);

		if (!oiOptional.isPresent()) {
			throw new OrderDetailNotFoundException();
		}
		return repo.save(oi);
	}

	@Override
	public void deleteById(Long id) throws OrderDetailNotFoundException {
		if (id == null) {
			throw new OrderDetailNotFoundException();
		}
		
		Optional<OrderItem> oiOptional = repo.findById(id);
		if (!oiOptional.isPresent()) {
			throw new OrderDetailNotFoundException("Boutique item with requested id does not exist");
		}

		repo.deleteById(id);
	}

	@Override
	public void delete(OrderItem oi) throws Exception {
		if (oi == null) {
			throw new OrderDetailNotFoundException();
		} else if (oi.getId() == null) {
			throw new OrderDetailNotFoundException();
		}
		if (!findAll().contains(oi)) {
			throw new OrderDetailNotFoundException();
		}

		repo.delete(oi);
	}


}
