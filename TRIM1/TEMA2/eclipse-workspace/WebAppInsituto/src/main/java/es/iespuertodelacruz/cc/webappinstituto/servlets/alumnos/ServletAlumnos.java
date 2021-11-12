package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import es.iespuertodelacruz.cc.webappinstituto.model.utils.JsonConverter;
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
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null)
			response.sendRedirect(Globals.SERVLET_LOGIN);
		else {
			AlumnoDAO alumnoDao = new AlumnoDAO(db);
			List<Alumno> alumnos = null;

			String paramSearchDni = request.getParameter("searchdni");
			String paramSearchNombre = request.getParameter("searchnombre");
			if (paramSearchDni != null && !paramSearchDni.isEmpty() && paramSearchNombre == null) {
				alumnos = new ArrayList<Alumno>();
				alumnos.add(alumnoDao.select(paramSearchDni));
			}
			if (paramSearchNombre != null && !paramSearchNombre.isEmpty() && paramSearchDni == null) {
				alumnos = alumnoDao.selectByName(paramSearchNombre);
			}
			if (paramSearchNombre == null && paramSearchDni == null) {
				alumnos = (ArrayList<Alumno>) alumnoDao.selectAll();
				
			}
			if (alumnos != null && alumnos.isEmpty()) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "La búsqueda no obtuvo resultados");
			}
			
			session.setAttribute(Globals.ATTRIBUTE_SESSION_JSON_ALUMNOS, 
					new JsonConverter<Alumno>().convertToJson(alumnos));
			session.setAttribute(Globals.ATTRIBUTE_SESSION_LIST_ALUMNOS, alumnos);
			request.getRequestDispatcher(Globals.JSP_ALUMNOS).forward(request, response);
			session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		}

	}
}
