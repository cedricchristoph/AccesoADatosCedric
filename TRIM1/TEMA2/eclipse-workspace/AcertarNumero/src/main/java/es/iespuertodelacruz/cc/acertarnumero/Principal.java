package es.iespuertodelacruz.cc.acertarnumero;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Globals;
import es.iespuertodelacruz.cc.entities.NumberController;
import es.iespuertodelacruz.cc.entities.Numero;
import es.iespuertodelacruz.cc.entities.Usuario;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		/* 
		 * COMPROBAMOS EXISTENCIA DE UN USUARIO EN LA SESION PARA POSTERIOR
		 * REENVIO A QUE SE REGISTRE O AL JUEGO.
		 */
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		GestorUsuario gestorUsuario = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS);
		Usuario user = null;
		user = gestorUsuario.get(request.getSession().getId());
		if (user != null) {
			request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_WIN, false);
			request.getRequestDispatcher(Globals.JSP_ACERTAR_NUMERO).forward(request, response);
		} else {
			request.getRequestDispatcher(Globals.JSP_REGISTRAR_USUARIO).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * COMPROBAMOS LAS ENTRADAS QUE NOS HA PROPORCIONADO EL USUARIO
		 * Y LOS ATRIBUTOS QUE YA TENEMOS PARA CORRECTO REENVIO
		 */
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		GestorUsuario gestorUsuario = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS);
		NumberController controlador = (NumberController) context.getAttribute(Globals.ATTRIBUTE_APP_NUMBER_CONTROLLER);
		
		Usuario user = null;
		// Recibir atributo usuario en la sesion
		user = (Usuario) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		
		// si no existe un usuario lo creamos
		if (user == null) {
			String nombre = request.getParameter(Globals.PARAMETER_NOMBRE);
			if (nombre == null || nombre.isEmpty())
				response.sendRedirect(Globals.JSP_REGISTRAR_USUARIO);
			else {
				user = new Usuario(session.getId(), nombre);
				gestorUsuario.add(user);
				session.setAttribute(Globals.ATTRIBUTE_SESSION_USER, user);
				session.setAttribute(Globals.ATTRIBUTE_SESSION_WIN, false);
				response.sendRedirect(Globals.JSP_ACERTAR_NUMERO);
			}
		} else {
			/*
			 * En este caso, el usuario ya existe y comprobamos que está enviando un numero
			 * para acertar el numero secreto
			 */
			Integer numberParameter = null;
			long actualMillis = new Date().getTime();
			try {
				numberParameter = Integer.parseInt(request.getParameter(Globals.PARAMETER_NUMERO));
			} catch (Exception e) {
				/* Si no nos proporciono el parametro numero lo reenviamos a la pagina de juego */
				response.sendRedirect(Globals.JSP_ACERTAR_NUMERO);
				return;
			}
			Numero numero = new Numero(actualMillis, numberParameter);
			

			// Validar entrada
			boolean win = (boolean) session.getAttribute(Globals.ATTRIBUTE_SESSION_WIN);
			if (win)
				session.setAttribute(Globals.ATTRIBUTE_SESSION_WIN, false);
			
			if (numberParameter < 0)
				response.sendRedirect(Globals.SERVLET_HISTORIAL);
			else {
				switch (controlador.check(numero)) {
					case -1:
						user.clearNumeros();
						break;
					case 0:
						user.addNumero(numero);
						session.setAttribute(Globals.ATTRIBUTE_SESSION_NUMBERS, user.getNumeros());
						break;
					case 1:
						user.addNumero(numero);
						session.setAttribute(Globals.ATTRIBUTE_SESSION_NUMBERS, user.getNumeros());
						break;
					case 2:
						gestorUsuario.resetAllNumbers();
						controlador.setSecreto(Globals.nuevoNumeroSecreto());
						context.setAttribute(Globals.ATTRIBUTE_APP_NUMBER, controlador.getSecreto());
						context.setAttribute(Globals.ATTRIBUTE_APP_LAST_WINNER, user.getNombre());
						session.setAttribute(Globals.ATTRIBUTE_SESSION_NUMBERS, user.getNumeros());
						session.setAttribute(Globals.ATTRIBUTE_SESSION_LAST_NUMBER, numero);
						session.setAttribute(Globals.ATTRIBUTE_SESSION_WIN, true);
						break;
				}
			}
			response.sendRedirect(Globals.JSP_ACERTAR_NUMERO);
		}
	}

}
