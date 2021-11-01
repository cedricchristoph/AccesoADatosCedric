package es.iespuertodelacruz.cc.entities;

import java.util.ArrayList;

/**
 * Clase encargada de gestionar los datos relacionados con el usuario
 * @author Cedric Christoph
 *
 */
public class Usuario {
	/**
	 * Variables de clase Usuario
	 */
	String id;
	String nombre;
	ArrayList<Numero> numeros;
	
	/**
	 * Constructor de la clase Usuario
	 * @param id Identificador de usuario
	 * @param nombre Nombre del usuario
	 */
	public Usuario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		numeros = new ArrayList<Numero>();
	}
	
	/**
	 * Metodo para añadir un numero a la lista de numeros de un usuario
	 * @param numero Numero a añadir
	 */
	public void addNumero(Numero numero) {
		numeros.add(numero);
	}
	
	/**
	 * Metodo para vaciar la lista de numeros de un usuario
	 */
	public void clearNumeros() {
		numeros = new ArrayList<Numero>();
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
	public ArrayList<Numero> getNumeros() {
		return numeros;
	}
	public void setNumeros(ArrayList<Numero> numeros) {
		this.numeros = numeros;
	}
	@Override
	public String toString() {
		return nombre;
	}
}
