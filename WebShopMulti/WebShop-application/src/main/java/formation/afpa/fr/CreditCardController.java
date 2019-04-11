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
public class CreditCardController {

	@Autowired
	private ShoppingCartService cartService;
	
	@GetMapping("/creditcard")
	public String creditCard() {
		
		return "creditcard";
	}	
	
	
}
