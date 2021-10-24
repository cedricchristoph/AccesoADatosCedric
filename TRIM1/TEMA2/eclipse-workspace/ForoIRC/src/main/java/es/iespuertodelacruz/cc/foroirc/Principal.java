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
 * Servlet implementation class Principal
 * @author Cedric Christoph
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Vector<Mensaje> mensajes;
	Vector<String> conectados;
	GestorUsuario gestor;
	
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
		gestor = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_USERS);
		mensajes = null;
		conectados = null;
		mensajes = (Vector<Mensaje>) context.getAttribute(Globals.ATTRIBUTE_MENSAJES);
		conectados = gestor.getAllUserNames();
		if (mensajes == null) {
			mensajes = new Vector<Mensaje>();
			context.setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajes);
		}
		if (conectados == null) {
			conectados = new Vector<String>();
		}
		context.setAttribute(Globals.ATTRIBUTE_CONNECTED_USERS, conectados);
		
		request.getRequestDispatcher(Globals.JSP_VISTA).forward(request, response);
	}
	

}
