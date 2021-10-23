package es.iespuertodelacruz.cc.foroirc;

import java.nio.file.Files;
import java.util.Vector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.cc.entities.FileManager;
import es.iespuertodelacruz.cc.entities.GestorUsuario;
import es.iespuertodelacruz.cc.entities.Mensaje;

/**
 * Application Lifecycle Listener implementation class Inicializador
 *
 */
public class Inicializador implements ServletContextListener {

	FileManager gestor;
	String pathToWeb;
	
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
    	 Vector<Mensaje> mensajes = (Vector<Mensaje>) sce.getServletContext().getAttribute(Globals.ATTRIBUTE_MENSAJES_NEW);
    	 gestor.append(mensajes);
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        // Cargar mensajes anteriores
    	pathToWeb = "/tmp/chat.txt";
    	gestor = new FileManager(pathToWeb);
    	sce.getServletContext().setAttribute(Globals.FILE_MANAGER_CHAT, gestor);
    	Vector<Mensaje> mensajesOld = gestor.loadAll();
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES_OLD, mensajesOld);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajesOld);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES_NEW, new Vector<Mensaje>());
    	
    	// Cargar gestor de usuarios
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_USERS, new GestorUsuario());
    }
	
}
