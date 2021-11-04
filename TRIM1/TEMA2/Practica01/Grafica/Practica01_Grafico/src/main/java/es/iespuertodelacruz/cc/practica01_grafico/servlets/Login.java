package es.iespuertodelacruz.cc.practica01_grafico.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.practica01_grafico.utils.Globals;
import es.iespuertodelacruz.cc.practica01_grafico.utils.MyDatabase;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) request.getSession().getAttribute(Globals.ATTRIBUTE_SESSION_CONN);
		if (conn == null) {
			response.sendRedirect(Globals.JSP_INDEX);
		} else {
			response.sendRedirect(Globals.SERVLET_SEARCH);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) request.getSession().getAttribute(Globals.ATTRIBUTE_SESSION_CONN);
		request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MESSAGE, "");
		if (conn == null) {
			String username = request.getParameter(Globals.PARAM_USER);
			String pwd = request.getParameter(Globals.PARAM_PWD);
			MyDatabase db = new MyDatabase("oficina", username, pwd);
			try {
				conn = db.getConnection();
				request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_CONN, conn);
				response.sendRedirect(Globals.JSP_SEARCH);
			} catch (SQLException e) {
				request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MESSAGE, "No se pudo conectar al servidor");
				response.sendRedirect(Globals.JSP_INDEX);
			}
		}
	}

}
