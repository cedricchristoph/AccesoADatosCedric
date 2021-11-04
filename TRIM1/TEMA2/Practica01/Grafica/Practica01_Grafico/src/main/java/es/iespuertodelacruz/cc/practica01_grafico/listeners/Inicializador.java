package es.iespuertodelacruz.cc.practica01_grafico.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.cc.practica01_grafico.utils.MyDatabase;

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
         MyDatabase.cargarDriverMysql();
    }
	
}
