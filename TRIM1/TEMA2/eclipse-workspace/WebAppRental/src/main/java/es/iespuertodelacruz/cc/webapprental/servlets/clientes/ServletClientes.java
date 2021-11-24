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
import es.iespuertodelacruz.cc.contracts.StaffEntry;
import es.iespuertodelacruz.cc.repositories.CustomerRepository;
import es.iespuertodelacruz.cc.webapprental.entity.Customer;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;
import es.iespuertodelacruz.cc.webapprental.utils.ParameterUrl;

/**
 * Servlet implementation class ServletClientes
 */
@WebServlet({"/clientes", "/home/clientes"})

public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClientes() {
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
		String paramName = request.getParameter("name");
		String paramLastName = request.getParameter("last");
		try {
			EntityManagerFactory factory = (EntityManagerFactory) context.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY);
			CustomerRepository db = new CustomerRepository(factory);
			List<Customer> results = null;
			if (paramName != null && !paramName.isEmpty() && paramLastName != null && !paramLastName.isEmpty()) {
				results = db.selectByFullname(paramName, paramLastName);
			} else if (paramName != null && !paramName.isEmpty() && paramLastName == null) {
				results = db.selectByName(paramName);
			} else if (paramLastName != null && !paramLastName.isEmpty() && paramName == null) {
				results = db.selectByLastName(paramLastName);
			} else {
				results = db.selectAll();
			}
			session.setAttribute(Globals.ATT_SESSION_CLIENTS_LIST, results);
		} catch (Exception e) {
			session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
		}
		request.getRequestDispatcher(Globals.JSP_CLIENTES).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramName = request.getParameter(Globals.PARAM_CLIENTES_SEARCH_NAME);
		String paramLast = request.getParameter(Globals.PARAM_CLIENTES_SEARCH_LASTNAME);
		ParameterUrl url = new ParameterUrl(Globals.SERVLET_CLIENTES);
		if (paramName != null && !paramName.isEmpty())
			url.addParameter("name", paramName);
		if (paramLast != null && !paramLast.isEmpty())
			url.addParameter("last", paramLast);
		response.sendRedirect(url.toUrl());
	}

}
