package es.iespuertodelacruz.cc.restauranteapi.dto.servicio;

public interface ConvertableDTO<ENTITY> {

	public ENTITY convertToEntity();
	
}
