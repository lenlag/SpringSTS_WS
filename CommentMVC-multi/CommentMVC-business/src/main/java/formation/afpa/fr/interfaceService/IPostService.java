package formation.afpa.fr.interfaceService;

import java.util.List;

import formation.afpa.fr.entity.Post;
import formation.afpa.fr.exception.PostAlreadyExistsException;
import formation.afpa.fr.exception.PostNotFoundException;
import formation.afpa.fr.exception.PostNotValidException;

public interface IPostService {
	
	/**
	 * 
	 * @return all instances of type Post
	 * @throws PostNotFoundException
	 */
	public List<Post> list() throws PostNotFoundException;
	
	/**
	 * 
	 * @param id
	 * @return the entity with the given id or Optional#empty() if none found
	 * @throws PostNotFoundException
	 */
	
	public Post findById(Long id) throws PostNotFoundException;
	
	/**
	 * 
	 * @param post
	 * @return saves a given entity, the saved entity will never be null
	 * @throws PostNotValidException
	 * @throws PostAlreadyExistsException
	 */
	
	public Post create(Post post) throws PostNotValidException, PostAlreadyExistsException;
	
	/**
	 * 
	 * @param post
	 * @return updates a given entity, the saved entity will never be null
	 * @throws PostNotFoundException
	 * @throws PostNotValidException
	 */
	
	public Post update(Post post) throws PostNotFoundException, PostNotValidException;
	
	/**
	 * Deletes the entity with the given id
	 * @param id
	 * @throws PostNotFoundException
	 */
	
	public void deleteById(Long id) throws PostNotFoundException;


}
