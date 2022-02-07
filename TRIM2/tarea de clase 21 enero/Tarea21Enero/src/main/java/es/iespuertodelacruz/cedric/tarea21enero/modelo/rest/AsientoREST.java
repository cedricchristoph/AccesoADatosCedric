package es.iespuertodelacruz.cedric.tarea21enero.modelo.rest;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cedric.tarea21enero.modelo.entity.Asiento;
import es.iespuertodelacruz.cedric.tarea21enero.modelo.service.AsientoService;
import es.iespuertodelacruz.cedric.tarea21enero.modelo.dto.*;


@RestController
@RequestMapping("/api/asientos")
public class AsientoREST {
	
	@Autowired
	private AsientoService service;
	
	@GetMapping
	public ResponseEntity<?> selectAll() {
		ArrayList<AsientoDTO> results = new ArrayList<>();
		service.findAll().forEach(a -> results.add(new AsientoDTO(a)));
		return ResponseEntity.ok(results);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		Optional<Asiento> optional = service.findById(id);
		if (!optional.isPresent())
			return ResponseEntity.notFound().build();
		service.deleteById(optional.get());
		return ResponseEntity.ok(null);
	}
	
}
