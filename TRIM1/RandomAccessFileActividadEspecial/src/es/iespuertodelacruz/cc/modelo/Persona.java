/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author cedric christoph
 */
public class Persona {
    
    public final Integer BLOCK_SIZE = DATA_SIZE * 3;
    public static final Integer DATA_SIZE = 50;
    
    private String dni;
    private String nombre;
    private String apellidos;

    /**
     * Constructor por defecto.
     */
    public Persona() {
    }

    /**
     * Constructor de la clase Persona
     * @param dni Dni de la persona
     * @param nombre Nombre de la persona
     * @param apellidos Apellidos de la persona
     */
    public Persona(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // Getters & Setters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }   
    
}
