package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class AlumnoDAO implements ICRUD<Alumno, Integer>{

	MyDatabase db;
	
	public AlumnoDAO(MyDatabase db) {
		this.db = db;
	}
	
	public AlumnoDAO(String ddbb, String user, String pwd) {
		this.db = new MyDatabase(ddbb, user, pwd);
	}
	
	@Override
	public Alumno select(Integer id) {
		try (Connection conn = db.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM alumnos where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getDate("fechanacimiento"));
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
			while (rs.next()) {
				alumnos.add(
						new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getDate("fechanacimiento"))
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return alumnos;
		}
	}
	
	@Override
	public Alumno insert(Alumno entity) {
		try (Connection conn = db.getConnection()) {
			String sql = "INSERT INTO alumnos (nombre, apellidos, fechanacimiento) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getApellidos());
			ps.setDate(3, entity.getFechaNacimiento());
			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                entity.setDni(generatedKeys.getString(1));
		            }
		            else {
		                throw new SQLException("Error. No se pudo crear el alumno. No se recibio ningun ID.");
		            }
		        }
			} else {
				throw new SQLException("Error. No se pudo crear el alumno. Ninguna fila afectada.");
			}
		} catch (SQLException e) {
			throw new SQLException("Error al guardar alumno. No se pudo guardar.");
		} finally {
			return entity;
		}
	}

	@Override
	public boolean update(Alumno entity) {
		try (Connection conn = db.getConnection()) {
			String sql = "UPDATE alumnos SET (nombre = ?, apellidos = ?, fechanacimiento = ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getNombre());
			ps.setString(2, entity.getApellidos());
			ps.setDate(3, entity.getFechaNacimiento());
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
	public boolean delete(Integer id) {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE  FROM alumnos WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
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
