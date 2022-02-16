package es.iespuertodelacruz.cc.restauranteapi.dto;

import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;

public class MesaSinServiciosDTO {

	private int nummesa;
	private int ocupantesmax;
	
	public MesaSinServiciosDTO(Mesa mesa) {
		nummesa = mesa.getNummesa();
		ocupantesmax = mesa.getOcupantesmax();
	}

	public int getNummesa() {
		return nummesa;
	}

	public void setNummesa(int nummesa) {
		this.nummesa = nummesa;
	}

	public int getOcupantesmax() {
		return ocupantesmax;
	}

	public void setOcupantesmax(int ocupantesmax) {
		this.ocupantesmax = ocupantesmax;
	}
	
	
	
}
