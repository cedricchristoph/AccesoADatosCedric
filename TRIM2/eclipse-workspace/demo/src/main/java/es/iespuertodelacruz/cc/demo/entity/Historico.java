package es.iespuertodelacruz.cc.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the historicocambioeuro database table.
 * 
 */
@Entity
@Table(name="historicocambioeuro")
@NamedQuery(name="Historico.findAll", query="SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idhistoricocambioeuro;

	private BigDecimal equivalenteeuro;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="fkidmoneda")
	@JsonIgnore
	private Moneda moneda;

	public Historico() {
	}

	public int getIdhistoricocambioeuro() {
		return this.idhistoricocambioeuro;
	}

	public void setIdhistoricocambioeuro(int idhistoricocambioeuro) {
		this.idhistoricocambioeuro = idhistoricocambioeuro;
	}

	public BigDecimal getEquivalenteeuro() {
		return this.equivalenteeuro;
	}

	public void setEquivalenteeuro(BigDecimal equivalenteeuro) {
		this.equivalenteeuro = equivalenteeuro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

}