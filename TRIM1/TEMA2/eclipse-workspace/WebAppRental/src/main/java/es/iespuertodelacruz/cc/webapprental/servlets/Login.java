package es.iespuertodelacruz.cc.webapprental.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.contracts.StaffEntry;
import es.iespuertodelacruz.cc.repositories.StaffRepository;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if ((Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER) != null)
			session.invalidate();
		request.getRequestDispatcher(Globals.JSP_LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		Staff user = (Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
		
		if (user != null) {
			session.setAttribute(Globals.ATT_SESSION_MSG, "Ya estaba logeado " + user.getUsername());
		} else {
			try {
				String paramUser = request.getParameter(Globals.PARAM_LOGIN_USER);
				String paramPwd = request.getParameter(Globals.PARAM_LOGIN_PWD);
				try {
					EntityManagerFactory factory = (EntityManagerFactory) context
							.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
					StaffRepository db = new StaffRepository(factory);
					user = db.selectByUser(paramUser);
				} catch (Exception e) {

				}
				if (user != null) {
					if (user.checkPwd(paramPwd)) {
						session.setAttribute(Globals.ATT_SESSION_LOGGED_USER, user);
						session.setAttribute(Globals.ATT_SESSION_MSG, "Has accedido correctamente " + user.getUsername());
						response.sendRedirect(Globals.SERVLET_CLIENTES);
					} else {
						throw new Exception("Usuario o contraseña errónea");
					}
				} else {
					throw new Exception("Usuario o contraseña errónea");
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_LOGIN);
			}
		}
	}
}
