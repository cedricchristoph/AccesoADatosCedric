package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.contracts.AsignaturaEntry;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Asignatura;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AsignaturaDAO extends AsignaturaEntry implements ICRUD<Asignatura, Integer> {

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
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new Asignatura(rs.getInt(ID), rs.getString(NOMBRE), rs.getString(CURSO));
			return null;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Devuelve una lista de asignaturas que tengan nombre parecido
	 * @param nombre Nombre a filtrar
	 * @return Devuelve lista con resultados o null en caso de error SQL
	 */
	public List<Asignatura> selectByNombre(String nombre) {
		try (Connection conn = db.getConnection()) {
			List<Asignatura> asignaturas = new ArrayList<Asignatura>();
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NOMBRE + " LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + nombre + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				asignaturas.add(
						new Asignatura(rs.getInt(ID), rs.getString(NOMBRE), rs.getString(CURSO))
						);
			return asignaturas;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Devuelve una lista de asignaturas que pertenezcan al mismo curso
	 * @param curso
	 * @return
	 */
	public List<Asignatura> selectByCurso(String curso) {
		try (Connection conn = db.getConnection()) {
			List<Asignatura> asignaturas = new ArrayList<Asignatura>();
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + CURSO + " LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + curso + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				asignaturas.add(
						new Asignatura(rs.getInt(ID), rs.getString(NOMBRE), rs.getString(CURSO))
						);
			return asignaturas;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Asignatura> selectAll() {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				asignaturas.add(new Asignatura(rs.getInt(ID), rs.getString(NOMBRE), rs.getString(CURSO)));
			return asignaturas;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Asignatura insert(Asignatura entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "INSERT INTO " + TABLE_NAME + " (" + NOMBRE + ", " + CURSO + ") VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getCurso());
			int affectedRows = ps.executeUpdate();
			if (affectedRows <= 0)
				throw new SQLException("No se pudo crear la asignatura");
			return entity;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean update(Asignatura entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "UPDATE " + TABLE_NAME + " SET " + NOMBRE + " = ?, " + CURSO + " = ? WHERE " + ID + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getCurso());
			ps.setInt(3, entity.getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	
}
