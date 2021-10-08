/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 *
 * @author Cedric Christoph
 */
public class ManejarFichero {
    private final Path fichero;
    
    public ManejarFichero(String ruta) {
        fichero = Paths.get(ruta);
    }
    
    public void guardarLinea(String linea) {
        try (PrintWriter pw = new PrintWriter(
            new BufferedWriter(
                new FileWriter(
                    fichero.toFile(), true
                )
            )
        )) {
            pw.println(linea);
        } catch (IOException ex) {
            ex.printStackTrace();
        }    
    }
    
    public boolean guardarPersonas(ArrayList<Persona> personas) {
        try (BufferedWriter writer = Files.newBufferedWriter(fichero, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Persona persona : personas) {
                writer.write(persona.getDataRow());
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public ArrayList<Persona> getPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(fichero)) {
            String strPersona;
            while ((strPersona = reader.readLine()) != null) {
                String datos[] = strPersona.split(";");
                lista.add(new Persona(datos[2], datos[0], datos[1], Integer.parseInt(datos[3])));
            }
        } catch (IOException ex) {
        }
        return lista;
    }
    
    public Persona getPersona(String dni) {
        try (BufferedReader reader = Files.newBufferedReader(fichero)) {
            String strPersona;
            while ((strPersona = reader.readLine()) != null) {
                String datos[] = strPersona.split(";");
                if (datos[2].equals(dni))
                    return new Persona(datos[2], datos[0], datos[1], Integer.parseInt(datos[3]));
            }
        } catch (IOException ex) {
            
        }
        return null;
    }
}
