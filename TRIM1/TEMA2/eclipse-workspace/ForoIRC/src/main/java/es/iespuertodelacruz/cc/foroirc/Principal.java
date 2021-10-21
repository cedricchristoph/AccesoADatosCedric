package es.iespuertodelacruz.cc.foroirc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArrayList<String> mensajes;
	ArrayList<String> conectados;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = request.getServletContext();
		mensajes = null;
		conectados = null;
		mensajes = (ArrayList<String>) context.getAttribute(Globals.ATTRIBUTE_MENSAJES);
		conectados = (ArrayList<String>) context.getAttribute(Globals.ATTRIBUTE_CONNECTED_USERS);
		if (mensajes == null) {
			mensajes = new ArrayList<String>();
			context.setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajes);
		}
		if (conectados == null) {
			conectados = new ArrayList<String>();
			context.setAttribute(Globals.ATTRIBUTE_CONNECTED_USERS, conectados);
		}
		request.getRequestDispatcher(Globals.JSP_VISTA).forward(request, response);
	}
	

}
