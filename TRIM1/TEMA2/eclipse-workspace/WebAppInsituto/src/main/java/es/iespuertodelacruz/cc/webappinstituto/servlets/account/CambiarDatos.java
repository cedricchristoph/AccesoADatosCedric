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
 * Servlet implementation class CambiarDatos
 */
public class CambiarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			request.getRequestDispatcher(Globals.JSP_ACCOUNT_EDIT).forward(request, response);
			session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_INFO_MSG, "");
			session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, "");
		}
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			UserDAO userDao = new UserDAO(db);
			String paramEmail = request.getParameter(Globals.PARAM_ACCOUNT_EDITAR_EMAIL);
			String paramOldPwd = request.getParameter(Globals.PARAM_ACCOUNT_EDITAR_PWD_OLD);
			String paramNewPwd = request.getParameter(Globals.PARAM_ACCOUNT_EDITAR_PWD_NEW);
			String paramRepeatPwd = request.getParameter(Globals.PARAM_ACCOUNT_EDITAR_PWD_REPEAT);
			try {
			if (paramOldPwd!= null && !paramOldPwd.isEmpty()) {
				if (!user.checkPwd(paramOldPwd)) 
					throw new Exception("Contraseña incorrecta");
				if (paramNewPwd != null && !paramNewPwd.isEmpty() &&
						paramRepeatPwd != null && !paramNewPwd.isEmpty() &&
						paramNewPwd.equals(paramRepeatPwd)) {
					User newUser = new User(user.getUser(), paramEmail, paramNewPwd, true, true, user.getAccessLevel());
					if(!userDao.update(newUser))
						throw new Exception("No se pudo actualizar la cuenta");
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Datos actualizados correctamente");
					response.sendRedirect(Globals.SERVLET_ACCOUNT);
				} else
					throw new Exception("Las contraseñas nuevas no coinciden");
			}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ACCOUNT_EDIT);
			} 
		}
	}

}
