package es.iespuertodelacruz.cc.webappinstituto.servlets.alumnos;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;
import es.iespuertodelacruz.cc.webappinstituto.model.daos.AlumnoDAO;
import es.iespuertodelacruz.cc.webappinstituto.model.entities.Alumno;

/**
 * Servlet implementation class AgregarAlumno
 */
public class AgregarAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		MyDatabase db = (MyDatabase) session.getAttribute(Globals.ATTRIBUTE_SESSION_DB_INSTANCE);
		if (db == null) {
			System.out.println("No estas logeado");
			response.sendRedirect(Globals.JSP_LOGIN);
		} else {
			String dni = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_DNI);
			String nombre = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_NOMBRE);
			String apellidos = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_APELLIDOS);
			String strFechaNacimiento = request.getParameter(Globals.PARAM_ALUMNO_AGREGAR_FECHA);
			Date fechaNacimientoSql = null;
			try {
				java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strFechaNacimiento);
				fechaNacimientoSql = new Date(date.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			if (!dni.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()
					&& !(fechaNacimientoSql == null)) 
			{
				Alumno alumno = new Alumno(
						dni, nombre, apellidos, fechaNacimientoSql
				);
				AlumnoDAO alumnoDao = new AlumnoDAO(db);
				try {
					System.out.println("Insertando");
					alumno = alumnoDao.insert(alumno);
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Alumno guardado correctamente");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				}
			}
			response.sendRedirect(Globals.JSP_ALUMNOS);
		}	
	}

}
