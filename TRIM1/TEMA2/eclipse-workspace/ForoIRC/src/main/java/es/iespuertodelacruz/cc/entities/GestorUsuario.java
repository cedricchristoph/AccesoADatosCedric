package es.iespuertodelacruz.cc.entities;

import java.util.Vector;

public class GestorUsuario {

	private Vector<Usuario> usuarios;
	
	public GestorUsuario() {
		this.usuarios = new Vector<Usuario>();
	}
	
	public void add(Usuario user) {
		usuarios.add(user);
	}
	
	public Usuario get(String id) {
		try {
			return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void remove(String id) {
		Usuario userToRemove = get(id);
		if (userToRemove != null)
			usuarios.remove(get(id));
	}
	
	public Vector<Usuario> getAll() {
		return usuarios;
	}
	
	public Vector<String> getAllUserNames() {
		Vector<String> nombres = new Vector<String>();
		getAll().stream().forEach(u -> nombres.add(u.getNombre()));
		return nombres;
	}
	
}
