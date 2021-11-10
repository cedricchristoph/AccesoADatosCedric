package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.contracts.AlumnoEntry;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AlumnoDAO extends AlumnoEntry implements ICRUD<Alumno, String> {

	MyDatabase db;
	
	public AlumnoDAO(MyDatabase db) {
		this.db = db;
	}
	
	public AlumnoDAO(String ddbb, String user, String pwd) {
		this.db = new MyDatabase(ddbb, user, pwd);
	}
	
	@Override
	public Alumno select(String id) {
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM " + TABLE_NAME + " where " + DNI + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new Alumno(rs.getString(DNI), rs.getString(NOMBRE), rs.getString(APELLIDOS), new Date(rs.getLong(FECHANACIMIENTO)));
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Alumno> selectAll() {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try (Connection conn = db.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM " + TABLE_NAME;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
				alumnos.add(new Alumno(rs.getString(DNI), rs.getString(NOMBRE), 
						rs.getString(APELLIDOS), new Date(rs.getLong(FECHANACIMIENTO))));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return alumnos;
		}
	}
	
	public List<Alumno> selectByName(String name) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NOMBRE + " LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				alumnos.add(new Alumno(rs.getString(DNI), rs.getString(NOMBRE), 
						rs.getString(APELLIDOS), new Date(rs.getLong(FECHANACIMIENTO))));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return alumnos;
		}
	}
	
	public List<Alumno> selectBySurname(String apellido) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + APELLIDOS + " LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, "%" + apellido + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				alumnos.add(new Alumno(rs.getString(DNI), rs.getString(NOMBRE), 
						rs.getString(APELLIDOS), new Date(rs.getLong(FECHANACIMIENTO))));
		} catch (SQLException e) {
			
		} finally {
			return alumnos;
		}
	}
	
	@Override
	public Alumno insert(Alumno entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "INSERT INTO " + TABLE_NAME + " (" + DNI + ", " + NOMBRE + ", " + APELLIDOS + ", " + FECHANACIMIENTO + ") VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getDni());
			ps.setString(2, entity.getNombre());
			ps.setString(3, entity.getApellidos());
			ps.setLong(4, entity.getFechaNacimiento().getTime());
			int affectedRows = ps.executeUpdate();
			if (affectedRows <= 0) {
				throw new SQLException("Error. No se pudo crear el alumno. Ninguna fila afectada.");
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			return entity;
		}
	}

	@Override
	public boolean update(Alumno entity) {
		try (Connection conn = db.getConnection()) {
			String sql = "UPDATE alumnos SET nombre = ?, apellidos = ?, fechanacimiento = ? WHERE dni = ?";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getApellidos());
			ps.setLong(3, entity.getFechaNacimiento().getTime());
			ps.setString(4, entity.getDni());
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				return true;
			} else {
				throw new SQLException("Error. No se pudo crear el alumno. Ninguna fila afectada.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String id) {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE  FROM " + TABLE_NAME + " WHERE " + DNI + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, id);
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				return true;
			} else {
				throw new SQLException("Error. No se pudo crear el alumno. Ninguna fila afectada.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
