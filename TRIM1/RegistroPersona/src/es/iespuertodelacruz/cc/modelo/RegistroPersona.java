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
public class RegistroPersona {
    
    final static int SIZE_NOMBRE = 50;
    final static int SIZE_APELLIDO = 50;
    final static int SIZE_EDAD = 3;
    final static int SIZE_REGISTRO = SIZE_NOMBRE + SIZE_APELLIDO + SIZE_EDAD;
    
    final ManejarFichero manager;
    private ArrayList<Persona> personas;
    
    public RegistroPersona() {
        manager = new ManejarFichero("personas.txt");
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
        manager.guardarLinea(persona.getDataRow());
    }
    
}
