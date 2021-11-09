package es.iespuertodelacruz.cc.webappinstituto.model.entities;

import java.util.ArrayList;

public class Matricula {

	private int id;
	private Alumno alumno;
	private int year;
	private ArrayList<Asignatura> asignaturas;
	
	/**
	 * Constructor por defecto
	 */
	public Matricula() {
		
	}
	
	/**
	 * Constructor de Matricula
	 * @param Alumno al que le pertenece esta matricula
	 * @param year Año de la creacion de la matricula
	 * @param asignaturas Asignaturas que se matriculan
	 */
	public Matricula(Alumno alumno, int year, ArrayList<Asignatura> asignaturas) {
		this.alumno = alumno;
		this.year = year;
		this.asignaturas = asignaturas;
	}
	
	/**
	 * Constructor para MatriculaDAO
	 * @param id
	 * @param alumno
	 * @param year
	 */
	public Matricula(Integer id, Alumno alumno, int year) {
		this.id = id;
		this.alumno = alumno;
		this.year = year;
	}
	
	/**
	 * Constructor completo
	 * @param id
	 * @param alumno
	 * @param year
	 * @param asignaturas
	 */
	public Matricula(Integer id, Alumno alumno, int year, ArrayList<Asignatura> asignaturas) {
		this.id = id;
		this.alumno = alumno;
		this.year = year;
		this.asignaturas = asignaturas;
	}
	
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ArrayList<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	
}
