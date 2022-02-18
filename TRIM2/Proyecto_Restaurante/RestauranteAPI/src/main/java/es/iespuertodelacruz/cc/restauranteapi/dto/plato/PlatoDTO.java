package es.iespuertodelacruz.cc.restauranteapi.dto.plato;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ConvertableDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;

public class PlatoDTO implements ConvertableDTO<Plato>{

	private int idplato;
	private String descripcion;
	private byte disponible;

	private String nombre;
	private double preciounidad;
	
	@JsonIgnore
	private List<Detallefactura> detallefacturas;
	
	public PlatoDTO() {
		
	}
	
	public PlatoDTO(Plato plato) {
		idplato = plato.getIdplato();
		descripcion = plato.getDescripcion();
		disponible = plato.getDisponible();
		nombre = plato.getNombre();
		preciounidad = plato.getPreciounidad();
		detallefacturas = plato.getDetallefacturas();		
	}

	
	@Override
	public Plato convertToEntity() {
		Plato plato = new Plato();
		plato.setIdplato(idplato);
		plato.setDescripcion(descripcion);
		plato.setDisponible(disponible);
		plato.setNombre(nombre);
		plato.setPreciounidad(preciounidad);
		plato.setDetallefacturas(detallefacturas);
		return plato;
	}

	public int getIdplato() {
		return idplato;
	}

	public void setIdplato(int idplato) {
		this.idplato = idplato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte getDisponible() {
		return disponible;
	}

	public void setDisponible(byte disponible) {
		this.disponible = disponible;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPreciounidad() {
		return preciounidad;
	}

	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}

	public List<Detallefactura> getDetallefacturas() {
		return detallefacturas;
	}

	public void setDetallefacturas(List<Detallefactura> detallefacturas) {
		this.detallefacturas = detallefacturas;
	}
	
	
}
