/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.cambiomonedas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "historicocambioeuro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicocambioeuro.findAll", query = "SELECT h FROM Historicocambioeuro h")
    , @NamedQuery(name = "Historicocambioeuro.findByIdhistoricocambioeuro", query = "SELECT h FROM Historicocambioeuro h WHERE h.idhistoricocambioeuro = :idhistoricocambioeuro")
    , @NamedQuery(name = "Historicocambioeuro.findByFecha", query = "SELECT h FROM Historicocambioeuro h WHERE h.fecha = :fecha")
    , @NamedQuery(name = "Historicocambioeuro.findByEquivalenteeuro", query = "SELECT h FROM Historicocambioeuro h WHERE h.equivalenteeuro = :equivalenteeuro")})
public class Historicocambioeuro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistoricocambioeuro")
    private Integer idhistoricocambioeuro;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "equivalenteeuro")
    private BigDecimal equivalenteeuro;
    @JoinColumn(name = "fkidmoneda", referencedColumnName = "idmoneda")
    @ManyToOne(optional = false)
    @JsonBackReference
    private Monedas fkidmoneda;

    public Historicocambioeuro() {
    }

    public Historicocambioeuro(Integer idhistoricocambioeuro) {
        this.idhistoricocambioeuro = idhistoricocambioeuro;
    }

    public Integer getIdhistoricocambioeuro() {
        return idhistoricocambioeuro;
    }

    public void setIdhistoricocambioeuro(Integer idhistoricocambioeuro) {
        this.idhistoricocambioeuro = idhistoricocambioeuro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getEquivalenteeuro() {
        return equivalenteeuro;
    }

    public void setEquivalenteeuro(BigDecimal equivalenteeuro) {
        this.equivalenteeuro = equivalenteeuro;
    }

    //@JsonIgnore
    public Monedas getFkidmoneda() {
        return fkidmoneda;
    }

    public void setFkidmoneda(Monedas fkidmoneda) {
        this.fkidmoneda = fkidmoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistoricocambioeuro != null ? idhistoricocambioeuro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicocambioeuro)) {
            return false;
        }
        Historicocambioeuro other = (Historicocambioeuro) object;
        if ((this.idhistoricocambioeuro == null && other.idhistoricocambioeuro != null) || (this.idhistoricocambioeuro != null && !this.idhistoricocambioeuro.equals(other.idhistoricocambioeuro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesmonedas.Historicocambioeuro[ idhistoricocambioeuro=" + idhistoricocambioeuro + " ]";
    }
    
}
