package es.iespuertodelacruz.cc.webappinstituto.servlets.matriculas;

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
 * Servlet implementation class MostrarMatricula
 */
public class MostrarMatricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarMatricula() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			try {
				String url = "";
				String paramYear = request.getParameter(Globals.PARAM_MATRICULA_MOSTRAR_YEAR);
				String paramDni = request.getParameter(Globals.PARAM_MATRICULA_MOSTRAR_DNI);
				Integer year = null;
				if (paramYear != null && !paramYear.isEmpty())
					try {
						year = Integer.parseInt(paramYear);
					} catch (Exception e) {
						throw new Exception("El valor en año no es válido");
					}
				if (year != null)
					url += "?searchyear=" + year;
				if (paramDni != null && !paramDni.isEmpty()) {
					if (year == null)
						url += "?searchdni=" + paramDni;
					else
						url += "&searchdni=" + paramDni;
				}
				response.sendRedirect(Globals.SERVLET_MATRICULAS + url);
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_MATRICULAS);
			}
		}
	}

}
