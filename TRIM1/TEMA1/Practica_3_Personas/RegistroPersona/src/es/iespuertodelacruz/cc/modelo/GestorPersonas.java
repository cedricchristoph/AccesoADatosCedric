/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.util.ArrayList;

/**
 *
 * @author dama
 */
public class GestorPersonas {

    private ManejarFichero manager;
    private ArrayList<Persona> personas;
    
    public GestorPersonas() {
        manager = new ManejarFichero("personas.txt");
        personas = new ArrayList<>();
    }
    
    public GestorPersonas(String fichero) {
        manager = new ManejarFichero(fichero);
        try {
            personas = manager.getPersonas();
        } catch (Exception e) {
            personas = new ArrayList<>();
        }
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }
    
    public void setFichero(String ruta) {
        manager = new ManejarFichero(ruta);
        try {
            personas = manager.getPersonas();
        } catch (Exception e) {
            personas = new ArrayList<>();
        }
    }
    
    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    
    public boolean addPersona(Persona persona) {
        try {
            personas.add(persona);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean removePersona(String dni) {
        try {
            personas.remove(
                    personas.stream().filter(p -> p.getDni().equals(dni)).findFirst().get()
            );
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
