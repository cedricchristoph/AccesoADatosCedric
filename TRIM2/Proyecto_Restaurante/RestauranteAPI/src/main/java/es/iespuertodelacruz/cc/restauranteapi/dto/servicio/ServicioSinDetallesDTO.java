package es.iespuertodelacruz.cc.restauranteapi.dto.servicio;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil.DateFormat;

public class ServicioSinDetallesDTO {

	
	private int idservicio;

	private String fechacomienzo;

	private String fechafin;

	private byte pagada;

	private String reservada;
	
	@JsonIgnore
	private Mesa mesa;
	
	public ServicioSinDetallesDTO(Servicio servicio) {
		idservicio = servicio.getIdservicio();
		fechacomienzo = DateUtil.millisToStringDate(DateFormat.DD_MM_YYYY, servicio.getFechacomienzo().longValue());
		fechafin = DateUtil.millisToStringDate(DateFormat.DD_MM_YYYY, servicio.getFechafin().longValue());
		pagada = servicio.getPagada();
		reservada = servicio.getReservada();
		mesa = servicio.getMesa();
	}

	public int getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
	}

	public String getFechacomienzo() {
		return fechacomienzo;
	}

	public void setFechacomienzo(String fechacomienzo) {
		this.fechacomienzo = fechacomienzo;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public byte getPagada() {
		return pagada;
	}

	public void setPagada(byte pagada) {
		this.pagada = pagada;
	}

	public String getReservada() {
		return reservada;
	}

	public void setReservada(String reservada) {
		this.reservada = reservada;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	
	
}
