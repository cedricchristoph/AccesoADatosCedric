package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public interface IDAO<T> {
	
	public T select(int id);
	public T[] selectAll();
	public boolean insert(T entity);
	public boolean delete(T entity);
	public boolean delete(int id);
	
}
