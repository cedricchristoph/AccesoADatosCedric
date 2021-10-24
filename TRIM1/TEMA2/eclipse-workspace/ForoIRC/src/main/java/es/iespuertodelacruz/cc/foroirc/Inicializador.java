package es.iespuertodelacruz.cc.foroirc;

import java.nio.file.Files;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.cc.entities.BackupManager;
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
    	 new BackupManager(sce).save();
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
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajesOld);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES_NEW, new Vector<Mensaje>());
    	
    	// Cargar gestor de usuarios
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_USERS, new GestorUsuario());
    	
    	// Iniciar timer de copias de seguridad
    	ExecutorService executorService = 
    			  new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,   
    			  new LinkedBlockingQueue<Runnable>());	
    	Runnable backupTimer = () -> {
			try {
				BackupManager backupManager = new BackupManager(sce);
				while (true) {
					TimeUnit.MILLISECONDS.sleep(Globals.TIMEOUT);
					backupManager.save();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		executorService.submit(backupTimer);
    }
	
}
