/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author cedric christoph
 */
public class ListaPropietarios {
    
    // Variables de la clase ListaPropietarios
    
    private ArrayList<Propietario> propietarios;
 
    /**
     * Constructor por defecto.
     */
    public ListaPropietarios() {
        propietarios = new ArrayList();
    }
    
    /**
     * Constructor de la clase ListaPropietarios
     * @param propietarios ArrayList de propietarios
     */
    public ListaPropietarios(ArrayList<Propietario> propietarios) {
        if (propietarios != null)
            this.propietarios = propietarios;
        else
            this.propietarios = new ArrayList<>();
    }
    
    /**
     * Metodo para añadir un propietario a la lista
     * @param propietario Propietario a añadir
     */
    public void add(Propietario propietario) {
        propietarios.add(propietario);
    }
    
    /**
     * Funcion para eliminar un propietario de la lista
     * @param propietario Objeto propietario a eliminar
     * @return Devuelve true y solo true si se pudo eliminar el propietario
     */
    public boolean remove(Propietario propietario) {
        try {
            propietarios.remove(propietario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para eliminar un propietario de la lista
     * @param dni Documento de identidad de propietario a eliminar
     * @return Devuelve true y solo true si se pudo eliminar el propietario
     */
    public boolean remove(String dni) {
        try {
            propietarios.remove(
                    propietarios.stream().filter(p -> p.getDni().equals(dni)).findFirst().get()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion que devuelve un objeto Propietario
     * @param dni Dni del propietario a devolver
     * @return Devuelve objeto propietario. Devuelve null si no se pudo encontrar
     */
    public Propietario get(String dni) {
        try {
            return propietarios.stream().filter(p -> p.getDni().equals(dni)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean exists(Propietario find) {
        for (Propietario propietario : propietarios) {
            if (propietario.getDni().equals(find.getDni()))
                return true;
        }
        return false;
    }
    
    // Getters & Setters

    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(ArrayList<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    
}
