package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class MostrarAlumno
 */
public class MostrarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**S
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null)
			response.sendRedirect(Globals.SERVLET_LOGIN);
		else {
			String paramDni = request.getParameter(Globals.PARAM_ALUMNO_BUSCAR_DNI);
			String paramNombre = request.getParameter(Globals.PARAM_ALUMNO_BUSCAR_NOMBRE);
			if (paramDni != null && !paramDni.isEmpty() && paramNombre != null && !paramNombre.isEmpty()) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "No se puede filtrar varios valores simultáneos");
				response.sendRedirect(Globals.SERVLET_ALUMNOS);
				return;
			}	
			if (paramDni != null && !paramDni.isEmpty()) {
				response.sendRedirect(Globals.SERVLET_ALUMNOS + "?searchdni=" + paramDni);				
				return;
			}
			if (paramNombre != null && !paramNombre.isEmpty()) {
				response.sendRedirect(Globals.SERVLET_ALUMNOS + "?searchnombre=" + paramNombre);
				return;
			}
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "No se ha introducido valores para filtrar");
			response.sendRedirect(Globals.SERVLET_ALUMNOS);
		}
	}

}
