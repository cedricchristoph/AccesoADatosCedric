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
    
    private String nombre;
    private int edad;
    private int alturaCm;
    private Dni dni;

    public Persona(String nombre, int edad, int alturaCm, Dni dni) {
        this.nombre = nombre;
        this.edad = edad;
        this.alturaCm = alturaCm;
        this.dni = dni;
    }

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
    
    
}
