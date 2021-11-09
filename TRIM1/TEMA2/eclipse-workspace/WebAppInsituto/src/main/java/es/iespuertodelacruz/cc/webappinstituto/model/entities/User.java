package es.iespuertodelacruz.cc.webappinstituto.model.entities;

import org.mindrot.jbcrypt.BCrypt;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;

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
	private boolean active;
	
	/**
	 * Constructor por defecto
	 */
	public User() {
		
	}
	
	/**
	 * Constructor completo
	 * @param user Nombre de usuario
	 * @param email Email del usuario
	 * @param plainPwd Contraseña del usuario en texto plano
	 * @param active Valor que indica si la cuenta esta activa o no
	 * @param hashPwd Determina si plainPwd será encriptado o no
	 */
	public User(String user, String email, String plainPwd, boolean active, boolean hashPwd) {
		super();
		this.user = user;
		this.email = email;
		this.active = active;
		if (hashPwd)
			this.hashPwd = BCrypt.hashpw(plainPwd, BCrypt.gensalt(Globals.BCRYPT_SALTS));
		else
			this.hashPwd = plainPwd;
	}

	/**
	 * Funcion que determina si la contraseña es correcta
	 * @param plainPwd Contraseña en forma de texto plano
	 * @return Devuelve true y solo true si la contraseña es correcta.
	 */
	public boolean checkPwd(String plainPwd) {
		return BCrypt.checkpw(plainPwd, hashPwd);
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
