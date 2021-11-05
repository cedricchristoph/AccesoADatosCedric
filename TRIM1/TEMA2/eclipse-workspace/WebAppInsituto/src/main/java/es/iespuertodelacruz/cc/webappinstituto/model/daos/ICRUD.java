package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.SQLException;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

// T objeto java, E id del objeto de DDBB
public interface ICRUD<T,E> {
	
	T select(E id);
	List<T> selectAll();
	T insert(T entity) throws SQLException;
	boolean update(T entity) throws SQLException;
	boolean delete(E id) throws SQLException;
	
}
