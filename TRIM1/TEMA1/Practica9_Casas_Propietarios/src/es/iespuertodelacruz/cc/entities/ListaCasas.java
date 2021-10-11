/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.cc.entities;

import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 *
 * @author cedric christoph
 */
@XmlRootElement(name="casas")
public class ListaCasas {
    
    // Variables de la clase ListaCasas
    @XmlElement(name="casa")
    private ArrayList<Casa> casas;
 
    /**
     * Constructor por defecto.
     */
    public ListaCasas() {
        casas = new ArrayList();
    }
    
    /**
     * Constructor de la clase ListaCasas que contiene sus atributos
     * @param casas ArrayList de casas
     */
    public ListaCasas(ArrayList<Casa> casas) {
        if (casas != null)
            this.casas = casas;
        else
            this.casas = new ArrayList<>();
    }
    
    /**
     * Metodo para añadir una casa a la lista
     * @param casa Casa a añadir
     */
    public void add(Casa casa) {
        casas.add(casa);
    }
    
    /**
     * Funcion para eliminar una casa de la lista
     * @param casa Objeto casa a eliminar
     * @return Devuelve true y solo true si se pudo eliminar la casa
     */
    public boolean remove(Casa casa) {
        try {
            casas.remove(casa);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion para eliminar una casa de la lista
     * @param id Identificador de casa a eliminar
     * @return Devuelve true y solo true si se pudo eliminar la casa
     */
    public boolean remove(Integer id) {
        try {
            casas.remove(
                    casas.stream().filter(casa -> Objects.equals(casa.getIdCasa(), id)).findFirst().get()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Funcion que devuelve un objeto casa
     * @param id Identificador de la casa a devolver
     * @return Devuelve objeto casa. Devuelve null si no se pudo encontrar la casa.
     */
    public Casa get(Integer id) {
        try {
            return casas.stream().filter(c -> Objects.equals(c.getIdCasa(), id)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }
    // Getters & Setters

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    private void setCasas(ArrayList<Casa> casas) {
        this.casas = casas;
    }
    
}
