package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AlumnoDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class EditarAlumno
 */
public class EditarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAlumno() {
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
			response.sendRedirect(Globals.JSP_LOGIN);
		} else {
			try {
			String paramDni = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_DNI);
			String paramNombre = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_NOMBRE);
			String paramApellidos = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_APELLIDOS);
			String paramFechaNacimiento = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_FECHA);
			java.util.Date fecha = null;
			if (paramDni != null && !paramDni.isEmpty()) {
				AlumnoDAO alumnoDao = new AlumnoDAO(db);
				try {
					fecha = new SimpleDateFormat("dd/MM/yyyy").parse(paramFechaNacimiento);
				} catch (ParseException e) {
					throw new Exception("Error al parsear la fecha de nacimiento");
				}
				Alumno alumno = new Alumno(paramDni, paramNombre, paramApellidos, fecha);
				alumnoDao.update(alumno);
			} else {
				throw new Exception("Debe introducir un dni a editar");
			}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			}
		}
		response.sendRedirect(Globals.SERVLET_ALUMNOS);
	}

}
