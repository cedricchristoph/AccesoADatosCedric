/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class ManejoFichero {
    
    
    Path file;
    
    
    public ManejoFichero(String nombre){
        file = Paths.get(nombre);
    }
    
    public boolean append(String texto){
        return false;
    }
    
    public boolean write(String texto){
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)) {
            writer.write(texto);
            return true;
        } catch (IOException ex) {
            return false;
        }   
    }
    
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
