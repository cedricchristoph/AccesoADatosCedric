package es.iespuertodelacruz.cc.webapprental.servlets.alquiler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class ServletAlquilerCreado
 */
@WebServlet({"/home/alquilercreado", "/alquilercreado"})
public class ServletAlquilerCreado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAlquilerCreado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vaciar los atributos en sesion
		request.getSession().setAttribute(Globals.ATT_SESSION_SELECTED_FILM, null);
		request.getRequestDispatcher(Globals.JSP_ALQUILER_CREADO).forward(request, response);
	}


}
