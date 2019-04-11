package formation.afpa.fr;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.Exception.MyOrderAlreadyExistsException;
import formation.afpa.fr.Exception.MyOrderNotAvailableException;
import formation.afpa.fr.Exception.MyOrderNotFoundException;
import formation.afpa.fr.Exception.MyOrderNotValidException;
import formation.afpa.fr.InterfaceService.IService;
import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.entity.MyOrder;
import formation.afpa.fr.entity.OrderItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;
import formation.afpa.fr.repository.MyOrderRepository;
import formation.afpa.fr.repository.OrderItemRepository;
import formation.afpa.fr.session.CartItem;
import formation.afpa.fr.session.ShoppingCart;

@Service
public class ServiceMyOrder implements IService<MyOrder, Exception> {

	@Autowired
	MyOrderRepository repo;

	@Autowired
	BoutiqueItemRepository birepo;

	@Autowired
	OrderItemRepository oirepo;

	@Override
	public List<MyOrder> findAll() throws MyOrderNotAvailableException {
		List<MyOrder> findAll = (List<MyOrder>) repo.findAll();

		if ((findAll == null) || (findAll.size() == 0)) {
			throw new MyOrderNotAvailableException();
		}

		return findAll;
	}

	@Override
	public MyOrder findById(Long id) throws MyOrderNotFoundException {
		if (id == null) {
			throw new MyOrderNotFoundException();
		}

		MyOrder order = repo.findById(id).get();

		if (order == null) {
			throw new MyOrderNotFoundException();
		}

		return order;
	}

	@Override
	public MyOrder create(MyOrder order) throws MyOrderAlreadyExistsException, MyOrderNotValidException {
		if (order == null) {
			throw new MyOrderNotValidException();
		}

		if (order.getId() != null) {
			Optional<MyOrder> orderOptional = repo.findById(order.getId());

			if (orderOptional.isPresent()) {
				throw new MyOrderAlreadyExistsException();
			} else {
				throw new MyOrderNotValidException();
			}
		}
		return repo.save(order);

	}

	@Override
	public MyOrder update(MyOrder order) throws MyOrderNotValidException, MyOrderNotFoundException {
		if ((order == null) || (order.getId() == null)) {
			throw new MyOrderNotValidException();
		}

		Long idAChercher = order.getId();
		Optional<MyOrder> orderOptional = repo.findById(idAChercher);

		if (!orderOptional.isPresent()) {
			throw new MyOrderNotFoundException();
		}
		return repo.save(order);
	}

	@Override
	public void deleteById(Long id) throws MyOrderNotFoundException {
		if (id == null) {
			throw new MyOrderNotFoundException();
		}

		Optional<MyOrder> orderOptional = repo.findById(id);
		if (!orderOptional.isPresent()) {
			throw new MyOrderNotFoundException("Order with requested id does not exist");
		}

		repo.deleteById(id);
	}

	@Override
	public void delete(MyOrder order)
			throws MyOrderNotValidException, MyOrderNotFoundException, MyOrderNotAvailableException {
		if (order == null) {
			throw new MyOrderNotValidException();
		} else if (order.getId() == null) {
			throw new MyOrderNotFoundException();
		}
		if (!findAll().contains(order)) {
			throw new MyOrderNotAvailableException();
		}

		repo.delete(order);
	}

	public void createOrderAndOrderItem(List<CartItem> list) {

		OrderItem orderItem = null;
		Set<OrderItem> set = new HashSet<>();
		for (CartItem cartItem : list) {
			orderItem = new OrderItem();
			BoutiqueItem bi = birepo.findByCode(cartItem.getCode());
			orderItem.setItem(bi);
			orderItem.setNb(cartItem.getQuantity());
			oirepo.save(orderItem);
		
			set.add(orderItem);
		}

		
		MyOrder myorder = new MyOrder(set, new Date());
		repo.save(myorder);

	}

}
