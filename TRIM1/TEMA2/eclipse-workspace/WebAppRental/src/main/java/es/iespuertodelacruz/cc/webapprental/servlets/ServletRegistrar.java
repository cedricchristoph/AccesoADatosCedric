package es.iespuertodelacruz.cc.webapprental.servlets;

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

import es.iespuertodelacruz.cc.repositories.StaffRepository;
import es.iespuertodelacruz.cc.webapprental.entity.Address;
import es.iespuertodelacruz.cc.webapprental.entity.Payment;
import es.iespuertodelacruz.cc.webapprental.entity.Rental;
import es.iespuertodelacruz.cc.webapprental.entity.Staff;
import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Servlet implementation class ServletRegistrar
 */
@WebServlet("/registrar")
public class ServletRegistrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Globals.JSP_REGISTRAR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		
		String paramUser = request.getParameter(Globals.PARAM_LOGIN_USER);
		String paramPwd = request.getParameter(Globals.PARAM_LOGIN_PWD);
		String paramRepeatPwd = request.getParameter(Globals.PARAM_LOGIN_REPEATPWD);
		try {
			StaffRepository rep = new StaffRepository((EntityManagerFactory) context.getAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY));
			if (paramUser != null && !paramUser.isEmpty() && paramPwd != null && !paramPwd.isEmpty() && paramRepeatPwd != null && !paramRepeatPwd.isEmpty()) {
				
				if ((rep.selectByUser(paramUser)) != null)
					throw new Exception("Ya existe una cuenta con ese usuario");
				if (!paramPwd.equals(paramRepeatPwd))
					throw new Exception("Las contraseñas no coinciden");
				
				Staff staff = new Staff();
				staff.setEmail("");
				staff.setAddress(new Address());
				staff.setFirstName("");
				staff.setLastName("");
				staff.setPayments(new ArrayList<Payment>());
				staff.setRentals(new ArrayList<Rental>());
				staff.setUsername(paramUser);
				staff.setPassword(paramPwd);
				rep.insert(staff);
				
				response.sendRedirect(Globals.SERVLET_LOGIN);
				
			} else {
				throw new Exception("Por favor rellena todos los campos");
			}
		} catch (Exception e) {
			session.setAttribute(Globals.ATT_SESSION_ERRMSG, e.getMessage());
			request.getRequestDispatcher(Globals.JSP_ERROR).forward(request, response);
		}
		
	}

}
