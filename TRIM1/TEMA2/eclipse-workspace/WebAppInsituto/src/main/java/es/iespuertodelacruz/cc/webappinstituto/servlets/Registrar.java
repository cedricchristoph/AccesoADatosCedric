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
    	HttpSession session = request.getSession();		
		session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
		
    	request.getRequestDispatcher(Globals.JSP_REGISTRAR).forward(request, response);;
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		
		String paramUser = request.getParameter(Globals.PARAM_REGISTRAR_USER);
		String paramEmail = request.getParameter(Globals.PARAM_REGISTRAR_EMAIL);
		String paramPwd = request.getParameter(Globals.PARAM_REGISTRAR_PWD);

		try {
			if (paramUser != null && !paramUser.isEmpty() && paramPwd != null && !paramPwd.isEmpty()) {
				UserDAO userDao = new UserDAO(db);
				User existing = userDao.select(paramUser);
				if (existing != null) {
					throw new Exception("El usuario ya existe");
				} else {
					User toRegister = new User(paramUser, paramEmail, paramPwd, false, true, User.ACCESSLEVEL_STANDARD);
					System.out.println(toRegister.getHashPwd());
					User output;
					if ((output = userDao.insert(toRegister)) != null) {
						session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Se ha registrado correctamente. Espere a que un administrador active su cuenta");
						response.sendRedirect(Globals.SERVLET_LOGIN);
					} else {
						throw new Exception("Ocurrió un error interno del servidor. No se creó su cuenta");
					}
				}
			} else {
				throw new Exception("Por favor rellene los campos obligatorios");
			}
		} catch (Exception e) {
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			response.sendRedirect(Globals.JSP_LOGIN);
		}

	}

}
