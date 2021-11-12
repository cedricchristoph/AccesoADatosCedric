package es.iespuertodelacruz.cc.webappinstituto.servlets.asignaturas;

import java.io.IOException;
import java.util.ArrayList;
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
import es.iespuertodelacruz.cc.webappinstituto.model.utils.JsonConverter;
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
		request.setCharacterEncoding("UTF-8");
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
			try {
				/* FILTROS DE BUSQUEDA */
				// Filtrar por ID
				if (paramSearchId != null && !paramSearchId.isEmpty()
						&& paramSearchNombre == null && paramSearchCurso == null) {
					Integer id = null;
					try {
						id = Integer.parseInt(paramSearchId);
					} catch (Exception e) {
						throw new Exception("El valor indicado para ID no es válido");
					}
					asignaturas = new ArrayList<Asignatura>();
					asignaturas.add(asignaturaDao.select(id));
				}
				// Filtrar por nombre
				if (paramSearchNombre != null && !paramSearchNombre.isEmpty()
						&& paramSearchId == null && paramSearchCurso == null) {
					asignaturas = asignaturaDao.selectByNombre(paramSearchNombre);
				}
				// Filtrar por curso
				if (paramSearchCurso != null && !paramSearchCurso.isEmpty()
						&& paramSearchId == null && paramSearchNombre == null) {
					asignaturas = asignaturaDao.selectByCurso(paramSearchCurso);
				}
				// Mostrar todos
				if (paramSearchId == null && paramSearchNombre == null && paramSearchCurso == null) {
					asignaturas = asignaturaDao.selectAll();
				}
				
				if (asignaturas != null && asignaturas.isEmpty())
					session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "La búsqueda no obtuvo resultados");
				
				session.setAttribute(Globals.ATTRIBUTE_SESSION_JSON_ASIGNATURAS, 
						new JsonConverter<Asignatura>().convertToJson(asignaturas));
				session.setAttribute(Globals.ATTRIBUTE_SESSION_LIST_ASIGNATURAS, asignaturas);
				request.getRequestDispatcher(Globals.JSP_ASIGNATURAS).forward(request, response);
				session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
				session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
			
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
			}
		}
	
	}
}
