package es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.entity.*;

public class AsientoDTO {
	
	private Integer idasiento;
	private String asunto;
	private double cuantia;
	private Date fecha;
	
	public AsientoDTO() {
		
	}
	
	public AsientoDTO(Asiento asiento) {
		this.idasiento = asiento.getIdasiento();
		this.asunto = asiento.getAsunto();
		this.cuantia = asiento.getCuantia();
		this.fecha = new Date(asiento.getFecha().longValue());
	}

	public Integer getIdasiento() {
		return idasiento;
	}

	public void setIdasiento(int idasiento) {
		this.idasiento = idasiento;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public double getCuantia() {
		return cuantia;
	}

	public void setCuantia(double cuantia) {
		this.cuantia = cuantia;
	}

	public String getFecha() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 return sdf.format(fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "AsientoDTO [idasiento=" + idasiento + ", asunto=" + asunto + ", cuantia=" + cuantia + ", fecha=" + fecha
				+ "]";
	}
	
	

}
