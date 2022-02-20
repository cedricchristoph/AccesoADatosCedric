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

import es.iespuertodelacruz.cc.restauranteapi.dto.plato.PlatoDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
import es.iespuertodelacruz.cc.restauranteapi.service.PlatoService;

@RestController
@RequestMapping("/api/v3/platos")
public class PlatosRESTv3 {

	@Autowired
	PlatoService platoService;
	
	@PostMapping
	public ResponseEntity<?> insertNewPlato(
			@RequestBody PlatoDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha enviado un objeto plato a añadir");
		
		Plato plato = dto.convertToEntity();
		if (platoService.save(plato) != null)
			return ResponseEntity.ok("Plato añadido correctamente");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo añadir el plato");
		
	}
	
	@PutMapping("/{platoid}")
	public ResponseEntity<?> updatePlato(
			@PathVariable("platoid") Integer platoid,
			@RequestBody PlatoDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha enviado un objeto plato a actualizar");
		
		if (dto.getIdplato() == null)
			return ResponseEntity.badRequest().body("El objeto enviado no contiene identificador");
		
		Optional<Plato> plato = platoService.findById(platoid);		
		
		if (!plato.isPresent())
			return ResponseEntity.notFound().build();
		
		if (dto.getIdplato() != platoid)
			return ResponseEntity.badRequest().body("Los identificadores de plato no coinciden en URL y cuerpo");
		
		// Actualizamos los datos proporcionados en el dto y mantenemos los que ya estan si  no se proporciona
		plato.get().setDescripcion(dto.getDescripcion());
		plato.get().setDisponible(dto.getDisponible());
		plato.get().setNombre(dto.getNombre());
		plato.get().setPreciounidad(dto.getPreciounidad());
		
		if (platoService.save(plato.get()) != null)
			return ResponseEntity.ok("Plato actualizado correctamente");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo actualizar el plato");
		
	}
	
	@DeleteMapping("/{platoid}")
	public ResponseEntity<?> deletePlato(
			@PathVariable("platoid") Integer platoid) {
		
		Optional<Plato> plato = platoService.findById(platoid);
		if (!plato.isPresent())
			return ResponseEntity.notFound().build();
		platoService.deleteById(plato.get());
		return ResponseEntity.ok("Plato eliminado correctamente");
		
	}
	
	
	
}
