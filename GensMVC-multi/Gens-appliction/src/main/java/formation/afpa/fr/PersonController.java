package formation.afpa.fr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import formation.afpa.fr.entity.Person;
import formation.afpa.fr.exception.PersonAlreadyExistsException;
import formation.afpa.fr.exception.PersonNotAvailableException;
import formation.afpa.fr.exception.PersonNotFoundException;
import formation.afpa.fr.exception.PersonNotValidException;



@Controller
public class PersonController {

	@Autowired
	private ServicePerson service;


	@GetMapping("/") 
	public String createPerson(Model model)  {
		model.addAttribute("person", new Person());
		return "index";
	}
	
	@PostMapping("/person/create")
	public String save(Person p, Model model)  {
		if(p.getId() == null) {
		try {
			service.create(p);
		} catch (PersonNotValidException | PersonAlreadyExistsException e) {

			e.printStackTrace();
		}
		} else if (p.getId() != null) {
			try {
				try {
					service.update(p);
				} catch (PersonNotValidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "redirect:/list"; // redirect = renvoie au nom du methode ==getMapping
	}
	
	
	@GetMapping("/list")
	public String personList(Model model) {
		List<Person> personList;
		try {
			personList = service.list();
			model.addAttribute("pList", personList);
		} catch (PersonNotAvailableException e) {
			e.printStackTrace();
		}
		
		
		return "personlist";
	}
	
	
	@GetMapping("/person/{id}/update")
	public String updatePerson(@PathVariable("id") String id, Model model) {
		Person p;
		try {
			p = service.findById(Long.parseLong(id));
			model.addAttribute("person", p);
		} catch (NumberFormatException | PersonNotFoundException e) {
			e.printStackTrace();
		}
		
		return "index"; //do not use neither => return "redirect:/", nor return "/", as it will re-execute the "/" link and person will be new Person() with id null
						//return "index" will execute the index.html page that will detect the person's firstName, lastName etc...
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
		} catch (PersonNotFoundException e) {	
		}
		return "redirect:/list";
	}
	
}