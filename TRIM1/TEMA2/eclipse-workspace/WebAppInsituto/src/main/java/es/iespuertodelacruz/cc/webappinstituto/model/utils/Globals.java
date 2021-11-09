package es.iespuertodelacruz.cc.webappinstituto.model.utils;

public class Globals {

	/* CREDENCIALES DE APLICACION PARA DDBB */
	//public static final String CONST_APP_USER = "webapp";
	//public static final String CONST_APP_PWD = "UrcZCb7qizCbeW2";
	public static final String CONST_APP_USER = "root";
	public static final String CONST_APP_PWD = "1q2w3e4r";
	/* SERVLETS */
	public static final String SERVLET_ALUMNOS = "alumnos";
	public static final String SERVLET_ASIGNATURAS = "asignaturas";
	public static final String SERVLET_MATRICULAS = "matriculas";
	public static final String SERVLET_LOGIN = "login";
	public static final String SERVLET_REGISTRAR = "registrar";
	
	/* JSPs */
	public static final String JSP_ALUMNOS = "gestionarAlumnos.jsp";
	public static final String JSP_ASIGNATURAS = "gestionarAsignaturas.jsp";
	public static final String JSP_MATRICULAS = "gestionarMatriculas.jsp";
	public static final String JSP_INICIO = "index.jsp";
	public static final String JSP_LOGIN = "login.jsp";
	public static final String JSP_REGISTRAR = "register.jsp";
	
	/* ATRIBUTOS APLICACION */
	public static final String ATTRIBUTE_APP_DATABASE = "appdatabase";
	
	/* ATRIBUTOS SESION */
	public static final String ATTRIBUTE_SESSION_USER = "sessionuser";
	public static final String ATTRIBUTE_SESSION_LIST_ALUMNOS = "alumnoslist";
	public static final String ATTRIBUTE_SESSION_LIST_ASIGNATURAS = "asignaturaslist";
	public static final String ATTRIBUTE_SESSION_LIST_MATRICULAS = "matriculaslist";
	public static final String ATTRIBUTE_SESSION_ERROR_MSG = "errorMessage";
	public static final String ATTRIBUTE_SESSION_INFO_MSG = "infoMessage";
	public static final String ATTRIBUTE_SESSION_MSG = "message";
	
	/* ATRIBUTOS APLICACION */
	
	/* PARAMETROS */
	public static final String PARAM_LOGIN_USER = "loginuser";
	public static final String PARAM_LOGIN_PWD = "loginpwd";
	
	public static final String PARAM_REGISTRAR_USER = "registraruser";
	public static final String PARAM_REGISTRAR_EMAIL = "registraremail";
	public static final String PARAM_REGISTRAR_PWD = "registrarpwd";
	
	public static final String PARAM_ALUMNO_AGREGAR_DNI = "agregaralumnodni";
	public static final String PARAM_ALUMNO_AGREGAR_NOMBRE = "agregaralumnonombre";
	public static final String PARAM_ALUMNO_AGREGAR_APELLIDOS = "agregaralumnoapellidos";
	public static final String PARAM_ALUMNO_AGREGAR_FECHA = "agregaralumnofecha";
	
	public static final String PARAM_ALUMNO_BORRAR_DNI = "borraralumnodni";
	
	public static final String PARAM_ALUMNO_EDITAR_DNI = "editaralumnodni";
	public static final String PARAM_ALUMNO_EDITAR_NOMBRE = "editaralumnonombre";
	public static final String PARAM_ALUMNO_EDITAR_APELLIDOS = "editaralumnoapellidos";
	public static final String PARAM_ALUMNO_EDITAR_FECHA = "editaralumnofecha";
	
	/* BCRYPT */
	public static final int BCRYPT_SALTS = 10;
}
