package es.iespuertodelacruz.cc.restauranteapi.rest.v3;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.service.MesaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v3/mesas")
public class MesasRESTv3 {

	@Autowired
	MesaService mesaService;
	
	@Operation(summary="Recibe JSON de mesa y lo inserta a la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@PostMapping
	public ResponseEntity<?> insertNewMesa(
			@RequestBody MesaSinServiciosDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha proporcionado un objeto mesa a añadir");
		if (dto.getNummesa() == null)
			return ResponseEntity.badRequest().body("No se ha especificado un identificador para la mesa");
		if (dto.getOcupantesmax() == 0)
			return ResponseEntity.badRequest().body("Los ocupantes máximos de una mesa no pueden ser 0");
		
		if (mesaService.save(dto.convertToEntity()) != null)
			return ResponseEntity.ok("Se ha creado la nueva mesa");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se ha creado la mesa");
		
	}
	
	@Operation(summary="Recibe un JSON de mesa y lo actualiza en la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@PutMapping("/{mesaid}")
	public ResponseEntity<?> updateMesa(
			@PathVariable("mesaid") Integer mesaid,
			@RequestBody MesaSinServiciosDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha proporcionado un objeto mesa a añadir");
		if (dto.getNummesa() != null && dto.getNummesa() != mesaid)
			return ResponseEntity.badRequest().body("Los identificadores en la URL y el cuerpo de mensaje no coinciden");
		if (dto.getOcupantesmax() == 0)
			return ResponseEntity.badRequest().body("Los ocupantes máximos de una mesa no pueden ser 0");
		
		Optional<Mesa> mesa = mesaService.findById(mesaid);
		if (!mesa.isPresent())
			return ResponseEntity.notFound().build();
		mesa.get().setOcupantesmax(dto.getOcupantesmax());
		if (mesaService.save(mesa.get()) != null)
			return ResponseEntity.ok("Mesa actualizada correctamente");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo actualizar la mesa");
		
	}
	
	@Operation(summary="Elimina una mesa segun id proporcionado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")})
	@DeleteMapping("/{mesaid}")
	public ResponseEntity<?> deleteMesa(
			@PathVariable("mesaid") Integer mesaid) {
		
		Optional<Mesa> mesa = mesaService.findById(mesaid);
		if (!mesa.isPresent())
			return ResponseEntity.notFound().build();
		mesaService.deleteById(mesa.get());
		return ResponseEntity.ok("Se ha eliminado la mesa correctamente");
		
	}
	
	
}
