package es.iespuertodelacruz.cc.restauranteapi.dto.servicio;

import java.math.BigInteger;
import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil.DateFormat;

public class ServicioSinDetallesDTO implements ConvertableDTO<Servicio>{

	
	private Integer idservicio;

	private String fechacomienzo;

	private String fechafin;

	private byte pagada;

	private String reservada;
	
	@JsonIgnore
	private MesaSinServiciosDTO mesa;
	
	public ServicioSinDetallesDTO() {
		
	}
	
	public ServicioSinDetallesDTO(Servicio servicio) {
		idservicio = servicio.getIdservicio();
		fechacomienzo = DateUtil.millisToStringDate(DateFormat.DD_MM_YYYY_HH_MM, servicio.getFechacomienzo().longValue());
		try {
			fechafin = DateUtil.millisToStringDate(DateFormat.DD_MM_YYYY_HH_MM, servicio.getFechafin().longValue());
		} catch (NullPointerException ex) {}
		pagada = servicio.getPagada();
		reservada = servicio.getReservada();
		mesa = new MesaSinServiciosDTO(servicio.getMesa());
	}
	
	@Override
	public Servicio convertToEntity() {
		Servicio servicio = new Servicio();
		servicio.setIdservicio(idservicio);
		try {
			servicio.setFechacomienzo(new BigInteger(String.valueOf(DateUtil.stringDateToMillis(DateFormat.DD_MM_YYYY_HH_MM, fechacomienzo))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			servicio.setFechafin(new BigInteger(String.valueOf(DateUtil.stringDateToMillis(DateFormat.DD_MM_YYYY_HH_MM, fechafin))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servicio.setPagada(pagada);
		servicio.setReservada(reservada);
		try {
			servicio.setMesa(mesa.convertToEntity());
		} catch (NullPointerException ex) {}
		return servicio;
	}

	public Integer getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(Integer idservicio) {
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

	public MesaSinServiciosDTO getMesa() {
		return mesa;
	}

	public void setMesa(MesaSinServiciosDTO mesa) {
		this.mesa = mesa;
	}

	
	
}
