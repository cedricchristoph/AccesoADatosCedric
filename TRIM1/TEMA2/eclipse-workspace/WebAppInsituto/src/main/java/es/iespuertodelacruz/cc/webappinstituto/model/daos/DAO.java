package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public abstract class DAO<T> implements IDAO<T> {

	private MyDatabase db;
	private Connection conn;
	private String tableName;
	
	public DAO(MyDatabase database, String tableName) {
		db = database;
		this.tableName = tableName;
	}
	
	@Override
	public T select(int id) {
		return null;
	}

	@Override
	public T[] selectAll() {
		
		return null;
	}

	@Override
	public boolean insert(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
