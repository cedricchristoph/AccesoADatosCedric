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
@XmlRootElement(name="casa")
public class Casa {
    
    // Variables de la clase Casa

    private Integer idCasa;
    private String direccion;
    private TipoCasa tipoCasa;
    private ArrayList<Propietario> propietarios;

    /**
     * Constructor por defecto.
     */
    public Casa() {
        propietarios = new ArrayList();
    }
    
    /**
     * Constructor de la clase Casa que incluye todos sus atributos.
     * @param idCasa Identificador de la casa
     * @param direccion Direccion de la casa
     * @param tipoCasa Tipo de casa
     * @param propietarios ArrayList de propietarios de la casa
     */
    public Casa(Integer idCasa, String direccion, TipoCasa tipoCasa, ArrayList<Propietario> propietarios) {
        this.idCasa = idCasa;
        this.direccion = direccion;
        this.tipoCasa = tipoCasa;
        if (propietarios != null)
            this.propietarios = propietarios;
        else
            this.propietarios = new ArrayList();
    }
    
    /**
     * Constructor de la clase Casa que incluye todos sus atributos.
     * @param idCasa Identificador de la casa
     * @param direccion Direccion de la casa
     * @param tipoCasa Tipo de casa
     * @param propietario Propietario de la casa
     */
    public Casa(Integer idCasa, String direccion, TipoCasa tipoCasa, Propietario propietario) {
        this.idCasa = idCasa;
        this.direccion = direccion;
        this.tipoCasa = tipoCasa;
        this.propietarios = new ArrayList();
        if (propietario != null) {
            this.propietarios.add(propietario);
        }
    }
    
    public void add(Propietario propietario) {
        propietarios.add(propietario);
    }
    
    @Override
    public String toString() {
        return getIdCasa() + " " + getDireccion() + " " + getTipoCasa();
    }
    // Getters & Setters
    
    public Integer getIdCasa() {
        return idCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoCasa getTipoCasa() {
        return tipoCasa;
    }

    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipoCasa(TipoCasa tipoCasa) {
        this.tipoCasa = tipoCasa;
    }

    public void setPropietarios(ArrayList<Propietario> propietarios) {
        this.propietarios = propietarios;
    }
    
    
    
}
