package es.iespuertodelacruz.cc.webappinstituto.servlets.matriculas;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.MatriculaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class BorrarMatricula
 */
public class BorrarMatricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarMatricula() {
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
				String paramBorrarId = request.getParameter(Globals.PARAM_MATRICULA_BORRAR_ID);
				Integer id = null;
				try {
					id = Integer.parseInt(paramBorrarId);
				} catch (Exception e) {
					throw new Exception("El valor de ID no es válido");
				}
				MatriculaDAO matriculaDao = new MatriculaDAO(db);
				if (!matriculaDao.delete(id))
					throw new Exception("No se pudo borrar la matrícula");
				session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Matrícula eliminada correctamente");
				response.sendRedirect(Globals.SERVLET_MATRICULAS);
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_MATRICULAS);
			}
		}
	}

}
