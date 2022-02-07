package es.iespuertodelacruz.cedric.tarea21enero.modelo.dto;

import java.util.Date;
import es.iespuertodelacruz.cedric.tarea21enero.modelo.entity.Asiento;

public class AsientoDTO {
	
	private int idasiento;
	private String asunto;
	private double cuantia;
	private Date fecha;
	
	public AsientoDTO(Asiento asiento) {
		this.idasiento = asiento.getIdasiento();
		this.asunto = asiento.getAsunto();
		this.cuantia = asiento.getCuantia();
		this.fecha = new Date(asiento.getFecha().longValue());
	}

	public int getIdasiento() {
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

	public Date getFecha() {
		return fecha;
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
