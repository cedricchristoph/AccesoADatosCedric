package es.iespuertodelacruz.cc.webappinstituto.model.entities;

/**
 * Clase User
 * @author dama
 *
 */
public class User {
	
	/**
	 * Variables de clase User
	 */
	private String user;
	private String email;
	private String hashPwd;
	
	/**
	 * Constructor por defecto
	 */
	public User() {
		
	}
	
	/**
	 * Constructor completo
	 * @param user Nombre de usuario
	 * @param email Email del usuario
	 * @param hashPwd Contraseña en forma hash del usuario
	 */
	public User(String user, String email, String hashPwd) {
		super();
		this.user = user;
		this.email = email;
		this.hashPwd = hashPwd;
	}

	
	// Getters and Setters
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashPwd() {
		return hashPwd;
	}

	public void setHashPwd(String hashPwd) {
		this.hashPwd = hashPwd;
	}
	
	
	
}
