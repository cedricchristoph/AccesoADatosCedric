/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.vista;

import es.iespuertodelacruz.cc.modelo.Dni;

/**
 *
 * @author Cedric Christoph
 */
public class actividadSerializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dni dni = new Dni("X8741949V");
        System.out.println(dni.isValido());
    }
    
}
