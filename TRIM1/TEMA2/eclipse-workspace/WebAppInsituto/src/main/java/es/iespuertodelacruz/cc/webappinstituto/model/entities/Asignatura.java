package es.iespuertodelacruz.cc.webappinstituto.model.entities;

public class Asignatura {

	/**
	 * Variables de la clase asignatura
	 */
	private String siglas;
	private String nombre;
	private String curso;
	
	/**
	 * Constructor por defecto
	 */
	public Asignatura() {
		
	}
	
	/**
	 * Constructor completo
	 * @param siglas de la asignatura
	 * @param nombre de la asignatura
	 * @param curso al que se imparte esta asignatura
	 */
	public Asignatura(String siglas, String nombre, String curso) {
		super();
		this.siglas = siglas;
		this.nombre = nombre;
		this.curso = curso;
	}

	
	// Getters and Setters
	
	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
}
