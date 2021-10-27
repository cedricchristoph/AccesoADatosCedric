package es.iespuertodelacruz.cc.acertarnumero;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.cc.entities.FileManager;
import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Globals;
import es.iespuertodelacruz.cc.entities.NumberController;
import es.iespuertodelacruz.cc.entities.Numero;

/**
 * Application Lifecycle Listener implementation class Inicializador
 *
 */
public class Inicializador implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Inicializador() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	String realPath = sce.getServletContext().getRealPath("/");
    	FileManager fileManager = new FileManager(realPath + "WEB-INF/secreto.txt");
    	NumberController controlador;
    	Numero secreto = fileManager.read();
    	if (secreto != null)
    		controlador = new NumberController(secreto);
    	else
    		controlador = new NumberController(Globals.nuevoNumeroSecreto());
    	
         // Creamos todos los gestores y variables necesarias a contexto de aplicacion
    	GestorUsuario gestorUsuario = new GestorUsuario();
    	fileManager.write(controlador.getSecreto());
    	System.out.println(controlador.getSecreto().getNumber());
    	
    	// Establecemos los atributos
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS, gestorUsuario);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_NUMBER_CONTROLLER, controlador);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_NUMBER, controlador.getSecreto());
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_NUMBER_FILE_MANAGER, fileManager);
    }
	
}
