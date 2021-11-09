package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.*;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class MatriculaDAO implements ICRUD<Matricula, Integer> {

	MyDatabase db;
	
	public MatriculaDAO(MyDatabase db) {
		this.db = db;
	}
	
	@Override
	public Matricula select(Integer id) {
		AlumnoDAO alumnoDao = new AlumnoDAO(db);
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
		Matricula matricula = null;
		try (Connection conn = db.getConnection()) {
			String sqlMatricula = "SELECT * FROM matriculas WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alumno = alumnoDao.select(rs.getString("dni"));
				matricula = new Matricula(rs.getInt("id"), alumno, rs.getInt("year"));
				break;
			}
			ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
			String sqlAsignaturaMatricula = "SELECT * FROM asignatura_matricula WHERE idMatricula = ?";
			PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
			ps2.setInt(1, matricula.getId());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Asignatura a = asignaturaDao.select(rs2.getInt("id"));
				asignaturas.add(a);
			}
			matricula.setAsignaturas(asignaturas);
			return matricula;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Matricula> selectAll() {
		AlumnoDAO alumnoDao = new AlumnoDAO(db);
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try (Connection conn = db.getConnection()) {
			String sqlMatricula = "SELECT * FROM matriculas";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlMatricula);
			while (rs.next()) {
				Alumno alumno = alumnoDao.select(rs.getString("dni"));
				Matricula matricula = new Matricula(rs.getInt("id"), alumno, rs.getInt("year"));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				String sqlAsignaturaMatricula = "SELECT * FROM asignatura_matricula WHERE idMatricula = ?";
				PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
				ps2.setInt(1, matricula.getId());
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Asignatura a = asignaturaDao.select(rs2.getInt("id"));
					asignaturas.add(a);
				}
				matricula.setAsignaturas(asignaturas);
			}
			return matriculas;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Matricula insert(Matricula entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sqlMatricula = "INSERT INTO matriculas (dni, year) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getAlumno().getDni());
			ps.setInt(2, entity.getYear());
			int ok = ps.executeUpdate();
			if (ok > 0) {
				ResultSet rsKeys = ps.getGeneratedKeys();
				while (rsKeys.next())
					entity.setId(rsKeys.getInt("id"));
				return entity;
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean update(Matricula entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		try (Connection conn = db.getConnection()) {
			String sql = "DELETE FROM matriculas WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int ok = ps.executeUpdate();
			if (ok > 0)
				return true;
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	

}
