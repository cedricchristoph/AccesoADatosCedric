package es.iespuertodelacruz.cc.restauranteapi.dto.detallefactura;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ConvertableDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;

public class DetalleFacturaDTO implements ConvertableDTO<Detallefactura> {

	private int iddetallefactura;
	private int cantidad;
	private double preciounidad;
	private Plato plato;
	
	@JsonIgnore
	private Servicio servicio;
	
	public DetalleFacturaDTO() {
		
	}
	
	public DetalleFacturaDTO(Detallefactura df) {
		iddetallefactura = df.getIddetallefactura();
		cantidad = df.getCantidad();
		preciounidad = df.getPreciounidad();
		plato = df.getPlato();
		servicio = df.getServicio();
	}

	@Override
	public Detallefactura convertToEntity() {
		Detallefactura df = new Detallefactura();
		df.setIddetallefactura(iddetallefactura);
		df.setCantidad(cantidad);
		df.setPreciounidad(preciounidad);
		df.setPlato(plato);
		df.setServicio(servicio);
		return df;
	}

	public int getIddetallefactura() {
		return iddetallefactura;
	}

	public void setIddetallefactura(int iddetallefactura) {
		this.iddetallefactura = iddetallefactura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPreciounidad() {
		return preciounidad;
	}

	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
	
	
}
