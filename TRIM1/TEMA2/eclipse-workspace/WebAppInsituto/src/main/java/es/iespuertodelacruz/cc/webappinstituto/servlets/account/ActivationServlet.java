package es.iespuertodelacruz.cc.webappinstituto.servlets.account;

import java.io.IOException;

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
 * Servlet implementation class ActivationServlet
 */
public class ActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			UserDAO userDao = new UserDAO(db);
			try {
				if (!(user.getAccessLevel() == User.ACCESSLEVEL_ADMINISTRATOR))
					throw new Exception("No tiene derechos para activar cuentas. Se informará del incidente");
				String userToActivate = request.getParameter(Globals.PARAM_ACCOUNT_USER_TOACTIVATE);
				if (userToActivate != null && !userToActivate.isEmpty()) {
					User toActivate = userDao.select(userToActivate);
					if (toActivate == null)
						throw new Exception("No se encontró la cuenta de usuario");
					toActivate.setActive(true);
					if (!userDao.update(toActivate))
						throw new Exception("No se pudo activar la cuenta");
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Cuenta " + toActivate.getUser() + " activada");
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			} finally {
				response.sendRedirect(Globals.SERVLET_ACCOUNT);
			}
		}
	}

}
