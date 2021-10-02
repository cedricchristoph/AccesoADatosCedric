/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.util.ArrayList;

/**
 *
 * @author cedric
 */
public class GestorPersonas {
    
    private GestorFichero gestor;
    private ArrayList<Persona> personas;
    
    public GestorPersonas() {
        gestor = new GestorFichero("personas", "index.txt");
        
    }
    
    public void addPersona(Persona p) {
        personas.add(p);
    }
    
    public void removePersona(String dni) {
        personas.remove(personas.stream().filter(p -> p.getDni().equals(dni)).findFirst().get());
    }
    
    
    
}
