package es.iespuertodelacruz.cc.institutorest.dto;

import java.util.List;

import es.iespuertodelacruz.cc.institutorest.entity.Asignatura;
import es.iespuertodelacruz.cc.institutorest.entity.Matricula;

public class AsignaturaDTO {

	private int idasignatura;
	private String curso;
	private String nombre;
	private List<Matricula> matriculas;

	public AsignaturaDTO() {
		
	}
	
	public AsignaturaDTO(Asignatura asignatura) {
		this.idasignatura = asignatura.getIdasignatura();
		this.curso = asignatura.getCurso();
		this.nombre = asignatura.getNombre();
		this.matriculas = asignatura.getMatriculas();
	}

	public int getIdasignatura() {
		return this.idasignatura;
	}

	public void setIdasignatura(int idasignatura) {
		this.idasignatura = idasignatura;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public Asignatura toAsignatura() {
		return new Asignatura(this);
	}
}
