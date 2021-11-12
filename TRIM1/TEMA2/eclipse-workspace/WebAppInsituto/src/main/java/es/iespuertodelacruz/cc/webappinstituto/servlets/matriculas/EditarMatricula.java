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
 * Servlet implementation class EditarMatricula
 */
public class EditarMatricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarMatricula() {
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
				String paramId = request.getParameter(Globals.PARAM_MATRICULA_EDITAR_ID);
				String paramDni = request.getParameter(Globals.PARAM_MATRICULA_EDITAR_DNI);
				String paramYear = request.getParameter(Globals.PARAM_MATRICULA_EDITAR_YEAR);
				String paramAsignaturas = request.getParameter(Globals.PARAM_MATRICULA_EDITAR_ASIGNATURAS);
				if (paramId != null && !paramId.isEmpty()) {
					Integer id = null;
					Integer year = null;
					try {
						id = Integer.parseInt(paramId);
						year = Integer.parseInt(paramYear);
					} catch (Exception e) {
					}
					if (id == null)
						throw new Exception("El ID introducido no es válido");
					Matricula matricula = matriculaDao.select(id);
					if (matricula == null)
						throw new Exception("No se encontró la matrícula con ID " + id);
					if (paramDni != null && !paramDni.isEmpty()) {
						Alumno alumno = alumnoDao.select(paramDni);
						if (alumno != null)
							matricula.setAlumno(alumno);
						else
							throw new Exception("No se encontró el alumno con DNI " + paramDni);
					}
					if (year != null)
						matricula.setYear(year);
					if (paramAsignaturas != null && !paramAsignaturas.isEmpty()) {
						String[] asignaturasStr = paramAsignaturas.replaceAll(" ", "").split(",");
						ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
						for (int i = 0; i < asignaturasStr.length; i++) {
							try {
								int idAsignatura = Integer.parseInt(asignaturasStr[i]);
								asignaturas.add(asignaturaDao.select(idAsignatura));
							} catch (Exception e) {
								throw new Exception("Error en la cadena de asignaturas. Algún valor no es numérico");
							}
						}
						matricula.setAsignaturas(asignaturas);
					}
					if (!matriculaDao.update(matricula))
						throw new Exception("No se pudo actualizar la matrícula");
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Matrícula actualizada correctamente");
				}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			} finally {
				response.sendRedirect(Globals.SERVLET_MATRICULAS);
			}
		}
	}

}
