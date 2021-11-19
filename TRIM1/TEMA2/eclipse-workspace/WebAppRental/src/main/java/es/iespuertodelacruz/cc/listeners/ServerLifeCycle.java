package es.iespuertodelacruz.cc.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import es.iespuertodelacruz.cc.webapprental.utils.Globals;

/**
 * Application Lifecycle Listener implementation class ServerLifeCycle
 *
 */
@WebListener
public class ServerLifeCycle implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServerLifeCycle() {
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
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebAppRental");
    	sce.getServletContext().setAttribute(Globals.ATT_APP_ENTITY_MANAGER_FACTORY, factory);
    }
	
}
