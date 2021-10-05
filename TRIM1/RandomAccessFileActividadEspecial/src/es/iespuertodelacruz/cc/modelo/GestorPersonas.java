/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.util.ArrayList;

/**
 *
 * @author cedric christoph
 */
public class GestorPersonas {
    
    private final String FICHERO_PERSONAS = "personas";
    private final String FICHERO_INDEX = "index.txt";
    
    private GestorFichero gestor;
    private ArrayList<Persona> personas;
    
    /**
     * Constructor de la clase GestorPersonas
     * @param rutaFichero Ruta del fichero de datos
     * @param rutaIndex Ruta del fichero de index
     */
    public GestorPersonas(String rutaFichero, String rutaIndex) {
        gestor = new GestorFichero(rutaFichero, rutaIndex);
        personas = new ArrayList<>();
    }
    
    /**
     * Constructor por defecto.
     */
    public GestorPersonas() {
        gestor = new GestorFichero(FICHERO_PERSONAS, FICHERO_INDEX);
        personas = new ArrayList<>();
    }
    
    /**
     * Metodo para añadir una persona a la lista y al fichero
     * @param p Persona a añadir
     */
    public void addPersona(Persona p) {
        personas.add(p);
        gestor.append(p.toByteArray(), p.getDni());
    }
    
    /**
     * Metodo para eliminar una persona de la lista
     * @param dni 
     */
    public void removePersona(String dni) {
        personas.remove(personas.stream().filter(p -> p.getDni().equals(dni)).findFirst().get());
    }
    
    public boolean removePersona(Persona persona) {
        personas.remove(persona);
        return gestor.remove(persona.getDni());
    }
    
    /**
     * Metodo para encontrar una persona a traves de su dni del fichero de bytes
     * @param dni Dni de la persona a encontrar
     * @return Devuelve el objeto persona o null si no se ha encontrado la persona
     */
    public Persona get(String dni) {
        return gestor.get(dni);
    }
}
