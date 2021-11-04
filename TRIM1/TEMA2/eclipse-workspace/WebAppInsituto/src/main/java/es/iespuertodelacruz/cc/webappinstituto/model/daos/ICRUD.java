package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

// T objeto java, E id del objeto de DDBB
public interface ICRUD<T,E> {
	
	T select(E id);
	List<T> selectAll();
	T insert(T entity);
	boolean update(T entity);
	boolean delete(E id);
	
}
