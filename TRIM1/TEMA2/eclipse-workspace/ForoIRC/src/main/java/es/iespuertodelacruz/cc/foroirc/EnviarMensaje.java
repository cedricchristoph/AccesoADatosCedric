package es.iespuertodelacruz.cc.foroirc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Mensaje;
import es.iespuertodelacruz.cc.entities.Usuario;

/**
 * Servlet implementation class EnviarMensaje
 */
public class EnviarMensaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarMensaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		GestorUsuario gestor = (GestorUsuario) request.getServletContext().getAttribute(Globals.ATTRIBUTE_USERS);
		Usuario user = null;
		String userMessage = (String) request.getParameter(Globals.FIELD_MENSAJE);
		// Si el mensaje esta vacio se reenvia de vuelta a la pagina sin enviar ningun
		// mensaje
		if (userMessage == null || userMessage.isEmpty()) {
			response.sendRedirect(Globals.JSP_VISTA);
			return;
		}
		// Averiguar si la sesion tiene un usuario asignado
		try {
			user = gestor.get(request.getSession().getId());
		} catch (Exception e) {
		}
		if (user == null) {
			// Crear usuario para actual sesion
			String nombre = (String) request.getParameter(Globals.FIELD_NOMBRE);
			if (nombre == null || nombre.isEmpty()) {
				response.sendRedirect(Globals.JSP_VISTA);
				return;
			}
			user = new Usuario(request.getSession().getId(), nombre);
			gestor.add(user);
			context.setAttribute(Globals.ATTRIBUTE_USERS, gestor);
			request.getSession().setAttribute(Globals.ATTRIBUTE_USERNAME, user.getNombre());
		}
		
		Mensaje message = new Mensaje(user, userMessage);
		
		// Tras el filtrado anterior procedemos a enviar el mensaje
		Vector<Mensaje> mensajes = (Vector<Mensaje>) context.getAttribute(Globals.ATTRIBUTE_MENSAJES);
		Vector<Mensaje> mensajesNew = (Vector<Mensaje>) context.getAttribute(Globals.ATTRIBUTE_MENSAJES_NEW);
		mensajes.add(message);
		mensajesNew.add(message);
		context.setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajes);
		context.setAttribute(Globals.ATTRIBUTE_MENSAJES_NEW, mensajesNew);
		
		
		response.sendRedirect(Globals.SERVLET_PRINCIPAL);
	}

}
