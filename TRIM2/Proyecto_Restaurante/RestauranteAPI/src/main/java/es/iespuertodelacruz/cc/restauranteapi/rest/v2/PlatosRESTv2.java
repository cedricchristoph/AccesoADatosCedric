package es.iespuertodelacruz.cc.restauranteapi.rest.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.plato.PlatoDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
import es.iespuertodelacruz.cc.restauranteapi.service.PlatoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/platos")
public class PlatosRESTv2 {

	@Autowired
	PlatoService platoService;
	
	@Operation(summary="Recibe un JSON de mesa y lo inserta a la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@GetMapping
	public ResponseEntity<?> findAllPlatos(
			@RequestParam(required=false, name="disponible") Boolean disponible) {
				
		List<PlatoDTO> platos = new ArrayList<>();
	
		// Enviar platos con filtrado de disponibilidad
		if (disponible != null) {
			platoService.findByAvailability(disponible).forEach(p -> platos.add(new PlatoDTO(p)));
			return ResponseEntity.ok(platos);
		}
		
		// Enviar sin filtrado
		platoService.findAll().forEach(p -> platos.add(new PlatoDTO(p)));
		return ResponseEntity.ok(platos);
	}
	
	@Operation(summary="Devuelve el plato con el id proporcionado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")})
	@GetMapping("/{platoid}")
	public ResponseEntity<?> findPlatoById(@PathVariable("platoid") Integer platoid) {
		Optional<Plato> plato = platoService.findById(platoid);
		if (plato.isPresent())
			return ResponseEntity.ok(new PlatoDTO(plato.get()));
		return ResponseEntity.notFound().build();
	}
	
	@Operation(summary="Recibe un JSON de plato y actualiza estado de disponibilidad")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@PutMapping("/{platoid}")
	public ResponseEntity<?> updatePlatoAvailability(
			@PathVariable("platoid") Integer platoid,
			@RequestBody PlatoDTO plato) {
		
		// Comprobaciones de integridad de datos (El plato recibido solo esta obligado a contener ID y DISPONIBLE los demas datos se ignoran)
		if (plato == null)
			return ResponseEntity.badRequest().body("No se ha proporcionado un objeto PLATO a actualizar");
		
		Optional<Plato> platoEnBD = platoService.findById(platoid);		
		if (!platoEnBD.isPresent())
			return ResponseEntity.notFound().build();
		
		if (plato.getIdplato() != platoid)
			return ResponseEntity.badRequest().body("Los identificadores del plato en la URL y el JSON no coinciden");
		
		// Solamente actualizaremos el campo de disponibilidad
		platoEnBD.get().setDisponible(plato.getDisponible());
		Plato ok = platoService.save(platoEnBD.get());
		if (ok != null)
			return ResponseEntity.ok("Disponibilidad del plato actualizado");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo actualizar la disponibilidad del plato");
		
	}
	
}
