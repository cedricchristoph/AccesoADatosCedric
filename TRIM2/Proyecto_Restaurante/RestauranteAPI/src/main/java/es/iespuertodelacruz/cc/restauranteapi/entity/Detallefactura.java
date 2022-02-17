package es.iespuertodelacruz.cc.restauranteapi.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the detallefactura database table.
 * 
 */
@Entity
@NamedQuery(name="Detallefactura.findAll", query="SELECT d FROM Detallefactura d")
public class Detallefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddetallefactura;

	private int cantidad;

	private double preciounidad;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="fkidplato")
	private Plato plato;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="fkidservicio")
	@JsonIgnore
	private Servicio servicio;

	public Detallefactura() {
	}

	public int getIddetallefactura() {
		return this.iddetallefactura;
	}

	public void setIddetallefactura(int iddetallefactura) {
		this.iddetallefactura = iddetallefactura;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPreciounidad() {
		return this.preciounidad;
	}

	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

}