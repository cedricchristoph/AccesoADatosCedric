package es.iespuertodelacruz.cc.webappinstituto.model.entities;

public class Asignatura {

	/**
	 * Variables de la clase asignatura
	 */
	private Integer id;
	private String nombre;
	private String curso;
	
	/**
	 * Constructor por defecto
	 */
	public Asignatura() {
		
	}
	
	/**
	 * Constructor de la clase asignatura
	 * @param nombre Nombre de la asignatura
	 * @param curso Curso al que pertenece
	 */
	public Asignatura(String nombre, String curso) {
		this.id = null;
		this.nombre = nombre;
		this.curso = curso;
	}
	
	/**
	 * Constructor completo
	 * @param id de la asignatura
	 * @param nombre Nombre de la asignatura
	 * @param  curso Curso al que pertenece
	 */
	public Asignatura(Integer id, String nombre, String curso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
	}

	
	// Getters and Setters

	public String getNombre() {
		return nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
