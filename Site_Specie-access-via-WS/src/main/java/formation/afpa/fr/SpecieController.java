package formation.afpa.fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import formation.afpa.fr.dto.SpecieDTO;
import formation.afpa.fr.service.ServiceSpecieWSRest;

@Controller
public class SpecieController {

	
	@Autowired
	private ServiceSpecieWSRest service;

	@GetMapping("/")
	public String start() {
		return "redirect:/species";
	}
	
	
	@GetMapping("/species") // for the 1st page if the name is not index.html
	public String specieList(Model model) throws Exception {
	
		model.addAttribute("listSpecie", service.list());

		return "species";
		
	}
	
	
	@GetMapping("/species/create")
	public String createSpecie(Model model) {
		model.addAttribute("specie", new SpecieDTO());
		return "addSpecie";
	}
	
	
	@PostMapping("/species/create")
	public String save(SpecieDTO sp, Model model) throws Exception {
		if (sp.getId() == null) {
			service.post(sp);
		} else {
			service.update(sp);
		}
		return "redirect:/species";
		
	}
	
	
	
	@GetMapping("/species/{id}")
	public String update(@PathVariable("id") String id, Model model) throws Exception {
		SpecieDTO sp = service.getById(Long.parseLong(id));
		model.addAttribute("specie", sp);
		return "addSpecie";
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		try {
			service.delete(id);
		} catch (Exception e) {	
		}
		return "redirect:/species";
	}
	

}
