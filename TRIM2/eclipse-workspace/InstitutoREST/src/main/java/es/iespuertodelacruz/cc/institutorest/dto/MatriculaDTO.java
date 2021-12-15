package es.iespuertodelacruz.cc.institutorest.dto;

import java.util.List;
import es.iespuertodelacruz.cc.institutorest.entity.Alumno;
import es.iespuertodelacruz.cc.institutorest.entity.Asignatura;
import es.iespuertodelacruz.cc.institutorest.entity.Matricula;

public class MatriculaDTO {
	private int idmatricula;
	private int year;
	private Alumno alumno;
	private List<Asignatura> asignaturas;

	public MatriculaDTO() {
		
	}
	
	public MatriculaDTO(Matricula matricula) {
		this.idmatricula = matricula.getIdmatricula();
		this.year = matricula.getYear();
		this.alumno = matricula.getAlumno();
		this.asignaturas = matricula.getAsignaturas();
	}

	public int getIdmatricula() {
		return this.idmatricula;
	}

	public void setIdmatricula(int idmatricula) {
		this.idmatricula = idmatricula;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public Matricula toMatricula() {
		return new Matricula(this);
	}
}
