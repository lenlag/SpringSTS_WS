package formation.afpa.fr.session;

import java.util.ArrayList;
import java.util.List;

import formation.afpa.fr.entity.BoutiqueItem;

public class ShoppingCart {

	private List<CartItem> cartList = new ArrayList<>();

	public ShoppingCart() {

	}

	public ShoppingCart(List<CartItem> cartList) {
		super();
		this.cartList = cartList;
	}

	

	public List<CartItem> getCartList() {
		return cartList;
	}

	public void setCartList(List<CartItem> cartList) {
		this.cartList = cartList;
	}

	public void add(BoutiqueItem bi) { 
		CartItem cartitem = new CartItem(bi.getCode(), bi.getLabel(), 1, bi.getPrice()); //creation d'un item du panier, nouvel item!!
		for (CartItem ci : cartList) { // parcour de la liste de items du panier = ce sont des items, mais créés précédemment, qui ont été déjà rajoutés
			if(cartitem.getCode().equals(ci.getCode())) {
			int quantity = ci.getQuantity();
			ci.setQuantity(quantity + 1); //s'il existe, on augmente juste la quantité
			return; // et on sort
			}
		}
		cartList.add(cartitem);
	}
	
	public void update (List<Integer>list) {
		int index = 0;
		for (CartItem ci : cartList) {
			ci.setQuantity(list.get(index));
			index++;
		} 
		
	}

	public void delete(int index) {
		cartList.remove(index);
	}
	
	
}
