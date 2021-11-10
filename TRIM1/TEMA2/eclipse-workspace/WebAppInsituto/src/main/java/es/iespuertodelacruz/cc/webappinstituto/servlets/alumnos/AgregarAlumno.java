package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;
import es.iespuertodelacruz.cc.webappinstituto.model.daos.AlumnoDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;

/**
 * Servlet implementation class AgregarAlumno
 */
public class AgregarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			try {
				String dni = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_DNI);
				String nombre = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_NOMBRE);
				String apellidos = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_APELLIDOS);
				String strFechaNacimiento = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_FECHA);
				Date fechaNacimiento = null;
				if (dni != null && !dni.isEmpty() && nombre != null && !nombre.isEmpty() && strFechaNacimiento != null) {
					try {
						java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strFechaNacimiento);
						fechaNacimiento = new Date(date.getTime());
					} catch (ParseException e) {
						throw new Exception("Error al parsear la fecha de nacimiento");
					}
					Alumno alumno = new Alumno(dni, nombre, apellidos, fechaNacimiento);
					AlumnoDAO alumnoDao = new AlumnoDAO(db);
					Alumno output = alumnoDao.insert(alumno);
					if (output != null) {
						session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Alumno guardado correctamente");
						response.sendRedirect(Globals.SERVLET_ALUMNOS);
					} else {
						throw new Exception("No se pudo crear el alumno");
					}
				} else {
					throw new Exception("Por favor rellene los campos obligatorios");
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ALUMNOS);
			}
		}
	}

}
