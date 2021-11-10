package es.iespuertodelacruz.cc.webappinstituto.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.UserDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();		
		User sessionUser = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (sessionUser != null)
			request.getRequestDispatcher(Globals.JSP_INICIO).forward(request, response);
		else
			request.getRequestDispatcher(Globals.JSP_LOGIN).forward(request, response);
		session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
		session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		String user = request.getParameter(Globals.PARAM_LOGIN_USER);
		String pwd = request.getParameter(Globals.PARAM_LOGIN_PWD);
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		try {
			User account = new UserDAO(db).select(user);
			if (account != null) {
				System.out.println(account.getHashPwd());
				if (account.checkPwd(pwd)) {
					if (!account.isActive())
						throw new Exception("Su cuenta aún no ha sido activada");
					session.setAttribute(Globals.ATTRIBUTE_SESSION_USER, account);
					//session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "¡Bienvenido " + account.getUser() + "!");
					response.sendRedirect(Globals.SERVLET_INICIO);
				} else {
					throw new Exception("Usuario y/o contraseña errónea");
				}	
			} else {
				throw new Exception("Usuario y/o contraseña errónea");
			}
		} catch (Exception e) {
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			response.sendRedirect(Globals.SERVLET_LOGIN);
		}
	}

}
