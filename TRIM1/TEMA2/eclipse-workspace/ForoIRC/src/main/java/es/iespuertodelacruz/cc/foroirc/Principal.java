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
		mensajes = (ArrayList<String>) context.getAttribute(Globals.ATTRIBUTE_MENSAJES);
		if (mensajes == null) {
			mensajes = new ArrayList<String>();
			mensajes.add("hola");
			mensajes.add("que tal");
			context.setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajes);
		}
		request.getRequestDispatcher(Globals.JSP_VISTA).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
