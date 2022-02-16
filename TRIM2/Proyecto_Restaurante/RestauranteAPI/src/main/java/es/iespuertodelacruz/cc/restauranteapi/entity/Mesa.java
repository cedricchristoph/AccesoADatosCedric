package es.iespuertodelacruz.cc.restauranteapi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mesas database table.
 * 
 */
@Entity
@Table(name="mesas")
@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
public class Mesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nummesa;

	private int ocupantesmax;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="mesa")
	private List<Servicio> servicios;

	public Mesa() {
	}

	public int getNummesa() {
		return this.nummesa;
	}

	public void setNummesa(int nummesa) {
		this.nummesa = nummesa;
	}

	public int getOcupantesmax() {
		return this.ocupantesmax;
	}

	public void setOcupantesmax(int ocupantesmax) {
		this.ocupantesmax = ocupantesmax;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setMesa(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setMesa(null);

		return servicio;
	}

}