package es.iespuertodelacruz.cc.webapprental.servlets.clientes;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.repositories.RentalRepository;
import es.iespuertodelacruz.cc.webapprental.entity.Payment;
import es.iespuertodelacruz.cc.webapprental.entity.Rental;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class ServletPay
 */
@WebServlet({"/home/pay", "/pay"})
public class ServletPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Obligatoriamente se deben pasar los parametros:
		 * rental (identificador de rental)
		 */ 
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		String rentalStr = request.getParameter("rental");
		Integer rentalId = null;
		try {
			EntityManagerFactory factory = (EntityManagerFactory) context
					.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
			RentalRepository rentalRepo = new RentalRepository(factory);
			if (rentalStr != null) {
				// En este caso se realiza el pago total del alquiler enviado
				rentalId = Integer.parseInt(rentalStr);
				Rental rental = rentalRepo.select(rentalId);
				if (rental != null) {
					Staff staff = (Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
					double pago = rental.getPagoPendiente();
					if (pago <= 0)
						throw new Exception("No se pueden realizar pagos con cantidades nulas");
					rental.addNewPayment(new Payment(staff, pago));
					if (rentalRepo.update(rental)) {
						session.setAttribute(Globals.ATT_SESSION_CANTIDAD_PAGADA, pago);
						request.getRequestDispatcher(Globals.JSP_PAID).forward(request, response);
					}
					else
						throw new Exception("No se pudo realizar el pago");

				} else {
					throw new Exception("No se pudo encontrar el alquiler con el ID " + rentalId);
				}
			} else {
				// En este caso se realiza el pago de todos los alquileres pendientes de pago
				ArrayList<Rental> pendientes = (ArrayList<Rental>) session.getAttribute(Globals.ATT_SESSION_RENTALS_PENDIENTE);
				if (pendientes != null) {
					Staff staff = (Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
					double pagado = 0;
					boolean error = false;
					for (Rental rental : pendientes) {
						double pendiente = rental.getPagoPendiente();
						if (pendiente <= 0)
							throw new Exception("No se pueden realizar pagos con cantidades nulas");
						rental.addNewPayment(new Payment(staff, pendiente));
						if (rentalRepo.update(rental)) {
							pagado += pendiente;
							continue; 
						}
						error = true;
						break;
					}
					if (!error) {
						session.setAttribute(Globals.ATT_SESSION_CANTIDAD_PAGADA, pagado);
						request.getRequestDispatcher(Globals.JSP_PAID).forward(request, response);
					} else {
						throw new Exception("No se pudo realizar el pago");	
					}
				} else {
					throw new Exception("No se encontraron alquileres pendientes");
				}
			}
		} catch(Exception e) {
			session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
			request.getRequestDispatcher(Globals.JSP_PAYERROR).forward(request, response);		
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Obligatoriamente se deben pasar los parametros:
		 * amount (cantidad a pagar)
		 */ 
	}

}
