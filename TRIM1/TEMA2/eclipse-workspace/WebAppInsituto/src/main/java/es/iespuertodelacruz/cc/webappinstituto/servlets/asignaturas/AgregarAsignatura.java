package es.iespuertodelacruz.cc.webappinstituto.servlets.asignaturas;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AsignaturaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Asignatura;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class AgregarAsignatura
 */
public class AgregarAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			String paramNombre = request.getParameter(Globals.PARAM_ASIGNATURA_AGREGAR_NOMBRE);
			String paramCurso = request.getParameter(Globals.PARAM_ASIGNATURA_AGREGAR_CURSO);
			try {
				if (paramNombre != null && !paramNombre.isEmpty() && paramCurso != null && !paramCurso.isEmpty()) {
					AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
					try {
						Asignatura output = asignaturaDao.insert(new Asignatura(paramNombre, paramCurso));
						if (output == null)
							throw new Exception("No se pudo crear la asignatura. Ocurrió un error interno");
						session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Asignatura guardada correctamente");
						response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
					} catch (SQLException e) {
						throw new Exception(e.getMessage());
					}
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
			}
		}
	}

}
