package formation.afpa.fr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import formation.afpa.fr.Exception.ItemNotFoundException;
import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.session.CartItem;
import formation.afpa.fr.session.ShoppingCart;
import formation.afpa.fr.session.ShoppingCartService;


@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService cartService;
	@Autowired
	private ServiceBoutiqueItem serviceItem;
	
	@GetMapping("/item/add/{id}")
	public String addItem(@PathVariable(name="id")long id, HttpServletRequest request) throws ItemNotFoundException {
		
		BoutiqueItem boutiqueItem = serviceItem.findById(id);	
		cartService.add(request.getSession(), boutiqueItem);	
		
		request.getSession().setAttribute("item", boutiqueItem);
		
		return "redirect:/shoppingcart";
	}
	
	
	
	@GetMapping("/shoppingcart") 
	public String listFromCart(HttpServletRequest request, Model model) {
		
		//BoutiqueItem boutiqueItem = (BoutiqueItem) request.getSession().getAttribute("item");
				
		
		List<CartItem> cartList = cartService.list(request.getSession());
		//List<CartItem> cartList = (List<CartItem>) request.getSession().getAttribute("cart");
		//model.addAttribute("cartList", cartList);
		
		ShoppingCart cart = new ShoppingCart();
		cart.setCartList(cartList);
		request.getSession().setAttribute("ShoppingCart", cart);
		model.addAttribute("shopcart", cart);
	
		float totalPrice = cartService.total(request.getSession());
		model.addAttribute("total", totalPrice);
		

		return "shoppingcart";
	}
	
	@PostMapping("/shoppingcart/update")
    public String listure(ShoppingCart cart, Model model, HttpServletRequest request) {
        request.getSession().setAttribute("ShoppingCart", cart);
        //model.addAttribute("shopcart", cart);
        return "redirect:/shoppingcart";
    }
	
	
	@GetMapping("/cart/{index}/delete")
	public String deleteCartItem(@PathVariable(name="index")int index, HttpServletRequest request) {
		cartService.delete(request.getSession(), index);
		return "redirect:/shoppingcart";
		
	}
	
	@GetMapping("/purchase")
	public String purchase(HttpServletRequest request, Model model) {
		List<CartItem> cartList = cartService.list(request.getSession());
		
		if (cartList.size() == 0) {
			return "cartempty";
		} else {
			return "redirect:/orderdetail";
		}
		
	}
	
	@GetMapping("/cartempty")
			public String cartEmpty() {
		
				return "cartempty";
	}
	
}
