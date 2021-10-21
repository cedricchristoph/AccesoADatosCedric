package es.iespuertodelacruz.cc.foroirc;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Usuario;

/**
 * Servlet implementation class DisconnectUser
 */
public class DisconnectUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisconnectUser() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		GestorUsuario gestor = (GestorUsuario) context.getAttribute(Globals.ATTRIBUTE_USERS);
		Usuario user = gestor.get(request.getSession().getId());
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_PRINCIPAL);
		} else {
			gestor.remove(request.getSession().getId());
			request.getSession().invalidate();
			response.sendRedirect(Globals.SERVLET_PRINCIPAL);
		}
	}
}
