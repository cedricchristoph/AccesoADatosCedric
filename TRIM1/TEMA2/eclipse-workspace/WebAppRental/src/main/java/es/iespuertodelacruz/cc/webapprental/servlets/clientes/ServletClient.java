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
 * Servlet implementation class ServletClient
 */
@WebServlet("/cliente")
public class ServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		Staff user = (Staff) session.getAttribute(Globals.ATT_SESSION_LOGGED_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			String paramId = request.getParameter("id");
			try {
				Integer id = Integer.parseInt(paramId);
				EntityManagerFactory factory = (EntityManagerFactory) context
						.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
				EntityManager manager = factory.createEntityManager();
				Query q = manager.createNamedQuery(CustomerEntry.FINDBYID);
				q.setParameter(1, id);
				Customer customer = (Customer) q.getSingleResult();
				session.setAttribute(Globals.ATT_SESSION_SELECTED_CLIENT, customer);
			} catch (Exception e) {
				session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
			}
			request.getRequestDispatcher(Globals.JSP_CLIENTE).forward(request, response);
		}
	}

}
