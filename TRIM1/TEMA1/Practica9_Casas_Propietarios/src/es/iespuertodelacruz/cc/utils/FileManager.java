/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author cedric christoph
 */
public class FileManager {
    
    Path file;
    
    /**
     * Constructor de la clse FileManager
     * @param nombre 
     */
    public FileManager(String nombre){
        file = Paths.get(nombre);
    }

    /**
     * Funcion que reescribe el contenido en un fichero
     * @param texto Texto a escribir al fichero
     * @return Devuelve true si se pudo escribir al fichero
     */
    public boolean write(String texto){
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)) {
            writer.write(texto);
            return true;
        } catch (IOException ex) {
            return false;
        }   
    }
    
    /**
     * Devuelve el contenido entero de un fichero
     * @return Devuelve String de contenido de un fichero
     */
    public String readAll(){
        String output = "";
        String line;
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            while ((line = reader.readLine()) != null) {
                output += line;
            }
            return output;
        } catch (IOException ex) {
           return null;
        }
    }
}
