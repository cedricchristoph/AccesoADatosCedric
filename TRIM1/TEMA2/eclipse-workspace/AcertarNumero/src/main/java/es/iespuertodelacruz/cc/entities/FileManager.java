package es.iespuertodelacruz.cc.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
	 * Metodo para reescribir un fichero entero e introducir una copia de seguridad
	 * @param mensajes Mensajes a ser asegurados
	 */
	public void write(Numero secreto) {		
		try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			writer.write(secreto.getMilis() + ";" + secreto.getNumber());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Funcion que devuelve el numero secreto guardado en secreto.txt
	 * @return Numero secreto
	 */
	public Numero read() {
		try (BufferedReader reader = Files.newBufferedReader(file)) {
			String data[] = reader.readLine().split(";");
			return new Numero(Long.parseLong(data[0]), Integer.parseInt(data[1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
