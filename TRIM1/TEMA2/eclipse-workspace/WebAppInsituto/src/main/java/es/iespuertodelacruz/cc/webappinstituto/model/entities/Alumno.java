package es.iespuertodelacruz.cc.webappinstituto.model.entities;

import java.sql.Date;

public class Alumno {
	
	/**
	 * Variables de clase alumnos
	 */
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	
	/**
	 * Constructor por defecto
	 */
	public Alumno() {
		
	}
	
	/**
	 * Constructor completo
	 * @param dni del alumno
	 * @param nombre del alumno
	 * @param apellidos del alumno
	 * @param fechaNacimiento del alumno
	 */
	public Alumno(String dni, String nombre, String apellidos, Date fechaNacimiento) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	
	// Getters and Setters
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
}
