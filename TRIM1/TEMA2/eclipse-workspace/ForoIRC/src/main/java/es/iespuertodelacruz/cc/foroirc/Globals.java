package es.iespuertodelacruz.cc.foroirc;

import es.iespuertodelacruz.cc.entities.FileManager;

/**
 * Clase que contiene todas las variables/constantes globales de la aplicacion
 * @author Cedric Christoph
 *
 */
public class Globals {

	/* ATTRIBUTES */
	public static final String ATTRIBUTE_MENSAJES_NEW = "mensajesnew";
	public static final String ATTRIBUTE_MENSAJES = "mensajes";
	public static final String ATTRIBUTE_USERS = "usuarios";
	public static final String ATTRIBUTE_USERNAME = "user";
	public static final String ATTRIBUTE_CONNECTED_USERS = "connected";
	
	/* FORM FIELDS */
	public static final String FIELD_MENSAJE = "mensaje";
	public static final String FIELD_NOMBRE = "nombre";
	
	/* PAGES */
	public static final String JSP_VISTA = "vista.jsp";
	
	/* SERVLETS */
	public static final String SERVLET_PRINCIPAL = "principal";
	
	/* OBJECTS */
	public static final String FILE_MANAGER_CHAT = "filemanagerchat";
	
	/* CONSTANTES */
	public static final long TIMEOUT = (60000*5); // milisegundos: 1 minuto * 5
}
