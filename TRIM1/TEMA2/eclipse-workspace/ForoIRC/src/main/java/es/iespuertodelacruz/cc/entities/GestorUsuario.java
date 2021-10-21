package es.iespuertodelacruz.cc.entities;

import java.util.ArrayList;

public class GestorUsuario {

	private ArrayList<Usuario> usuarios;
	
	public GestorUsuario(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Usuario get(String id) {
		return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().get();
	}
	
}
