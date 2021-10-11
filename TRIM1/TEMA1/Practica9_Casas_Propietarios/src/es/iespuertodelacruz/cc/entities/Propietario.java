/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author cedric christoph
 */
@XmlRootElement(name="propietario")
public class Propietario {
    
    // Variables de la clase Propietario
    private String dni;
    private String nombre;
    private String apellidos;
    private ArrayList<Casa> propiedades;

    /**
     * Constructor por defecto.
     */
    public Propietario() {
        propiedades = new ArrayList();
    }
    
    /**
     * Constructor para clase Propietario que incluye todos los atributos
     * @param dni Dni de la persona
     * @param nombre Nombre de la persona
     * @param apellidos Apellidos de la persona
     * @param propiedades ArrayList de casas en propiedad de la persona
     */
    public Propietario(String dni, String nombre, String apellidos, ArrayList<Casa> propiedades) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        if (propiedades != null)
            this.propiedades = propiedades;
        else
            this.propiedades = new ArrayList();
    }

    /**
     * Metodo para añadir una propiedad a un propietario
     * @param casa Objeto casa a añadir
     */
    public void addPropiedad(Casa casa) {
        propiedades.add(casa);
    }
    
    /**
     * Funcion que elimina una propiedad de una persona
     * @param id Identificador de la casa
     * @return Devuelve true y solo true si la casa ha sido eliminada
     */
    public boolean removePropiedad(Integer id) {
       try {
           propiedades.remove(
                   propiedades.stream().filter(casa -> casa.getIdCasa() == id).findFirst().get()
           );
           return true;
       } catch (Exception e) {
           return false;
       }
    }
    
    /**
     * Funcion que elimina una propiedad de una persona
     * @param casa Objeto casa a eliminar
     * @return Devuelve true y solo true si la casa ha sido eliminada
     */
    public boolean removePropiedad(Casa casa) {
        try {
            propiedades.remove(casa);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    @Override
    public String toString() {
        return getDni() + " " + getNombre() + " " + getApellidos();
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

    public ArrayList<Casa> getPropiedades() {
        return propiedades;
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

    @XmlTransient()
    public void setPropiedades(ArrayList<Casa> propiedades) {
        this.propiedades = propiedades;
    }
    
}
