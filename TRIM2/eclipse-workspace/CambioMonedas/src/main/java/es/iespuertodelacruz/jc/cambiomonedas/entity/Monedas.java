/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.cambiomonedas.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "monedas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monedas.findAll", query = "SELECT m FROM Monedas m")
    , @NamedQuery(name = "Monedas.findByIdmoneda", query = "SELECT m FROM Monedas m WHERE m.idmoneda = :idmoneda")
    , @NamedQuery(name = "Monedas.findByNombre", query = "SELECT m FROM Monedas m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Monedas.findByPais", query = "SELECT m FROM Monedas m WHERE m.pais = :pais")})
public class Monedas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmoneda")
    private Integer idmoneda;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "pais")
    private String pais;
    @OneToMany(
    		 cascade = CascadeType.PERSIST,  
    		mappedBy = "fkidmoneda"
    )
    private Collection<Historicocambioeuro> historicocambioeuroCollection;

    public Monedas() {
    }

    public Monedas(Integer idmoneda) {
        this.idmoneda = idmoneda;
    }

    public Monedas(Integer idmoneda, String nombre) {
        this.idmoneda = idmoneda;
        this.nombre = nombre;
    }

    public Integer getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(Integer idmoneda) {
        this.idmoneda = idmoneda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    //@XmlTransient
   // @JsonIgnore     // -------------   !!!!!!          OBSERVAR JSONIGNORE PARA LA SALIDA DE REST   !!!!!!     -------------
    @JsonProperty("historicos")
    public Collection<Historicocambioeuro> getHistoricocambioeuroCollection() {
        return historicocambioeuroCollection;
    }

    public void setHistoricocambioeuroCollection(Collection<Historicocambioeuro> historicocambioeuroCollection) {
        this.historicocambioeuroCollection = historicocambioeuroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmoneda != null ? idmoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monedas)) {
            return false;
        }
        Monedas other = (Monedas) object;
        if ((this.idmoneda == null && other.idmoneda != null) || (this.idmoneda != null && !this.idmoneda.equals(other.idmoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	String histo= "";
    	if(historicocambioeuroCollection != null)
	    	for( Historicocambioeuro h : historicocambioeuroCollection) {
	    		histo += h.toString() +"\n";
	    	}
        return "entitiesmonedas.Monedas[ idmoneda=" + idmoneda + " ]" + histo;
    }
    
}
