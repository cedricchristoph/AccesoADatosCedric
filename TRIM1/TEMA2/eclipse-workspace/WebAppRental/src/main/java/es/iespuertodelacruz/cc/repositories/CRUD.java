package es.iespuertodelacruz.cc.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CRUD<T,E> {

	T select(E id);
	List<T> selectAll();
	T insert(T entity) throws SQLException;
	boolean update(T entity) throws SQLException;
	boolean delete(E id) throws SQLException;
	
}
