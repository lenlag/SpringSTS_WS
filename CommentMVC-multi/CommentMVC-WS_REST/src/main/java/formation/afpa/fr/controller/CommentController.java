package formation.afpa.fr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import formation.afpa.fr.exception.CommentAlreadyExistsException;
import formation.afpa.fr.exception.CommentNotFoundException;
import formation.afpa.fr.exception.CommentNotValidException;
import formation.afpa.fr.exception.PostAlreadyExistsException;
import formation.afpa.fr.exception.PostNotFoundException;
import formation.afpa.fr.exception.PostNotValidException;
import formation.afpa.fr.service.ServiceComment;
import formation.afpa.fr.service.ServicePost;

@RestController
@RequestMapping("/rest/comment")
public class CommentController {

	@Autowired
	private ServiceComment serviceComment;
	
	@Autowired
	private ServicePost servicePost;

	@GetMapping("")
	public List<Comment> commentList() throws CommentNotFoundException {
		List<Comment> list = serviceComment.list();
		
		return list;
	}
	
	
	@GetMapping("/{id}")
	public Comment get(@PathVariable("id") Long id) throws CommentNotFoundException {
		return serviceComment.findById(id);
		
	}

	@PostMapping("")
	public Long create(@RequestBody Comment c) throws CommentNotValidException, CommentAlreadyExistsException  {
		serviceComment.create(c);

		return c.getId(); // we return the id of the new created object
	}

	@PutMapping("/{id}")
	public void update(@RequestBody Comment c, @PathVariable("id") Long id) throws CommentNotFoundException, CommentNotValidException {

		serviceComment.findById(id);
		serviceComment.update(c);

	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) throws CommentNotFoundException {
		serviceComment.deleteById(id);
	}

	
	
}