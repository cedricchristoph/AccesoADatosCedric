package es.iespuertodelacruz.cc.webappinstituto.servlets.account;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
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
			if (user.getAccessLevel() == User.ACCESSLEVEL_ADMINISTRATOR) {
				UserDAO userDao = new UserDAO(db);
				List<User> inactivos = userDao.selectAllInactive();
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ACCOUNTS_INACTIVE, inactivos);
			}
			request.getRequestDispatcher(Globals.JSP_ACCOUNT).forward(request, response);
			session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
