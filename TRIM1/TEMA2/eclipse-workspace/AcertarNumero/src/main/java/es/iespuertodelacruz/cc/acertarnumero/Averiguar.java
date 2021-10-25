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
 * Servlet implementation class Averiguar
 */
public class Averiguar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Averiguar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_WIN, false);
    	response.sendRedirect(Globals.SERVLET_HISTORIAL);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recibir atributos del contexto de aplicacion
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		GestorUsuario gestorUsuario = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS);
		NumberController controlador = (NumberController) context.getAttribute(Globals.ATTRIBUTE_APP_NUMBER_CONTROLLER);
		
		// Recibir datos del usuario
		Usuario user = (Usuario) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		Integer numberParameter = null;
		long actualMillis = new Date().getTime();
		try {
			numberParameter = Integer.parseInt(request.getParameter(Globals.PARAMETER_NUMERO));
		} catch (Exception e) {
			response.sendRedirect(Globals.SERVLET_HISTORIAL);
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
					System.out.println("Se caducó el numero secreto");
					user.clearNumeros();
					break;
				case 0:
					System.out.println("secreto >");
					user.addNumero(numero);
					session.setAttribute(Globals.ATTRIBUTE_SESSION_NUMBERS, user.getNumeros());
					break;
				case 1:
					System.out.println("secreto <");
					user.addNumero(numero);
					session.setAttribute(Globals.ATTRIBUTE_SESSION_NUMBERS, user.getNumeros());
					break;
				case 2:
					System.out.println("acertado!");
					gestorUsuario.resetAllNumbers();
					controlador.setSecreto(Globals.nuevoNumeroSecreto());
					context.setAttribute(Globals.ATTRIBUTE_APP_NUMBER, controlador.getSecreto());
					request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_NUMBERS, user.getNumeros());
					request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_LAST_NUMBER, numero);
					request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_WIN, true);
					break;
			}
		}
		response.sendRedirect(Globals.SERVLET_HISTORIAL);
	}

}
