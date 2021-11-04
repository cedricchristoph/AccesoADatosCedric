package es.iespuertodelacruz.cc.practica01_grafico.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.practica01_grafico.utils.Globals;
import es.iespuertodelacruz.cc.practica01_grafico.entities.Lapiz;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
			response.sendRedirect(Globals.JSP_SEARCH);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) request.getSession().getAttribute(Globals.ATTRIBUTE_SESSION_CONN);
		if (conn == null) {
			response.sendRedirect(Globals.JSP_INDEX);
		} else {
			try {
				String marca = request.getParameter(Globals.PARAM_MARCA);
				ArrayList<Lapiz> lapices = new ArrayList<Lapiz>();
				if (!marca.isEmpty()) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select idlapiz,marca,numero from lapices where marca=" + marca);
					while (rs.next()) {
						lapices.add(new Lapiz(rs.getInt(0), rs.getString(1), rs.getInt(2)));
					}
					request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_RESULTADOS, lapices);
				}
			} catch (SQLException e) {
				request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MESSAGE, e.getMessage());
				request.getRequestDispatcher(Globals.JSP_SEARCH);
			}
		}
	}

}
