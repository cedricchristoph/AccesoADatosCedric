package es.iespuertodelacruz.cc.webappinstituto.servlets.matriculas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.MatriculaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Matricula;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class Matriculas
 */
public class ServletMatriculas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMatriculas() {
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
			List<Matricula> matriculas = null;
			MatriculaDAO matriculaDao = new MatriculaDAO(db);
			String paramSearchYear = request.getParameter("searchyear");
			String paramSearchDni = request.getParameter("searchdni");
			try {
				// Filtrar por year
				if (paramSearchYear != null && !paramSearchYear.isEmpty() && paramSearchDni == null) {
					Integer year = null;
					try {
						year = Integer.parseInt(paramSearchYear);
					} catch (Exception e) {
						throw new Exception("El valor de año no es válido");
					}
					matriculas = matriculaDao.selectByYear(year);
				}
				// Filtrar por dni
				if (paramSearchDni != null && !paramSearchDni.isEmpty() && paramSearchYear == null) {
					matriculas = matriculaDao.selectByDni(paramSearchDni);
				}
				// Filtrar por ambos
				if (paramSearchDni != null && !paramSearchDni.isEmpty()
						&& paramSearchYear != null && !paramSearchYear.isEmpty()) {
					Integer year = null;
					try {
						year = Integer.parseInt(paramSearchYear);
					} catch (Exception e) {
						throw new Exception("El valor de año no es válido");
					}
					matriculas = matriculaDao.selectByYearAndDni(year, paramSearchDni);
				}
				// No filtrar
				if (paramSearchDni == null && paramSearchYear == null) {
					matriculas = matriculaDao.selectAll();
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			}
			session.setAttribute(Globals.ATTRIBUTE_SESSION_LIST_MATRICULAS, matriculas);
			request.getRequestDispatcher(Globals.JSP_MATRICULAS).forward(request, response);
			session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		}
	}

}
