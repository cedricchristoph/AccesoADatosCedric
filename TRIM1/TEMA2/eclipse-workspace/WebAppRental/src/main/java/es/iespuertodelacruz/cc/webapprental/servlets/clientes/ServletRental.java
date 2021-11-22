package es.iespuertodelacruz.cc.webapprental.servlets.clientes;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.contracts.CustomerEntry;
import es.iespuertodelacruz.cc.webapprental.entity.Customer;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class ServletRental
 */
@WebServlet({ "/rental", "/home/rental" })
public class ServletRental extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRental() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		Staff user = (Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
		
		String paramId = request.getParameter("id");
		try {
			// TODO
			// - Crear RentalEntry
			// - Crear RentalRepository
		} catch (Exception e) {
			
		}
		
	}

}
