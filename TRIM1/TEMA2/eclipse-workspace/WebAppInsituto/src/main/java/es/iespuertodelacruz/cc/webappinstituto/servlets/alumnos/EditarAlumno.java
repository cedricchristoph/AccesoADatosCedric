package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.daos.AlumnoDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.User;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Servlet implementation class EditarAlumno
 */
public class EditarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) context.getAttribute(Globals.ATTRIBUTE_APP_DATABASE);
		User user = (User) session.getAttribute(Globals.ATTRIBUTE_SESSION_USER);
		if (user == null) {
			response.sendRedirect(Globals.SERVLET_LOGIN);
		} else {
			try {
			String paramDni = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_DNI);
			String paramNombre = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_NOMBRE);
			String paramApellidos = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_APELLIDOS);
			String paramFechaNacimiento = request.getParameter(Globals.PARAM_ALUMNO_EDITAR_FECHA);
			java.util.Date fecha = null;
			if (paramDni != null && !paramDni.isEmpty()) {
				AlumnoDAO alumnoDao = new AlumnoDAO(db);
				Alumno entity = alumnoDao.select(paramDni);
				if (entity == null)
					throw new Exception("No se encontró el alumno con dni " + paramDni);
				try {
					fecha = new SimpleDateFormat("dd/MM/yyyy").parse(paramFechaNacimiento);
				} catch (ParseException e) {
					fecha = entity.getFechaNacimiento();
				}
				if (paramNombre != null && paramNombre.isEmpty())
					paramNombre = entity.getNombre();
				if (paramApellidos != null && paramApellidos.isEmpty())
					paramApellidos = entity.getApellidos();
				Alumno alumno = new Alumno(paramDni, paramNombre, paramApellidos, fecha);
				if (alumnoDao.update(alumno))
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Se actualizó el registro de " + alumno.getDni());
				else
					throw new Exception("No se pudo actualizar el registro de " + alumno.getDni());
			} else {
				throw new Exception("Debe introducir un dni a editar");
			}
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
			}
		}
		response.sendRedirect(Globals.SERVLET_ALUMNOS);
	}

}
