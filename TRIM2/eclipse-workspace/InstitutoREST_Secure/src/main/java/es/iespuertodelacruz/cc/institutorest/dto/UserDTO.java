package es.iespuertodelacruz.cc.institutorest.dto;

import org.springframework.security.crypto.bcrypt.BCrypt;

import es.iespuertodelacruz.cc.institutorest.entity.User;

import javax.persistence.*;
/**
 * Clase User
 * @author dama
 *
 */
@Entity
public class UserDTO {
	
	/* CONSTANTES GLOBALES ESTATICAS */
	public static final int ACCESSLEVEL_ADMINISTRATOR = 1;
	public static final int ACCESSLEVEL_STANDARD = 0;
	
	/**
	 * Variables de clase User
	 */
	@Id
	private String user;
	private String email;
	private boolean active;
	@Column
	private int type;
	
	/**
	 * Constructor por defecto
	 */
	public UserDTO() {
		
	}
	
	/**
	 * Constructor completo
	 * @param user Nombre de usuario
	 * @param email Email del usuario
	 * @param plainPwd Contraseña del usuario en texto plano
	 * @param active Valor que indica si la cuenta esta activa o no
	 * @param hashPwd Determina si plainPwd será encriptado o no
	 */
	public UserDTO(String user, String email, boolean active, int type) {
		this.user = user;
		this.email = email;
		this.active = active;
		this.type = type;
	}	
	
	public UserDTO(User user) {
		this.user = user.getUser();
		this.email = user.getEmail();
		this.active = user.isActive();
		this.type = user.getAccessLevel();
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAccessLevelStr() {
		switch (type) {
		case ACCESSLEVEL_ADMINISTRATOR:
			return "ROLE_ADMIN";
		case ACCESSLEVEL_STANDARD:
			return "ROLE_USER";
		default:
			return "Unknown";
		}
	}
	
	
}