package formation.afpa.fr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation.afpa.fr.entity.Comment;
import formation.afpa.fr.entity.Post;
import formation.afpa.fr.exception.PostAlreadyExistsException;
import formation.afpa.fr.exception.PostNotFoundException;
import formation.afpa.fr.exception.PostNotValidException;
import formation.afpa.fr.service.ServiceComment;
import formation.afpa.fr.service.ServicePost;

@RestController
@RequestMapping("/rest/post")
public class PostController {

	@Autowired
	private ServicePost servicePost;


	@GetMapping("")
	public List<Post> postList() throws PostNotFoundException {
		List<Post> listPosts = servicePost.list();

		return listPosts;
	}

	@GetMapping("/{id}")
	public Post get(@PathVariable("id") Long id) throws PostNotFoundException {
		return servicePost.findById(id);
	}

	@PostMapping("")
	public Long create(@RequestBody Post p) throws PostAlreadyExistsException, PostNotValidException {
		servicePost.create(p);

		return p.getId(); // we return the id of the new created object
	}

	@PutMapping("/{id}")
	public void update(@RequestBody Post p, @PathVariable("id") Long id)
			throws PostNotValidException, PostNotFoundException {

		servicePost.findById(id);
		servicePost.update(p);

	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) throws PostNotFoundException {
		servicePost.deleteById(id);
	}

}