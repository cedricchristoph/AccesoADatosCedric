/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Cedric Christoph
 */
public class Gestor {
    
    final Path fichero;
    
    /**
     * Constructor por defecto
     */
    public Gestor() {
        fichero = Paths.get("resultado.txt");
    }
    
    public Gestor(String fichero) {
        this.fichero = Paths.get(fichero);
    }
    
    /**
     * Metodo para guardar los numeros primos calculados en un fichero
     * @param numeros Los resultados
     */
    public void guardar(ArrayList<Integer> numeros) {
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(fichero);
            for (Integer numero : numeros)
                writer.write(numero + "\n");
        } catch (IOException ex) {
            
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
              
            }
        }
    }
    
}
