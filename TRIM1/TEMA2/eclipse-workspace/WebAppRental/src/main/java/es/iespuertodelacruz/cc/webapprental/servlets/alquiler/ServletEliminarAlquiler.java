package es.iespuertodelacruz.cc.webapprental.servlets.alquiler;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.repositories.RentalRepository;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class ServletEliminarAlquiler
 */
@WebServlet("/ServletEliminarAlquiler")
public class ServletEliminarAlquiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarAlquiler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		EntityManagerFactory factory = (EntityManagerFactory) session.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
		
		try {
			Integer rentalId = null;
			try {
				rentalId = Integer.parseInt(request.getParameter("id"));
			} catch (Exception e) {
				throw new Exception("El formato del ID no es correcto");
			}
			if (rentalId != null) {
				RentalRepository rentals = new RentalRepository(factory);
				rentals.delete(rentalId);
				response.sendRedirect(Globals.SERVLET_CLIENTES);
			}
		} catch (Exception e) {
			session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
			request.getRequestDispatcher(Globals.JSP_ERROR).forward(request, response);
		}

		
	}


}
