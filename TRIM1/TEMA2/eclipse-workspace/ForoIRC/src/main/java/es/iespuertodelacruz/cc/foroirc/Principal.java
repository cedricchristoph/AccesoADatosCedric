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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String user = null;
		user = (String) request.getSession().getAttribute(Globals.ATTRIBUTE_USERNAME);
		if (user == null) {
			// Crear usuario para actual sesion
			user = (String) request.getParameter(Globals.FIELD_NOMBRE);
			request.getSession().setAttribute(Globals.ATTRIBUTE_USERNAME, user);
			ArrayList<String> users = (ArrayList<String>) context.getAttribute(Globals.ATTRIBUTE_CONNECTED_USERS);
			users.add(user);
			context.setAttribute(Globals.ATTRIBUTE_CONNECTED_USERS, users);
		}
		String userMessage = (String) request.getParameter(Globals.FIELD_MENSAJE);
		// Si el mensaje esta vacio se reenvia de vuelta a la pagina sin enviar ningun mensaje
		if (userMessage == null || userMessage.isEmpty()) {
			request.getRequestDispatcher(Globals.JSP_VISTA).forward(request, response);
			return;
		}
		String message = "[" + user + "]: " + userMessage;
		// Si el mensaje no es vacio y se establecio el nombre de usuario se procede a enviar el mensaje.
		ArrayList<String> mensajes = (ArrayList<String>) context.getAttribute(Globals.ATTRIBUTE_MENSAJES);
		mensajes.add(message);
		context.setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajes);
		//request.getRequestDispatcher(Globals.JSP_VISTA).forward(request, response);
		response.sendRedirect(Globals.JSP_VISTA);
	}

}
