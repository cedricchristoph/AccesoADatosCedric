package es.iespuertodelacruz.cc.restauranteapi.rest.v1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.service.MesaService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/mesas")
public class MesasRESTv1 {

	@Autowired
	private MesaService mesaService;
	
	@Operation(summary="Recibe fecha, hora y ocupantes y devuelve mesas libres/disponibles")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@GetMapping
	public ResponseEntity<?> getMesasLibres(
			@RequestParam(required = true, name = "time") long time,
			@RequestParam(required = true, name = "ocupantes") int ocupantes) {
		
		if (time < new Date().getTime())
			return ResponseEntity.badRequest().body("No se puede revisar mesas libres en el pasado");
		
		if (ocupantes < 1)
			return ResponseEntity.badRequest().body("Los ocupantes de una mesa deben ser mínimo 1");
		
		List<MesaSinServiciosDTO> mesas = new ArrayList<>();
		mesaService.findMesasLibres(time, ocupantes).forEach(m -> mesas.add(new MesaSinServiciosDTO(m)));
		return ResponseEntity.ok(mesas);
		
	}
	
}
