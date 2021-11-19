package es.iespuertodelacruz.cc.webapprental.utils;

/**
 * Clase global que contiene constantes estáticas
 * @author Cedric Christoph
 */
public class Globals {

	/* SERVLETS */
	public static final String SERVLET_LOGIN = "login";
	public static final String SERVLET_CLIENTES = "clientes";
	
	/* JSPs */
	public static final String JSP_LOGIN = "login.jsp";
	public static final String JSP_CLIENTES = "clientes.jsp";
	
	/* ATRIBUTOS DE APLICACION */
	public static final String ATT_APP_ENTITY_MANAGER_FACTORY = "appemf";
	
	
	/* ATRIBUTOS DE SESION */
	public static final String ATT_SESSION_MSG = "message";
	public static final String ATT_SESSION_INFOMSG = "infoMessage";
	public static final String ATT_SESSION_ERRMSG = "errorMessage";
	
	public static final String ATT_SESSION_LOGGED_USER = "user";
	
	/* PARAMETROS */
		/* PARAMETROS DE LOGIN */
	public static final String PARAM_LOGIN_USER = "loginuser";
	public static final String PARAM_LOGIN_PWD = "loginpwd";
	
}
