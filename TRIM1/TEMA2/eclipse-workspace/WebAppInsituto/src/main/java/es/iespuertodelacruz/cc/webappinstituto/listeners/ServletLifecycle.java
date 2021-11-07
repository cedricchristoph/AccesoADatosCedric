package es.iespuertodelacruz.cc.webappinstituto.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.cc.webappinstituto.model.utils.Globals;
import es.iespuertodelacruz.cc.webappinstituto.model.utils.MyDatabase;

/**
 * Application Lifecycle Listener implementation class ServletLifecycle
 *
 */
public class ServletLifecycle implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServletLifecycle() {
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
         MyDatabase db = new MyDatabase("instituto");
         sce.getServletContext().setAttribute(Globals.ATTRIBUTE_APP_DATABASE, db);
    }
	
}
