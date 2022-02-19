package es.iespuertodelacruz.cc.restauranteapi.dto;

import java.util.Collections;
import java.util.stream.Collectors;

import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ConvertableDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;

public class MesaSinServiciosDTO implements ConvertableDTO<Mesa> {

	private Integer nummesa;
	private int ocupantesmax;
	
	public MesaSinServiciosDTO() {
		
	}
	
	public MesaSinServiciosDTO(Mesa mesa) {
		nummesa = mesa.getNummesa();
		ocupantesmax = mesa.getOcupantesmax();
	}
	
	@Override
	public Mesa convertToEntity() {
		Mesa mesa = new Mesa();
		mesa.setNummesa(nummesa);
		mesa.setOcupantesmax(ocupantesmax);
		mesa.setServicios(Collections.EMPTY_LIST);
		return mesa;
	}
	
	public Integer getNummesa() {
		return nummesa;
	}

	public void setNummesa(Integer nummesa) {
		this.nummesa = nummesa;
	}

	public int getOcupantesmax() {
		return ocupantesmax;
	}

	public void setOcupantesmax(int ocupantesmax) {
		this.ocupantesmax = ocupantesmax;
	}

	
	
	
	
}
