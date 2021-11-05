package es.iespuertodelacruz.cc.webappinstituto.model.utils;

public class Globals {

	/* SERVLETS */
	public static final String SERVLET_ALUMNOS = "alumnos";
	public static final String SERVLET_ASIGNATURAS = "asignaturas";
	public static final String SERVLET_MATRICULAS = "matriculas";
	
	/* JSPs */
	public static final String JSP_ALUMNOS = "gestionarAlumnos.jsp";
	public static final String JSP_ASIGNATURAS = "gestionarAsignaturas.jsp";
	public static final String JSP_MATRICULAS = "gestionarMatriculas.jsp";
	public static final String JSP_INICIO = "index.jsp";
	public static final String JSP_LOGIN = "login.jsp";
	
	/* ATRIBUTOS SESION */
	public static final String ATTRIBUTE_SESSION_DB_INSTANCE = "databaseinstance";
	public static final String ATTRIBUTE_SESSION_LIST_ALUMNOS = "alumnoslist";
	public static final String ATTRIBUTE_SESSION_LIST_ASIGNATURAS = "asignaturaslist";
	public static final String ATTRIBUTE_SESSION_LIST_MATRICULAS = "matriculaslist";
	public static final String ATTRIBUTE_SESSION_ERROR_MSG = "errorMessage";
	public static final String ATTRIBUTE_SESSION_MSG = "message";
	
	/* ATRIBUTOS APLICACION */
	
	/* PARAMETROS */
	public static final String PARAM_LOGIN_USER = "loginuser";
	public static final String PARAM_LOGIN_PWD = "loginpwd";
	
	public static final String PARAM_ALUMNO_AGREGAR_DNI = "agregaralumnodni";
	public static final String PARAM_ALUMNO_AGREGAR_NOMBRE = "agregaralumnonombre";
	public static final String PARAM_ALUMNO_AGREGAR_APELLIDOS = "agregaralumnoapellidos";
	public static final String PARAM_ALUMNO_AGREGAR_FECHA = "agregaralumnofecha";
}
