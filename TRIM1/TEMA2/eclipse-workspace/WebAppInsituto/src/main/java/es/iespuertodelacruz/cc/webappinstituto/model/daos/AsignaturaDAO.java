package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.Asignatura;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AsignaturaDAO implements ICRUD<Asignatura, Integer> {

	MyDatabase db;
	
	public AsignaturaDAO(MyDatabase db) {
		this.db = db;
	}
	
	public AsignaturaDAO(String dbLocation) {
		db = new MyDatabase(dbLocation);
	}
	
	@Override
	public Asignatura select(Integer id) {
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM asignaturas where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new Asignatura(rs.getInt("id"), rs.getString("nombre"), rs.getString("curso"));
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Asignatura> selectAll() {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM asignaturas";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				asignaturas.add(new Asignatura(rs.getInt("id"), rs.getString("nombre"), rs.getString("curso")));
			return asignaturas;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Asignatura insert(Asignatura entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "INSERT INTO asignaturas (nombre, curso) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getCurso());
			int ok = ps.executeUpdate();
			if (ok > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next())
					entity.setId(rs.getInt("id"));
				return entity;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean update(Asignatura entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "UPDATE FROM asignaturas SET (nombre = ?, curso = ?) WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getCurso());
			ps.setInt(3, entity.getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE FROM asignaturas WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	
}
