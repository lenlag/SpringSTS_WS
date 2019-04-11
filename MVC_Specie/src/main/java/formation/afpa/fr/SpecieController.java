package formation.afpa.fr;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import formation.afpa.fr.ServiceSpecie;
import formation.afpa.fr.Specie;
import formation.afpa.fr.Exception.SpecieNotAvailableException;

@Controller
public class SpecieController {

	
	@Autowired
	private ServiceSpecie service;

	@GetMapping("/")
	public String start() {
		return "redirect:/species";
	}
	
	
	@GetMapping("/species") // for the 1st page if the name is not index.html
	public String specieList(Model model) throws SpecieNotAvailableException {
		List<Specie> listSpecie = service.findAll();
		model.addAttribute("listSpecie", listSpecie);

		return "species";
		
	}
	
	
	@GetMapping("/species/create")
	public String createSpecie(Model model) {
		model.addAttribute("specie", new Specie());
		return "addSpecie";
	}
	
	@PostMapping("/species/create")
	public String save(Specie sp, Model model) throws Exception {
		if (sp.getId() == null) {
			service.create(sp);
		} else {
			service.update(sp);
		}
		return "redirect:/species";
		
	}
	
	
	@GetMapping("/species/{id}")
	public String update(@PathVariable("id") String id, Model model) throws Exception {
		Specie sp = service.findById(Long.parseLong(id));
		model.addAttribute("specie", sp);
		return "addSpecie";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
		} catch (Exception e) {	
		}
		return "redirect:/species";
	}
	

}
