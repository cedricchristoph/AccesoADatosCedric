/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.modelo;

import java.io.Serializable;

/**
 *
 * @author Cedric Christoph
 */
public class Persona implements Serializable {
    
    /**
     * Variables
     */
    private String nombre;
    private int edad;
    private int alturaCm;
    private Dni dni;

    /**
     * Constructor de la clase.
     * @param dni
     * @param nombre
     * @param edad
     * @param alturaCm 
     */
    public Persona(Dni dni, String nombre, int edad, int alturaCm) {
        this.nombre = nombre;
        this.edad = edad;
        this.alturaCm = alturaCm;
        this.dni = dni;
    }

    // Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getAlturaCm() {
        return alturaCm;
    }

    public Dni getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setAlturaCm(int alturaCm) {
        this.alturaCm = alturaCm;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }
    
    /**
     * Metodo para recibir los datos de la persona como String
     * @return 
     */
    public String getDataRow() {
        // FORMATO: dni;nombre;edad;altura
        String dataRow = getDni().toString() + ";" + getNombre() + ";" + getEdad() + ";" + getAlturaCm();
        return dataRow;
    }
    
}
