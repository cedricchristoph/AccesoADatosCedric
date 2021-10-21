package es.iespuertodelacruz.cc.entities;

public class Mensaje {

	/**
	 * Variables de la clase Mensaje
	 */
	Usuario user;
	String mensaje;
	
	/**
	 * Constructor de la clase MensajeS
	 * @param user
	 * @param mensaje
	 */
	public Mensaje(Usuario user, String mensaje) {
		this.user = user;
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "[" + user.getNombre() + "]: " + mensaje;
	}
	
	/**
	 * Getters & Setters
	 */
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
