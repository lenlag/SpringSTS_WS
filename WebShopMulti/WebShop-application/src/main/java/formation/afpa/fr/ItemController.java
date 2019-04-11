package formation.afpa.fr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import formation.afpa.fr.Exception.ItemNotAvailableException;

import formation.afpa.fr.entity.BoutiqueItem;


@Controller
public class ItemController {

	@Autowired
	private ServiceBoutiqueItem service;
	

	@GetMapping("/") 
	public String itemList(Model model) throws ItemNotAvailableException {
		List<BoutiqueItem> listItem = service.findAll();
		model.addAttribute("listItem", listItem);

		return "index";
	}
	

}