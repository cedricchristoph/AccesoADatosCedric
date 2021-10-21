package es.iespuertodelacruz.cc.entities;

public class Usuario {

	/**
	 * Variables de clase Usuario
	 */
	String id;
	String nombre;
	
	/**
	 * Constructor de la clase Usuario
	 * @param id Identificador de usuario
	 * @param nombre Nombre del usuario
	 */
	public Usuario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	/**
	 * Getters & Setters 
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
