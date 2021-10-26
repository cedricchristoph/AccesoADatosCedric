package es.iespuertodelacruz.cc.entities;

import java.util.Date;

public class Globals {

	/* JSPs */
	public static final String JSP_REGISTRAR_USUARIO = "register.jsp";
	public static final String JSP_ACERTAR_NUMERO = "acertarnumero.jsp";
	
	/* Servlets */
	public static final String SERVLET_REGISTRAR_USUARIO = "registrarusuario";
	public static final String SERVLET_HISTORIAL = "historial";
	
	/* APPLICATION ATTRIBUTES */
	public static final String ATTRIBUTE_APP_NUMBER = "numerosecreto";
	public static final String ATTRIBUTE_APP_NUMBER_CONTROLLER = "numbercontroller";
	public static final String ATTRIBUTE_APP_GESTOR_USUARIOS = "gestorusuarios";
	public static final String ATTRIBUTE_APP_LAST_WINNER = "lastwinner";
	
	/* SESSION ATTRIBUTES */
	public static final String ATTRIBUTE_SESSION_USER = "sessionuser";
	public static final String ATTRIBUTE_SESSION_WIN = "ganador";
	public static final String ATTRIBUTE_SESSION_NUMBERS = "numeros";
	public static final String ATTRIBUTE_SESSION_LAST_NUMBER = "lastnumber";
	
	/* PARAMETER (form fields) */
	public static final String PARAMETER_NOMBRE = "nombre";
	public static final String PARAMETER_NUMERO = "numero";
	
	/* FUNCTIONS */
	public static Numero nuevoNumeroSecreto() {
		int numero = (int) (Math.random() * (10000));
		long millis = (new Date()).getTime();
		return new Numero(millis, numero);
	}
}
