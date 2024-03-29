package es.iespuertodelacruz.cc.webappinstituto.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;

/**
 * Servlet implementation class Inicio
 */
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null)
			response.sendRedirect(Globals.SERVLET_LOGIN);
		else 
			request.getRequestDispatcher(Globals.JSP_INICIO).forward(request, response);
		session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
	}

}
