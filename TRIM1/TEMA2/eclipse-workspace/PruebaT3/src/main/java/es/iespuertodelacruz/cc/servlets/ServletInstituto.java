package es.iespuertodelacruz.cc.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cc.entities.Alumno;
import es.iespuertodelacruz.cc.pruebat3.utils.Globals;

/**
 * Servlet implementation class ServletInstituto
 */
@WebServlet({ "/ServletInstituto", "/instituto" })
public class ServletInstituto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInstituto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("instituto");
		EntityManager manager = factory.createEntityManager();
		List<Alumno> alumnos = manager.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
		request.getSession().setAttribute(Globals.ATTRIBUTE_SESSION_ALUMNOS_LIST, alumnos);
		request.getRequestDispatcher(Globals.JSP_INDEX).forward(request, response);
		
	}

}
