package es.iespuertodelacruz.cc.restauranteapi.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the servicio database table.
 * 
 */
@Entity
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idservicio;

	private BigInteger fechacomienzo;

	private BigInteger fechafin;

	private byte pagada;

	private String reservada;

	//bi-directional many-to-one association to Detallefactura
	@OneToMany(mappedBy="servicio")
	@JsonIgnore
	private List<Detallefactura> detallefacturas;

	//bi-directional many-to-one association to Mesa
	@ManyToOne
	@JoinColumn(name="fknummesa")
	@JsonIgnore
	private Mesa mesa;

	public Servicio() {
	}

	public int getIdservicio() {
		return this.idservicio;
	}

	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
	}

	public BigInteger getFechacomienzo() {
		return this.fechacomienzo;
	}

	public void setFechacomienzo(BigInteger fechacomienzo) {
		this.fechacomienzo = fechacomienzo;
	}

	public BigInteger getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(BigInteger fechafin) {
		this.fechafin = fechafin;
	}

	public byte getPagada() {
		return this.pagada;
	}

	public void setPagada(byte pagada) {
		this.pagada = pagada;
	}

	public String getReservada() {
		return this.reservada;
	}

	public void setReservada(String reservada) {
		this.reservada = reservada;
	}

	public List<Detallefactura> getDetallefacturas() {
		return this.detallefacturas;
	}

	public void setDetallefacturas(List<Detallefactura> detallefacturas) {
		this.detallefacturas = detallefacturas;
	}

	public Detallefactura addDetallefactura(Detallefactura detallefactura) {
		getDetallefacturas().add(detallefactura);
		detallefactura.setServicio(this);

		return detallefactura;
	}

	public Detallefactura removeDetallefactura(Detallefactura detallefactura) {
		getDetallefacturas().remove(detallefactura);
		detallefactura.setServicio(null);

		return detallefactura;
	}

	public Mesa getMesa() {
		return this.mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}