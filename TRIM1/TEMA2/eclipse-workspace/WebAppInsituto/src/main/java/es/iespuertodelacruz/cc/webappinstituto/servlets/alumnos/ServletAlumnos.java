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
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class Alumnos
 */
public class ServletAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAlumnos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
		
		MyDatabase db = (MyDatabase) session.getAttribute(Globals.ATTRIBUTE_SESSION_DB_INSTANCE);
		if (db == null)
			response.sendRedirect(Globals.JSP_LOGIN);
		else {
			AlumnoDAO alumnoDao = new AlumnoDAO(db);
			ArrayList<Alumno> alumnos = (ArrayList<Alumno>) alumnoDao.selectAll();
			session.setAttribute(Globals.ATTRIBUTE_SESSION_LIST_ALUMNOS, alumnos);
			request.getRequestDispatcher(Globals.JSP_ALUMNOS).forward(request, response);
		}	
		
	}
}
