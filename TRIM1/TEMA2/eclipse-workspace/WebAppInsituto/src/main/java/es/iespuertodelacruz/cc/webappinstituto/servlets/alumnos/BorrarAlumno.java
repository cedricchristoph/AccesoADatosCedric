package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AlumnoDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class BorrarAlumno
 */
public class BorrarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarAlumno() {
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
			String paramDni = request.getParameter(Globals.PARAM_ALUMNO_BORRAR_DNI);
			if (paramDni != null && !paramDni.isEmpty()) {
				AlumnoDAO alumnoDao = new AlumnoDAO(db);
				if (alumnoDao.delete(paramDni)) {
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Se ha borrado el alumno con dni " + paramDni);
				} else {
					session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "No se ha podido borrar el alumno");
				}
			} else {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "Debe introducir un dni a borrar");
			}
		}
		response.sendRedirect(Globals.SERVLET_ALUMNOS);
	}

}
