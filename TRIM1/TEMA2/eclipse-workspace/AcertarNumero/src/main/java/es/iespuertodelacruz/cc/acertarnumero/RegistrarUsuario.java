package es.iespuertodelacruz.cc.acertarnumero;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Globals;
import es.iespuertodelacruz.cc.entities.Usuario;

/**
 * Servlet implementation class Controller
 */
public class RegistrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistrarUsuario iniciado metodo GET");
		ServletContext context = request.getServletContext();
		GestorUsuario gestorUsuario = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS);
		Usuario user = null;
		user = gestorUsuario.get(request.getSession().getId());
		if (user != null) {
			System.out.println("RegistrarUsuario redireccionando a HISTORIAL");
			request.getRequestDispatcher(Globals.SERVLET_HISTORIAL).forward(request, response);
		} else {
			System.out.println("RegistrarUsuario redireccionando a REGISTRARUSUARIO");
			request.getRequestDispatcher(Globals.JSP_REGISTRAR_USUARIO).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		GestorUsuario gestorUsuario = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS);
		Usuario user = null;
		// Recibir atributo usuario en la sesion
		user = (Usuario) request.getSession().getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		
		// si no existe un usuario lo cremos
		if (user == null) {
			String nombre = request.getParameter(Globals.PARAMETER_NOMBRE);
			if (nombre == null || nombre.isEmpty())
				response.sendRedirect(Globals.JSP_REGISTRAR_USUARIO);
			else {
				user = new Usuario(request.getSession().getId(), request.getParameter(Globals.PARAMETER_NOMBRE));
				gestorUsuario.add(user);
				request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_USER, user);
				request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_WIN, false);
				response.sendRedirect(Globals.SERVLET_HISTORIAL);
			}
		} else {
			response.sendRedirect(Globals.SERVLET_HISTORIAL);
		}
	}

}
