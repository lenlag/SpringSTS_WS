package formation.afpa.fr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import formation.afpa.fr.session.CartItem;
import formation.afpa.fr.session.ShoppingCartService;


@Controller
public class PaymentController {

	@Autowired
	private ServiceOrderItem service;
	
	@Autowired
	private ServiceBoutiqueItem biservice;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private ServiceMyOrder myorderservice;
		
	@PostMapping("/paymentaccepted")
	public String paymentAccepted(HttpServletRequest request, HttpSession session) throws Exception {
		
		List<CartItem> cartList = cartService.list(request.getSession()); // cannot use request.getSession().getAttribute(" cartList")
		
		myorderservice.createOrderAndOrderItem(cartList);
		
		cartService.clear(session);
		return "paymentaccepted";
	}
	
	
		
}
