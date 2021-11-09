package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class UserDAO implements ICRUD<User, String> {

	MyDatabase db;
	
	public UserDAO(MyDatabase db) {
		this.db = db;
	}
	
	public UserDAO(String dbLocation) {
		db = new MyDatabase(dbLocation);
	}
	
	@Override
	public User select(String id) {
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM users where user = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new User(rs.getString("user"), rs.getString("email"), rs.getString("hash"), rs.getBoolean("active"), false);
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User insert(User entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "INSERT INTO users (user, email, hash, active) VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getUser());
			ps.setString(2, entity.getEmail());
			ps.setString(3, entity.getHashPwd());
			ps.setBoolean(4, entity.isActive());
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) 
				return entity;
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean update(User entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "UPDATE users SET (email = ?, hash = ?) WHERE user = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getHashPwd());
			ps.setString(3, entity.getUser());
			int ok = ps.executeUpdate();
			return (ok > 0);
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
