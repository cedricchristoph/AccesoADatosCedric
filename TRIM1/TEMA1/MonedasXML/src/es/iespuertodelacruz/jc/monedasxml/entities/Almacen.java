/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cedric Christoph
 */
@XmlRootElement(name="monedas")
public class Almacen {
    
    @XmlElement(name="moneda")
    public ArrayList<Moneda> monedas;

    public Almacen() {
        monedas = new ArrayList();
    }

    public ArrayList<Moneda> getMonedas() {
        return monedas;
    }

    
    private void setMonedas(ArrayList<Moneda> monedas) {
        this.monedas = monedas;
    }
    
    public void add(Moneda m) {
        monedas.add(m);
    }
    
    public void addAll(Moneda... m) {
        for (Moneda moneda : m) {
            monedas.add(moneda);
        }
    }
    
}
