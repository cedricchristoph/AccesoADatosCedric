package es.iespuertodelacruz.jc.matriculas.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> lista;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
        // TODO Auto-generated constructor stub
        lista = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hola = request.getParameter("hola");
		String adios = request.getParameter("adios");
		String text = "";
		String redireccion = "vista.jsp";
		if (hola != null) {
			redireccion = "hola.jsp";
			text = hola;
		}
		else if (adios != null && !adios.isEmpty()) {
			redireccion = "adios.jsp";
			text = adios;
		}
		
		if (lista == null)
			lista = new ArrayList<String>();
		
		lista.add(text);
		request.getServletContext().setAttribute("lista", lista);
		
		request.getRequestDispatcher(redireccion).forward(request, response);
		//ArrayList<String> lista = (ArrayList<String>) request.getServletContext().getAttribute("mensajes");
		//if (lista == null) {
		//	lista = new ArrayList<>();
		//	request.getServletContext().setAttribute("mensaje", lista);
		//}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
