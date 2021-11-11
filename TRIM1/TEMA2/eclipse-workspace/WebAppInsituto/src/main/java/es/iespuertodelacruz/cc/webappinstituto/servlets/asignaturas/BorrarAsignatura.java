package es.iespuertodelacruz.cc.webappinstituto.servlets.asignaturas;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AsignaturaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class BorrarAsignatura
 */
public class BorrarAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			String paramId = request.getParameter(Globals.PARAM_ASIGNATURA_BORRAR_ID);
			try {
				if (paramId != null && !paramId.isEmpty()) {
					Integer id = null;
					try {
						id = Integer.parseInt(paramId);
					} catch (Exception e) {
						throw new Exception("El valor para ID no es válido");
					}
					AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
					if (!asignaturaDao.delete(id))
						throw new Exception("No se pudo borrar la asignatura con ID " + id);
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Se borró la asignatura con ID " + id);
					response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
				} else {
					throw new Exception("Por favor rellene los campos obligatorios");
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
			}
		}
	}

}
