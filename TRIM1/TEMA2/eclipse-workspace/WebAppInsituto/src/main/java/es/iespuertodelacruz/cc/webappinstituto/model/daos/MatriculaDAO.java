package es.iespuertodelacruz.cc.webappinstituto.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.cc.webappinstituto.contracts.AlumnoEntry;
import es.iespuertodelacruz.cc.webappinstituto.contracts.AsignaturaMatriculaEntry;
import es.iespuertodelacruz.cc.webappinstituto.contracts.MatriculaEntry;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.*;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

public class MatriculaDAO extends MatriculaEntry implements ICRUD<Matricula, Integer> {

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
			String sqlMatricula = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = ?";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alumno = alumnoDao.select(rs.getString(DNI));
				matricula = new Matricula(rs.getInt(ID), alumno, rs.getInt(YEAR));
				break;
			}
			ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
			String sqlAsignaturaMatricula = "SELECT * FROM " + AsignaturaMatriculaEntry.TABLE_NAME + " WHERE " + AsignaturaMatriculaEntry.IDMATRICULA + " = ?";
			PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
			ps2.setInt(1, matricula.getId());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Asignatura a = asignaturaDao.select(rs2.getInt(AsignaturaMatriculaEntry.IDASIGNATURA));
				asignaturas.add(a);
			}
			matricula.setAsignaturas(asignaturas);
			return matricula;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public List<Matricula> selectByYear(int year) {
		AlumnoDAO alumnoDao = new AlumnoDAO(db);
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try (Connection conn = db.getConnection()) {
			String sqlMatricula = "SELECT * FROM " + TABLE_NAME + " WHERE " + YEAR + " = ?";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula);
			ps.setInt(1, year);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alumno = alumnoDao.select(rs.getString(DNI));
				Matricula matricula = new Matricula(rs.getInt(ID), alumno, rs.getInt(YEAR));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				String sqlAsignaturaMatricula = "SELECT * FROM " + AsignaturaMatriculaEntry.TABLE_NAME + " WHERE " + AsignaturaMatriculaEntry.IDMATRICULA + " = ?";
				PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
				ps2.setInt(1, matricula.getId());
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Asignatura a = asignaturaDao.select(rs2.getInt(AsignaturaMatriculaEntry.IDASIGNATURA));
					asignaturas.add(a);
				}
				matricula.setAsignaturas(asignaturas);
				matriculas.add(matricula);
			}
			return matriculas;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public List<Matricula> selectByDni(String dni) {
		AlumnoDAO alumnoDao = new AlumnoDAO(db);
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try (Connection conn = db.getConnection()) {
			String sqlMatricula = "SELECT * FROM " + TABLE_NAME + " WHERE " + DNI + " = ?";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alumno = alumnoDao.select(rs.getString(DNI));
				Matricula matricula = new Matricula(rs.getInt(ID), alumno, rs.getInt(YEAR));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				String sqlAsignaturaMatricula = "SELECT * FROM " + AsignaturaMatriculaEntry.TABLE_NAME + " WHERE " + AsignaturaMatriculaEntry.IDMATRICULA + " = ?";
				PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
				ps2.setInt(1, matricula.getId());
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Asignatura a = asignaturaDao.select(rs2.getInt(AsignaturaMatriculaEntry.IDASIGNATURA));
					asignaturas.add(a);
				}
				matricula.setAsignaturas(asignaturas);
				matriculas.add(matricula);
			}
			return matriculas;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public List<Matricula> selectByYearAndDni(int year, String dni) {
		AlumnoDAO alumnoDao = new AlumnoDAO(db);
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try (Connection conn = db.getConnection()) {
			String sqlMatricula = "SELECT * FROM " + TABLE_NAME + " WHERE " + YEAR + " = ? AND " + DNI + " = ?";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula);
			ps.setInt(1, year);
			ps.setString(2, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alumno = alumnoDao.select(rs.getString(DNI));
				Matricula matricula = new Matricula(rs.getInt(ID), alumno, rs.getInt(YEAR));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				String sqlAsignaturaMatricula = "SELECT * FROM " + AsignaturaMatriculaEntry.TABLE_NAME + " WHERE " + AsignaturaMatriculaEntry.IDMATRICULA + " = ?";
				PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
				ps2.setInt(1, matricula.getId());
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Asignatura a = asignaturaDao.select(rs2.getInt(AsignaturaMatriculaEntry.IDASIGNATURA));
					asignaturas.add(a);
				}
				matricula.setAsignaturas(asignaturas);
				matriculas.add(matricula);
			}
			return matriculas;
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
			String sqlMatricula = "SELECT * FROM " + TABLE_NAME;
			Statement stmt = conn.createStatement();
			ResultSet matriculasResultSet = stmt.executeQuery(sqlMatricula);
			while (matriculasResultSet.next()) {
				Alumno alumno = alumnoDao.select(matriculasResultSet.getString(DNI));
				Matricula matricula = new Matricula(matriculasResultSet.getInt(ID), alumno, matriculasResultSet.getInt(YEAR));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				String sqlAsignaturaMatricula = "SELECT * FROM " + AsignaturaMatriculaEntry.TABLE_NAME + " WHERE " + AsignaturaMatriculaEntry.IDMATRICULA + " = ?";
				PreparedStatement ps2 = conn.prepareStatement(sqlAsignaturaMatricula);
				ps2.setInt(1, matricula.getId());
				ResultSet matriculasAsignaturasResultSet = ps2.executeQuery();
				while (matriculasAsignaturasResultSet.next()) {
					Asignatura a = asignaturaDao.select(matriculasAsignaturasResultSet.getInt(AsignaturaMatriculaEntry.IDASIGNATURA));
					asignaturas.add(a);
				}
				matricula.setAsignaturas(asignaturas);
				matriculas.add(matricula);
			}
			return matriculas;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Matricula insert(Matricula entity) throws SQLException {
		try (Connection conn = db.getConnection()) {
			conn.setAutoCommit(false);
			String sqlMatricula = "INSERT INTO " + TABLE_NAME + " (" + DNI + ", " + YEAR + ") VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sqlMatricula, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, entity.getAlumno().getDni());
			ps.setInt(2, entity.getYear());
			int affectedRows = ps.executeUpdate();
			if (affectedRows <= 0) {
				conn.rollback();
				conn.setAutoCommit(true);
				throw new SQLException("Error. No se pudo crear la matrícula. Ninguna fila afectada.");
			} else {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					entity.setId(rs.getInt(1));
					System.out.println("El id generado es " + entity.getId());
				}
			}
			for (Asignatura asignatura : entity.getAsignaturas()) {
				String sql = "INSERT INTO " + AsignaturaMatriculaEntry.TABLE_NAME + " ("
						+ AsignaturaMatriculaEntry.IDMATRICULA + ", " + AsignaturaMatriculaEntry.IDASIGNATURA + ") VALUES (?,?)";
				PreparedStatement ps2 = conn.prepareStatement(sql);
				ps2.setInt(1, entity.getId());
				ps2.setInt(2, asignatura.getId());
				int ok = ps2.executeUpdate();
				if (ok <= 0) {
					conn.rollback();
					conn.setAutoCommit(true);
					throw new SQLException("Error al vincular relaciones con asignaturas en la matrícula");
				}
			}	
			conn.commit();
			conn.setAutoCommit(true);
			return entity;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
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
			conn.setAutoCommit(false);
			String sqlRelaciones = "DELETE FROM " + AsignaturaMatriculaEntry.TABLE_NAME + " WHERE " +
					AsignaturaMatriculaEntry.IDMATRICULA + " = ?";
			PreparedStatement psRelations = conn.prepareStatement(sqlRelaciones);
			psRelations.setInt(1, id);
			psRelations.executeUpdate();
			String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int ok = ps.executeUpdate();
			if (ok == 0) {
				conn.rollback();
				conn.setAutoCommit(true);
				throw new SQLException("No se pudo eliminar el registro");
			}
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	

}
