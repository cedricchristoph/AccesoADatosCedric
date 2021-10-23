package es.iespuertodelacruz.cc.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class FileManager {

	// String pathToWeb = getServletContext().getRealPath(File.separator);
	
	Path file;
	
	public FileManager(String ruta) {
		file = (Paths.get(ruta));
	}
	
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
			e.printStackTrace();
		} finally {
			return mensajes;
		}
	}
	
	public void writeAll(Vector<Mensaje> mensajes) {
		try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			for (Mensaje mensaje : mensajes) {
				writer.write(mensaje.toString() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
