package formation.afpa.fr.session;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import formation.afpa.fr.entity.BoutiqueItem;


@Service
public class ShoppingCartService {
	
	public static final String KEY = "ShoppingCart";

	public void add(HttpSession session, BoutiqueItem bi) {
		ShoppingCart shoppingCart = (ShoppingCart)session.getAttribute(KEY);
		if(shoppingCart == null) { // si le panier est vide
			shoppingCart = new ShoppingCart(); // on le cr�e
			session.setAttribute(KEY, shoppingCart);
		}
		shoppingCart.add(bi);
	}
	
	public void delete(HttpSession session, int index) {
		ShoppingCart ShoppingCart = (ShoppingCart) session.getAttribute(KEY);
		ShoppingCart.delete(index);
	}
	
	
	public List<CartItem> list(HttpSession session) {
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(KEY);
		if(shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute(KEY, shoppingCart);
		}
		return shoppingCart.getCartList();
		
	}
	
	
	
	public void update(HttpSession session, List<Integer> list) {
		ShoppingCart ShoppingCart = (ShoppingCart)session.getAttribute(KEY);
		if(ShoppingCart == null) { 
			ShoppingCart = new ShoppingCart(); 
			session.setAttribute(KEY, ShoppingCart);
		}
		ShoppingCart.update(list);	
	}
	
	
	
	public void clear(HttpSession session) {
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(KEY);
		session.removeAttribute(KEY);
	}
	
	
	public float total(HttpSession session) {
		List<CartItem> myList = list (session);
		float sum = 0;
		for (CartItem ci : myList) {
			sum += ci.getPrice() * ci.getQuantity();
		}
		return sum;
	}
	
	
	
}



