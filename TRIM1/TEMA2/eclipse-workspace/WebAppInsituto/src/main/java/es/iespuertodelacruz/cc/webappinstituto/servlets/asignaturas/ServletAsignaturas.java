package es.iespuertodelacruz.cc.webappinstituto.servlets.asignaturas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AsignaturaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Asignatura;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class Asignaturas
 */
public class ServletAsignaturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAsignaturas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null)
			response.sendRedirect(Globals.SERVLET_LOGIN);
		else {
			String paramSearchId = request.getParameter("searchid");
			String paramSearchNombre = request.getParameter("searchnombre");
			String paramSearchCurso = request.getParameter("searchcurso");
			AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
			List<Asignatura> asignaturas = null;
			
			// TODO CODE HERE
			
			session.setAttribute(Globals.ATTRIBUTE_SESSION_LIST_ASIGNATURAS, asignaturas);
			request.getRequestDispatcher(Globals.JSP_ASIGNATURAS).forward(request, response);
			session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		}
	
	}
}
