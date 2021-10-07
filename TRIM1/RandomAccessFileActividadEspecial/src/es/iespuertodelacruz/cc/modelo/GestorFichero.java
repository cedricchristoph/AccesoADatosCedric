
package es.iespuertodelacruz.cc.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
/**
 *
 * @author cedric christoph
 */
public class GestorFichero {

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
    public boolean append(char[] data, String dni) {
        try (RandomAccessFile raf = new RandomAccessFile(dataFile.toFile(), "rw")) {
            raf.seek(raf.length());
            raf.writeChars(new String(data));
            appendIndex(dni);
            return true;
        } catch (IOException ex) { 
            return false;
        }
//        try (FileOutputStream fos = new FileOutputStream(dataFile.toFile(), true)) {
//            fos.write(data);
//            appendIndex(dni);
//            return true;
//        } catch (IOException ex) {
//           
//        }
//        return false;
    }
    
    /**
     * Metodo que elimina un registro del fichero binario y reescribe el fichero de indexado
     * @param dni Dni de la persona a eliminar
     * @return Devuelve true y solo true si se ha borrado correctamente
     */
    public boolean remove(String dni) {
        Integer indexPos = getIndexedValue(dni);
        List<String> lines = null;
        char[] zeroData = new char[RegistroPersona.CHAR_BLOCK_SIZE];
        zeroData = fillWithSpaces(zeroData);
        try (RandomAccessFile raf = new RandomAccessFile(dataFile.toFile(), "rw")) {
            raf.seek(indexPos);
            raf.writeChars(new String(zeroData));
        } 
        catch (FileNotFoundException ex) {return false;} 
        catch (IOException ex) {return false;}
        try {
            lines = Files.readAllLines(indexFile);
        } catch (IOException ex) {}
        try (BufferedWriter writer = Files.newBufferedWriter(indexFile, StandardOpenOption.TRUNCATE_EXISTING)) {
            String output = "";
            for (String line : lines) {
                System.out.println(output);
                if (line.split(";")[0].equals(dni)) {
                    
                } else
                    output += line + "\n";
            }
            writer.write(output);
        } catch (IOException ex) {return false;}
        return true;       
    }
     
    /**
     * Funcion que devuelve un objeto persona encontrado a partir del Dni proporcionado en el fichero de bytes con ayuda de indexado
     * @param id Identificador de la persona (Dni)
     * @return Devuelve objeto persona o null si no se encontro la persona
     */
    public RegistroPersona get(String id) {
        Integer index;
        if ((index = getIndexedValue(id)) != null) {
            Integer filePos = index * RegistroPersona.BLOCK_SIZE;
            try (RandomAccessFile raf = new RandomAccessFile(dataFile.toFile(), "r")) {
                raf.seek(filePos);
                char[] contenido = new char[RegistroPersona.CHAR_BLOCK_SIZE];
                for (int i = 0; i < contenido.length; i++)
                    contenido[i] = raf.readChar();
                return new RegistroPersona(new String(contenido));
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
        Integer output = 0;
        try (BufferedReader reader = Files.newBufferedReader(indexFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                output = Integer.parseInt(line.split(";")[1]);
            }
            return output + 1;
        } catch (IOException ex) {}
        return output;
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
    
    private char[] fillWithSpaces(char[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = ' ';
        }
        return array;
    }
}
