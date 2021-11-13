package es.iespuertodelacruz.cc.webappinstituto.servlets.account;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.UserDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
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
			UserDAO userDao = new UserDAO(db);
			try {
				if (userDao.delete(user.getUser())) {
					session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "Su cuenta ha sido eliminada permanentemente del sistema");
				} else 
					throw new SQLException("No se pudo eliminar la cuenta");
				session.setAttribute(Globals.ATTRIBUTE_SESSION_USER, null);
				response.sendRedirect(Globals.SERVLET_LOGIN);
			} catch (SQLException e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ACCOUNT);
			}
		}
		
	}

}
