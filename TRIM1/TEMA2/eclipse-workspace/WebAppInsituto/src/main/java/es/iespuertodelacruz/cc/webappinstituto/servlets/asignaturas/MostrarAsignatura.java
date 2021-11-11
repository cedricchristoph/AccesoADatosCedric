package es.iespuertodelacruz.cc.webappinstituto.servlets.asignaturas;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class MostrarAsignatura
 */
public class MostrarAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarAsignatura() {
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
			try {
				int usedFilters = 0;
				String url = "";
				String paramId = request.getParameter(Globals.PARAM_ASIGNATURA_MOSTRAR_ID);
				String paramNombre = request.getParameter(Globals.PARAM_ASIGNATURA_MOSTRAR_NOMBRE);
				String paramCurso = request.getParameter(Globals.PARAM_ASIGNATURA_MOSTRAR_CURSO);
				
				if (paramId != null && !paramId.isEmpty()) {
					usedFilters++;
					Integer id = null;
					try {
						id = Integer.parseInt(paramId);
					} catch (Exception e) {
						throw new Exception("El valor para ID no es válido");
					}
					url = "?searchid=" + id;
				}
				
				if (paramNombre != null && !paramNombre.isEmpty()) {
					usedFilters++;
					url = "?searchnombre=" + paramNombre;
				}
				
				if (paramCurso != null && !paramCurso.isEmpty()) {
					usedFilters++;
					url = "?searchcurso=" + paramCurso;
				}
				
				if (usedFilters > 1)
					throw new Exception("No se puede filtrar varios valores simultáneos");
				response.sendRedirect(Globals.SERVLET_ASIGNATURAS + url);
				
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
			}
		}
	}

}
