package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AlumnoDAO implements ICRUD<Alumno, String>{

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
			String sql = "SELECT * FROM alumnos where dni = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), new Date(rs.getLong("fechanacimiento")));
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
			String sql = "SELECT * FROM alumnos";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
				alumnos.add(new Alumno(rs.getString("dni"), rs.getString("nombre"), 
						rs.getString("apellidos"), new Date(rs.getLong("fechanacimiento"))));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return alumnos;
		}
	}
	
	public List<Alumno> selectByName(String name) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM alumnos WHERE nombre LIKE %?%";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				alumnos.add(new Alumno(rs.getString("dni"), rs.getString("nombre"), 
						rs.getString("apellidos"), new Date(rs.getLong("fechanacimiento"))));
		} catch (SQLException e) {
			
		} finally {
			return alumnos;
		}
	}
	
	public List<Alumno> selectBySurname(String apellido) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		try (Connection conn = db.getConnection()) {
			String sql = "SELECT * FROM alumnos WHERE apellidos LIKE %?%";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, apellido);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				alumnos.add(new Alumno(rs.getString("dni"), rs.getString("nombre"), 
						rs.getString("apellidos"), new Date(rs.getLong("fechanacimiento"))));
		} catch (SQLException e) {
			
		} finally {
			return alumnos;
		}
	}
	
	@Override
	public Alumno insert(Alumno entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "INSERT INTO alumnos (dni, nombre, apellidos, fechanacimiento) VALUES (?,?,?,?)";
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
			String sql = "UPDATE alumnos SET (nombre = ?, apellidos = ?, fechanacimiento = ?) WHERE dni = ?";
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
			return false;
		}
	}

	@Override
	public boolean delete(String id) {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE  FROM alumnos WHERE dni = ?";
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
