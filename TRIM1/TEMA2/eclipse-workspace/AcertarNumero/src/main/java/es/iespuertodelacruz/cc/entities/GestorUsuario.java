package es.iespuertodelacruz.cc.entities;

import java.util.Vector;

/**
 * Clase para gestionar el trafico de usuarios conectados
 * @author Cedric Christoph
 *
 */
public class GestorUsuario {

	/**
	 * Variables de la clase GestorUsuario
	 */
	private Vector<Usuario> usuarios;
	
	/**
	 * Constructor de la clase GestorUsuario
	 */
	public GestorUsuario() {
		this.usuarios = new Vector<Usuario>();
	}
	
	/**
	 * Metodo para añadir un usuario a la lista de conectados
	 * @param user Objeto Usuario a añadir
	 */
	public void add(Usuario user) {
		usuarios.add(user);
	}
	
	/**
	 * Funcion que devuelve un usuario de la lista con un ID de sesion especifico
	 * @param id ID del usuario, equivalente al ID de la sesion
	 * @return Devuelve Objeto usuario con el ID o null si no se encontro ningun usuario
	 */
	public Usuario get(String id) {
		try {
			return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Metodo para eliminar un usuario de la lista
	 * @param id ID del usuario a eliminar
	 */
	public void remove(String id) {
		Usuario userToRemove = get(id);
		if (userToRemove != null)
			usuarios.remove(get(id));
	}
	
	/**
	 * Funcion que devuelve la lista entera de usuarios conectados
	 * @return Vector<Usuario> Usuarios conectados
	 */
	public Vector<Usuario> getAll() {
		return usuarios;
	}
	
	/**
	 * Funcion que devuelve una lista de nombres de usuarios conectados
	 * @return Lista de nombres de usuarios conectados
	 */
	public Vector<String> getAllUserNames() {
		Vector<String> nombres = new Vector<String>();
		getAll().stream().forEach(u -> nombres.add(u.getNombre()));
		return nombres;
	}
	
}
