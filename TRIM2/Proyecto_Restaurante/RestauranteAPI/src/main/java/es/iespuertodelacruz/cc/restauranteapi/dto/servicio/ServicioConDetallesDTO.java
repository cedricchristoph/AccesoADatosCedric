package es.iespuertodelacruz.cc.restauranteapi.dto.servicio;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil.DateFormat;

public class ServicioConDetallesDTO implements ConvertableDTO<Servicio> {


	private int idservicio;

	private String fechacomienzo;

	private String fechafin;

	private byte pagada;

	private String reservada;
	
	private List<Detallefactura> detallefacturas;
	
	@JsonIgnore
	private Mesa mesa;
	
	public ServicioConDetallesDTO(Servicio servicio) {
		idservicio = servicio.getIdservicio();
		fechacomienzo = DateUtil.millisToStringDate(DateFormat.DD_MM_YYYY, servicio.getFechacomienzo().longValue());
		fechafin = DateUtil.millisToStringDate(DateFormat.DD_MM_YYYY, servicio.getFechafin().longValue());
		pagada = servicio.getPagada();
		reservada = servicio.getReservada();
		mesa = servicio.getMesa();
		detallefacturas = servicio.getDetallefacturas();
	}	
	
	@Override
	public Servicio convertToEntity() {
		Servicio servicio = new Servicio();
		servicio.setIdservicio(idservicio);
		servicio.setIdservicio(idservicio);
		servicio.setDetallefacturas(detallefacturas);
		servicio.setPagada(pagada);
		servicio.setMesa(mesa);
		try {
			servicio.setFechacomienzo(new BigInteger(String.valueOf(DateUtil.stringDateToMillis(DateFormat.DD_MM_YYYY, fechacomienzo))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			servicio.setFechafin(new BigInteger(String.valueOf(DateUtil.stringDateToMillis(DateFormat.DD_MM_YYYY, fechafin))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servicio;
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
