package formation.afpa.fr.interfaceService;

import java.util.List;

import formation.afpa.fr.entity.Comment;
import formation.afpa.fr.exception.CommentAlreadyExistsException;
import formation.afpa.fr.exception.CommentNotFoundException;
import formation.afpa.fr.exception.CommentNotValidException;

public interface ICommentService {
	
	/**
	 * 
	 * @return List of all objects type Comment
	 * @throws CommentNotFoundException
	 */
	
	public List<Comment> list() throws CommentNotFoundException;
	
	/**
	 * 
	 * @param id
	 * @return the entity with the given id or Optional#empty() if none found
	 * @throws CommentNotFoundException
	 */
	
	public Comment findById(Long id) throws CommentNotFoundException;
	
	/**
	 * 
	 * @param comm
	 * @return saves a given entity, the saved entity will never be null
	 * @throws CommentNotValidException
	 * @throws CommentAlreadyExistsException
	 */
	
	public Comment create(Comment comm) throws CommentNotValidException, CommentAlreadyExistsException;
	
	/**
	 * 
	 * @param comm
	 * @return updates a given entity, the saved entity will never be null
	 * @throws CommentNotFoundException
	 * @throws CommentNotValidException
	 */
	
	public Comment update(Comment comm) throws CommentNotFoundException, CommentNotValidException;
	
	/**
	 * Deletes the entity with the given id
	 * @param id
	 * @throws CommentNotFoundException
	 */
	
	public void deleteById(Long id) throws CommentNotFoundException;

}
