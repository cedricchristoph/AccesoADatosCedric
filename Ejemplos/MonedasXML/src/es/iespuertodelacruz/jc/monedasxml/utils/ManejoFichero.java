/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class ManejoFichero {
    
    
    File file;
    
    
    public ManejoFichero(String nombre){
        file = new File(nombre);
    }
    
    public boolean agregarTexto(String texto){
        return false;
    }
    
    public boolean borrarYAgregar(String texto){
        return false;    
    }
    
    public String leerTodo(){
        return null;
    }
    
}
