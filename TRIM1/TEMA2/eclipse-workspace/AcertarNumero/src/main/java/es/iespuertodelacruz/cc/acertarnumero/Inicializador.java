package es.iespuertodelacruz.cc.acertarnumero;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Globals;
import es.iespuertodelacruz.cc.entities.NumberController;

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
         // Creamos todos los gestores y variables necesarias a contexto de aplicacion
    	GestorUsuario gestorUsuario = new GestorUsuario();
    	NumberController controlador = new NumberController(Globals.nuevoNumeroSecreto());
    	
    	// Establecemos los atributos
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_GESTOR_USUARIOS, gestorUsuario);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_NUMBER_CONTROLLER, controlador);
    }
	
}
