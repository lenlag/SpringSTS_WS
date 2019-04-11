package formation.afpa.fr.interfaceService;

import java.util.List;


public interface IService<T, E extends Exception> {
	
	public List<T> findAll() throws E;
	
	public T findById(Long id) throws E;
	
	public T create(T t) throws E;
	
	public List<T> createAll(List<T> t) throws E;
	
	public T update(T t) throws E;
	
	public List<T> updateAll(List<T> t) throws E;
	
	public void deleteById(Long id) throws E;
	
	public void delete(T t) throws E;
	
	public void deleteAll(List<T> t) throws E;

}
