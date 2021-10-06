/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.BufferedInputStream;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;



/**
 *
 * @author Cedric Christoph
 */
public class FileManager {
    
    private final Path fichero;
    
    /**
     * Constructor de la clase
     * @param ruta Ruta al fichero que gestiona el FileManager
     */
    public FileManager(String ruta) {
        fichero = Paths.get(ruta);
    }
    
    /**
     * Metodo para escribir una Persona serializado en el fichero indicado
     * @param persona 
     */
    public void write(ArrayList<Persona> personas) {
        try (
                FileOutputStream fos = new FileOutputStream(fichero.toFile());
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);) {

            for (Persona p : personas) {
                oos.writeObject(p);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Funcion que devuelve un ArrayList de Persona que cargamos del fichero indicado
     * @return Devuelve ArrayList de Persona
     */
    public ArrayList<Persona> load() {
        ArrayList<Persona> personas = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(fichero.toFile());
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);) {
            boolean finDeFichero = false;
            Persona p;
            do {
                try {
                    p = (Persona) ois.readObject();
                    personas.add(p);
                    System.out.println(p);
                } catch (EOFException ex) {
                    finDeFichero = true;
                } catch (ClassNotFoundException ex) {
                }
            } while (!finDeFichero);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return personas;
    }
}
