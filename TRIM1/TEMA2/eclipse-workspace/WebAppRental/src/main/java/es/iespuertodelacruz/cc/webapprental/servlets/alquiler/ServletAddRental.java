package es.iespuertodelacruz.cc.webapprental.servlets.alquiler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.repositories.FilmRepository;
import es.iespuertodelacruz.cc.repositories.RentalRepository;
import es.iespuertodelacruz.cc.webapprental.entity.Customer;
import es.iespuertodelacruz.cc.webapprental.entity.Film;
import es.iespuertodelacruz.cc.webapprental.entity.Inventory;
import es.iespuertodelacruz.cc.webapprental.entity.Rental;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class ServletAddRental
 */
@WebServlet("/newrental")
public class ServletAddRental extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddRental() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();	
		EntityManagerFactory factory = (EntityManagerFactory) context
				.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
		FilmRepository filmRepo = new FilmRepository(factory);
		List<Film> films = filmRepo.selectAll();
		session.setAttribute(Globals.ATT_SESSION_FILMS, films);
		
		/* Recibir posibles parametros */
		String filmIdStr = request.getParameter("selectfilm");
		Film selectedFilm = (Film) session.getAttribute(Globals.ATT_SESSION_SELECTED_FILM);
		try {
			if (selectedFilm == null && filmIdStr != null && !filmIdStr.isEmpty()) {
				try {
					Integer filmId = Integer.parseInt(filmIdStr);
					EntityManager em = factory.createEntityManager();
					Film film = em.find(Film.class, filmId);
					if (film != null)
						session.setAttribute(Globals.ATT_SESSION_SELECTED_FILM, film);
					else
						throw new Exception("No se encontró ninguna pelÃ­cula con el identificador indicado");
				} catch (Exception e) {
					throw new Exception("El formato del identificador de la película es erróneo");
				}
			}
			
			request.getRequestDispatcher(Globals.JSP_CREAR_ALQUILER).forward(request, response);
			
		} catch (Exception e) {
			session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
			request.getRequestDispatcher(Globals.JSP_ERROR).forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Recibimos confirmaciÃ³n de creaciÃ³n de alquiler con el parametro de returndate */
		
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();	
		
		EntityManagerFactory factory = (EntityManagerFactory) context
				.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
		
		Staff staff = (Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
		Customer customer = (Customer) session.getAttribute(Globals.ATT_SESSION_SELECTED_CLIENT);
		
		String returnDateStr = request.getParameter("returndate");
		Date today = new Date();
		Date returnDate = null;
		try {
			if (returnDateStr != null && !returnDateStr.isEmpty()) {
				returnDate = new SimpleDateFormat("dd/MM/yyyy").parse(returnDateStr);
			} else {
				throw new NullPointerException("No se ha especificado una fecha de devolución");
			}
			Rental rental = new Rental();
			Inventory inventory = new Inventory();
			inventory.setFilm((Film) session.getAttribute(Globals.ATT_SESSION_SELECTED_FILM));
			inventory.setStore(staff.getStore());
			rental.setCustomer(customer);
			rental.setRentalDate(today);
			rental.setReturnDate(returnDate);
			rental.setStaff(staff);
			rental.setInventory(inventory);
			
			RentalRepository rentals = new RentalRepository(factory);
			rentals.insert(rental);
			
			response.sendRedirect(Globals.SERVLET_ALQUILER_CREADO);
			
		} catch (Exception e) {
			session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
			request.getRequestDispatcher(Globals.JSP_ERROR).forward(request, response);
		}
		
	}

}
