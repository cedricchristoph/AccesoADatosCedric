package es.iespuertodelacruz.cc.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

/**
 * Clase encargada a guardar en ficheros la informacion que se le proporciona
 * @author Cedric Christoph
 *
 */
public class FileManager {

	/**
	 * Variables de la clase FileManager
	 */
	Path file;
	
	/**
	 * Constructor de la clase FileManager
	 * @param ruta
	 */
	public FileManager(String ruta) {
		file = (Paths.get(ruta));
	}
	
	/**
	 * Funcion que devuelve el contenido de chat de una copia de seguridad
	 * @return Vector<Mensaje> que incluye todos los mensajes guardados en la copia de seguridad
	 */
	public Vector<Mensaje> loadAll() {
		Vector<Mensaje> mensajes = new Vector<Mensaje>();
		try (BufferedReader reader = Files.newBufferedReader(file)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				mensajes.add(
						new Mensaje(null, line)
				);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		} finally {
			return mensajes;
		}
	}
	
	/**
	 * Metodo para reescribir un fichero entero e introducir una copia de seguridad
	 * @param mensajes Mensajes a ser asegurados
	 */
	public void writeAll(Vector<Mensaje> mensajes) {
		try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING)) {
			for (Mensaje mensaje : mensajes) {
				writer.write(mensaje.toString() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para añadir mensajes a una copia de seguridad sin reescribir todos los mensajes anteriores
	 * @param mensajes Mensajes añadir a la copia de seguridad
	 */
	public void append(Vector<Mensaje> mensajes) {
		try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			for (Mensaje mensaje : mensajes) {
				writer.write(mensaje.toString() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
