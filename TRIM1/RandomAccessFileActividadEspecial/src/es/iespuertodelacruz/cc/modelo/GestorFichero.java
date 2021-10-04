
package es.iespuertodelacruz.cc.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/**
 *
 * @author cedric christoph
 */
public class GestorFichero {
    
    public final Integer BLOCK_SIZE = Persona.DATA_SIZE * 3;
    
    private final Path indexFile;
    private final Path dataFile;

    /**
     * Constructor de clase GestorFichero
     * @param dataFile Ruta al fichero de datos
     * @param indexFile Ruta al fichero de index
     */
    public GestorFichero(String dataFile, String indexFile) {
        this.dataFile = Paths.get(dataFile);
        this.indexFile = Paths.get(indexFile);
    }
    
    /**
     * Añade al fichero de datos una persona como bytes y actualiza el fichero de index
     * @param data Datos de la persona como Array de bytes
     * @param dni String dni de la persona para el indexado
     * @return Devuelve true solo si se ha guardado e indexado correctamente
     */
    public boolean append(byte[] data, String dni) {
        try (FileOutputStream fos = new FileOutputStream(dataFile.toFile(), true)) {
            fos.write(data);
            appendIndex(dni);
            return true;
        } catch (IOException ex) {
           
        }
        return false;
    }
     
    /**
     * Funcion que devuelve un objeto persona encontrado a partir del Dni proporcionado en el fichero de bytes con ayuda de indexado
     * @param id Identificador de la persona (Dni)
     * @return Devuelve objeto persona o null si no se encontro la persona
     */
    public Persona get(String id) {
        Integer index;
        if ((index = getIndexedValue(id)) != null) {
            Integer filePos = index * BLOCK_SIZE;
            try (RandomAccessFile raf = new RandomAccessFile(dataFile.toFile(), "r")) {
                raf.seek(filePos);
                byte readLength = raf.readByte();
                byte[] dataAsBytes = new byte[readLength];
                raf.read(dataAsBytes);
                String data[] = new String(dataAsBytes).split(";");
                return new Persona(data[0], data[1], data[2]);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }
        }
        return null;
    }
    
    /**
     * Añade al fichero de indexado la informacion requerida para el correcto funcionamiento del sistema
     * @param dni Dni de la persona a indexar
     */
    private void appendIndex(String dni) {
        Integer index = getIndexPosition();
        try (BufferedWriter writer = Files.newBufferedWriter(indexFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(dni + ";" + index + "\n");
        } catch (IOException ex) {
        }
    }
    
    /**
     * Devuelve el numero de la posicion a la que se debe seguir a indexar
     * @return Integer posicion
     */
    private Integer getIndexPosition() {
        try {
            return Files.readAllLines(indexFile).size();
        } catch (IOException ex) {}
        return 0;
    } 
    
    /**
     * Funcion que devuelve la posicion encontrada en el archivo de indexado
     * @param id Dni de la persona a buscar en el archivo index
     * @return Devuelve la posicion en el archivo de bytes
     */
    private Integer getIndexedValue(String id) {
        String indexLine;
        try (BufferedReader reader = Files.newBufferedReader(indexFile)) {
            while ((indexLine = reader.readLine()) != null) {
                String[] data = indexLine.split(";");
                if (data[0].equals(id))
                    return Integer.parseInt(data[1]);
            }
        } catch (IOException ex) {
            
        }
        return null;
    }
    
}
