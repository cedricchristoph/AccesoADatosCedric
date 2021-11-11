package es.iespuertodelacruz.cc.webappinstituto.servlets.asignaturas;

import java.io.IOException;

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
 * Servlet implementation class EditarAsignatura
 */
public class EditarAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAsignatura() {
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
			try {
				String paramId = request.getParameter(Globals.PARAM_ASIGNATURA_EDITAR_ID);
				String paramNombre = request.getParameter(Globals.PARAM_ASIGNATURA_EDITAR_NOMBRE);
				String paramCurso = request.getParameter(Globals.PARAM_ASIGNATURA_EDITAR_CURSO);
				if (paramId != null) {
					Asignatura newAsignatura = new Asignatura();
					Integer id = null;
					try {
						id = Integer.parseInt(paramId);
					} catch (Exception e) {
						throw new Exception("El valor para ID no es válido");
					}
					AsignaturaDAO asignaturaDao = new AsignaturaDAO(db);
					Asignatura oldAsignatura = asignaturaDao.select(id);
					if (oldAsignatura == null)
						throw new Exception("No se encontró la asignatura con ID " + id);
					
					newAsignatura.setId(oldAsignatura.getId());
					if (paramNombre != null && paramNombre.isEmpty())
						newAsignatura.setNombre(oldAsignatura.getNombre());
					else
						newAsignatura.setNombre(paramNombre);
					if (paramCurso != null && paramCurso.isEmpty())
						newAsignatura.setCurso(oldAsignatura.getCurso());
					else
						newAsignatura.setCurso(paramCurso);
					if (!asignaturaDao.update(newAsignatura))
						throw new Exception("No se pudo actualizar el registro");
					session.setAttribute(Globals.ATTRIBUTE_SESSION_MSG, "Asignatura actualizada correctamente");
					response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
				}
				
			} catch (Exception e) {
				session.setAttribute(Globals.ATTRIBUTE_SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Globals.SERVLET_ASIGNATURAS);
			}
		}
	}

}
