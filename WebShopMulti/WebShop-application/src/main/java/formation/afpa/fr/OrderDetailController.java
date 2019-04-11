package formation.afpa.fr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.session.CartItem;
import formation.afpa.fr.session.ShoppingCartService;


@Controller
public class OrderDetailController {

	@Autowired
	private ShoppingCartService cartService;
	
	@GetMapping("/orderdetail")
	public String listFromCart(HttpServletRequest request, Model model) {
		
		BoutiqueItem boutiqueItem = (BoutiqueItem) request.getSession().getAttribute("item");
				
		List<CartItem> cartList = cartService.list(request.getSession());
		model.addAttribute("cartList", cartList);
		
		float totalPrice = cartService.total(request.getSession());
		model.addAttribute("total", totalPrice);
		

		return "orderdetail";
	}	
		
}
