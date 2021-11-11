package es.iespuertodelacruz.cc.webappinstituto.servlets.matriculas;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AlumnoDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.daos.AsignaturaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.daos.MatriculaDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Asignatura;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Matricula;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class AgregarMatricula
 */
public class AgregarMatricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarMatricula() {
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
			AlumnoDAO alumnoDao = new AlumnoDAO(db);
			AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
			MatriculaDAO matriculaDao = new MatriculaDAO(db);
			try {
				String paramDni = request.getParameter(Globals.PARAM_MATRICULA_AGREGAR_DNI);
				String paramYear = request.getParameter(Globals.PARAM_MATRICULA_AGREGAR_YEAR);
				String paramAsignaturas = request.getParameter(Globals.PARAM_MATRICULA_AGREGAR_ASIGNATURAS);
				if (paramDni != null && !paramDni.isEmpty() 
						&& paramYear != null && !paramYear.isEmpty()
						&& paramAsignaturas != null && !paramAsignaturas.isEmpty()) {
					Alumno alumno = alumnoDao.select(paramDni);
					if (alumno == null)
						throw new Exception("No se encontró el alumno con dni " + paramDni + " en el sistema");
					Integer year = null;
					try {
						year = Integer.parseInt(paramYear);
					} catch (Exception e) {
						throw new Exception("El valor de año no es válido");
					}
					String[] asignaturasStr = paramAsignaturas.replaceAll(" ", "").split(",");
					Integer[] idAsignaturas = new Integer[asignaturasStr.length];
					for (int i = 0; i < idAsignaturas.length; i++) {
						try {
							idAsignaturas[i] = Integer.parseInt(asignaturasStr[i]);
						} catch (Exception e) {
							throw new Exception("Error en la cadena de asignaturas. Algún valor no es numérico");
						}
					}
					ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
					for(Integer id : idAsignaturas)
						asignaturas.add(asignaturaDao.select(id));
					Matricula matricula = new Matricula(alumno, year, asignaturas);
					Matricula output = matriculaDao.insert(matricula);
					if (output == null) 
						throw new Exception("No se pudo crear la matrícula");
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Matrícula guardada correctamente");
					response.sendRedirect(Globals.SERVLET_MATRICULAS);
				} else
					throw new Exception("Por favor rellene los campos obligatorios");
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_MATRICULAS);
			}
		}
	}

}
