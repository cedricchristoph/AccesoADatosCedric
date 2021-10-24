package es.iespuertodelacruz.cc.entities;

import java.util.Vector;

import javax.servlet.ServletContextEvent;

import es.iespuertodelacruz.cc.foroirc.Globals;

/**
 * Clase encargada de gestionar las copias de seguridad del chat del foro.
 * Averigua si existen nuevos mensajes y los guarda en tal caso.
 * @author Cedric Christoph
 * 
 */
public class BackupManager {

	/**
	 * Constantes y variables de la clase BackupManager
	 */
	private final FileManager gestor;
	private Vector<Mensaje> newMessages;
	private ServletContextEvent sce;
	
	/**
	 * Constructor para la clase BackupManager
	 * @param sce Objeto ServletContextEvent
	 */
	public BackupManager(ServletContextEvent sce) {
		this.sce = sce;
		this.gestor = (FileManager) sce.getServletContext().getAttribute(Globals.FILE_MANAGER_CHAT);
	}

	/**
	 * Metodo para iniciar el proceso de guardado. Unicamente guarda si hubo cambios.
	 */
	public void save() {
		reload();
		if (!(newMessages.isEmpty())) {
			gestor.append(newMessages);
			System.out.println("Guardado copia de seguridad");
			resetAttributes();
		} else {
			System.out.println("No hay nuevos mensajes a guardar");
		}
	}

	/**
	 * Metodo para reiniciar los atributos del contexto del servlet tras el guardado 
	 * de una copia de seguridad.
	 */
	private void resetAttributes() {
    	Vector<Mensaje> mensajes = gestor.loadAll();
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES, mensajes);
    	sce.getServletContext().setAttribute(Globals.ATTRIBUTE_MENSAJES_NEW, new Vector<Mensaje>());
	}

	/**
	 * Metodo para recargar las variables necesarias a ser implementadas en la copia
	 * de seguridad.
	 */
	private void reload() {
		newMessages = (Vector<Mensaje>) sce.getServletContext().getAttribute(Globals.ATTRIBUTE_MENSAJES_NEW);
	}
	
}
