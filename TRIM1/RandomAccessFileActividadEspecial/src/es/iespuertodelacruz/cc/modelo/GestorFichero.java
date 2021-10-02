/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author cedric
 */
public class GestorFichero {
    
    private final Path indexFile;
    private final Path dataFile;

    public GestorFichero(String dataFile, String indexFile) {
        this.dataFile = Paths.get(dataFile);
        this.indexFile = Paths.get(indexFile);
    }
    
    public void write(String output) {
        byte[] data = output.getBytes();
        
    }
    
    
    public Persona get(String id) {
        Integer filePos = getIndexedValue(id) * Persona.BLOCK_SIZE;
        try (RandomAccessFile raf = new RandomAccessFile(dataFile.toFile(), "r")) {
            raf.seek(filePos);
            byte[] dataAsBytes = new byte[Persona.BLOCK_SIZE];
            raf.read(dataAsBytes);
            String data[] = new String(dataAsBytes).split(";");
            return new Persona(data[0], data[1], data[2]);
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
        return null;
    }
    
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
