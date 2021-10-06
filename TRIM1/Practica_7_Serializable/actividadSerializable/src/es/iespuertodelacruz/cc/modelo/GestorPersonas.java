/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.util.ArrayList;

/**
 *
 * @author Cedric Christoph
 */
public class GestorPersonas {
    
    private FileManager manager;
    private ArrayList<Persona> personas;
    
    public GestorPersonas(String fichero) {
        manager = new FileManager(fichero);
        personas = new ArrayList<>();
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    
    public void addPersona(Persona persona) {
        personas.add(persona);
    }
    
    public void guardar() {
        manager.write(personas);
    }
    
    public void addPersona(String datarow) {
        // FORMATO: dni;nombre;edad;altura
        String datos[] = datarow.split(";");
        addPersona(
                new Persona(
                        new Dni(datos[0]),
                        datos[1],
                        Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[3])
                )
        );
        
    }
    
    public void loadFichero() {
        personas = manager.load();
    }
}
