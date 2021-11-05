package es.iespuertodelacruz.cc.webappinstituto.servlets;

import java.io.IOException;
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
 * Servlet implementation class Registrar
 */
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect(Globals.JSP_REGISTRAR);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) session.getAttribute(Globals.ATTRIBUTE_SESSION_DB_INSTANCE);
		if (db == null) {			
			String username = request.getParameter(Globals.PARAM_REGISTRAR_USER);
			String email = request.getParameter(Globals.PARAM_REGISTRAR_EMAIL);
			String strPwd = request.getParameter(Globals.PARAM_REGISTRAR_PWD);
			if (username != null && !username.isEmpty() && strPwd != null && !strPwd.isEmpty()) {
				String pwdHash = BCrypt.hashpw(strPwd, BCrypt.gensalt());
				UserDAO userDao = new UserDAO("instituto");
				try {
					userDao.insert(new User(username, email, pwdHash));
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Se ha registrado correctamente. Su cuenta aun tiene que ser activada por un administrador.");
					response.sendRedirect(Globals.JSP_LOGIN);
				} catch (SQLException e) {
					session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
					response.sendRedirect(Globals.SERVLET_REGISTRAR);
				}
			} else {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "Usuario y/o contraseña están vacíos");
				response.sendRedirect(Globals.SERVLET_REGISTRAR);
			}
		}
	}

}
