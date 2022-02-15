package es.iespuertodelacruz.cc.institutorest.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.institutorest.dto.AsignaturaDTO;
import es.iespuertodelacruz.cc.institutorest.entity.Asignatura;
import es.iespuertodelacruz.cc.institutorest.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/api/v1/asignaturas")
public class AsignaturaREST {

	private Logger log = LoggerFactory.getLogger(AsignaturaREST.class);
	
	@Autowired
	private AsignaturaService service;
	
	@Operation(summary = "Devuelve todas las asignaturas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se devuelve la lista")
	})
	@GetMapping
	public ResponseEntity<?> getAllAsignaturas() {
		ArrayList<AsignaturaDTO> lista = new ArrayList<AsignaturaDTO>();
		service.findAll().forEach(a -> lista.add(a.toDTO()));
		return ResponseEntity.ok(lista);
	}
	
	
	@Operation(summary = "Devuelve un objeto asignatura con un id especifico")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se devuelve el objeto"),
			@ApiResponse(code = 404, message = "No se encontro la asignatura")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getAsignaturaById (@PathVariable("id") Integer id) {
		Optional<Asignatura> asignatura = service.findById(id);
		if (!asignatura.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(asignatura.get().toDTO());
	}
}
