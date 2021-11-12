package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.contracts.UserEntry;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class UserDAO extends UserEntry implements ICRUD<User, String> {

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
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new User(rs.getString(USER), rs.getString(EMAIL), rs.getString(HASH), rs.getBoolean(ACTIVE), false, rs.getInt(TYPE));
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<User> selectAllInactive() {
		List<User> users = new ArrayList<User>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ACTIVE + " = false";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				users.add(new User(rs.getString(USER), rs.getString(EMAIL),
						rs.getString(HASH), rs.getBoolean(ACTIVE), false, rs.getInt(TYPE)));
			return users;
		} catch (Exception e) {
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
			String sql = "INSERT INTO " + TABLE_NAME + " (" + USER + ", " + EMAIL + ", " + HASH + ", " + ACTIVE + ", " + TYPE + ") VALUES (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getUser());
			ps.setString(2, entity.getEmail());
			ps.setString(3, entity.getHashPwd());
			ps.setBoolean(4, entity.isActive());
			ps.setInt(5, entity.getAccessLevel());
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
			String sql = "UPDATE " + TABLE_NAME + " SET " + EMAIL + " = ?, " + HASH + " = ?, " + TYPE + " = ?, " + ACTIVE + " = ? WHERE " + USER + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getEmail());
			ps.setString(2, entity.getHashPwd());
			ps.setInt(3, entity.getAccessLevel());
			ps.setBoolean(4, entity.isActive());
			ps.setString(5, entity.getUser());
			int ok = ps.executeUpdate();
			if (ok == 0)
				return false;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	} 

	@Override
	public boolean delete(String id) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + USER + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			int ok = ps.executeUpdate();
			return (ok > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
}
