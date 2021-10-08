/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.vista;

import es.iespuertodelacruz.cc.modelo.*;

/**
 *
 * @author Cedric Christoph
 */
public class actividadSerializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorPersonas gp = new GestorPersonas("personas.csv");
  
        gp.addPersona(new Persona(new Dni("X8741949V"), "Juan Daniel 2", 74, 175));
        gp.addPersona(new Persona(new Dni("X8741949V"), "Cedric Christoph", 18, 181));
        gp.guardar();
        System.out.println("Personas creadas");
        
        gp = new GestorPersonas("personas.csv");
        gp.loadFichero();
        for (Persona persona : gp.getPersonas()) {
            System.out.println(persona.getDataRow());
        }

    }
    
}
