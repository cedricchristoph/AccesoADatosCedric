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
        GestorPersonas gp = new GestorPersonas("personas.txt");
        gp.loadFichero();
        for (Persona persona : gp.getPersonas()) {
            System.out.println(persona.getDataRow());
        }

        
//        gp.addPersona(new Persona(new Dni("43382313N"), "Jesus Sosa", 22, 178));
//        gp.addPersona(new Persona(new Dni("X8741949V"), "Cedric Christoph", 18, 181));
//        System.out.println("Completado");

    }
    
}
