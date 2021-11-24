package es.iespuertodelacruz.cc.webapprental.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase global que contiene constantes estáticas
 * @author Cedric Christoph
 */
public class Globals {

	/* SERVLETS */
	public static final String SERVLET_LOGIN = "/WebAppRental/login";
	public static final String SERVLET_CLIENTES = "clientes";
	public static final String SERVLET_CLIENTE = "cliente";
	public static final String SERVLET_RENTAL = "alquiler";
	
	/* JSPs */
	public static final String JSP_LOGIN = "login.jsp";
	public static final String JSP_CLIENTES = "home/clientes.jsp";
	public static final String JSP_CLIENTE = "home/cliente.jsp";
	public static final String JSP_RENTAL = "home/alquiler.jsp";
	
	/* ATRIBUTOS DE APLICACION */
	public static final String ATT_APP_ENTITY_MANAGER_FACTORY = "appemf";
	
	
	/* ATRIBUTOS DE SESION */
	public static final String ATT_SESSION_MSG = "message";
	public static final String ATT_SESSION_INFOMSG = "infoMessage";
	public static final String ATT_SESSION_ERRMSG = "errorMessage";
	
	public static final String ATT_SESSION_LOGGED_USER = "user";
	
	public static final String ATT_SESSION_CLIENTS_LIST = "clientlist";
	public static final String ATT_SESSION_SELECTED_CLIENT = "selectedclient";
	public static final String ATT_SESSION_SELECTED_RENTAL = "selectedrental";
	
	/* PARAMETROS */
		/* PARAMETROS DE LOGIN */
	public static final String PARAM_LOGIN_USER = "loginuser";
	public static final String PARAM_LOGIN_PWD = "loginpwd";
	
		/* PARAMETROS DE CLIENTES BUSQUEDA */
	public static final String PARAM_CLIENTES_SEARCH_NAME = "searchname";
	public static final String PARAM_CLIENTES_SEARCH_LASTNAME = "searchlastname";
	
	/* FUNCTIONS */
	public static String getOnlyDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
	    return formatter.format(date);  
	}
	
}
