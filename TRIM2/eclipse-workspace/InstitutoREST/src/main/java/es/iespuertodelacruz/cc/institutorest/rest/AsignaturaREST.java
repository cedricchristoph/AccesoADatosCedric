package es.iespuertodelacruz.cc.institutorest.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.institutorest.dto.AsignaturaDTO;
import es.iespuertodelacruz.cc.institutorest.entity.Asignatura;
import es.iespuertodelacruz.cc.institutorest.service.AsignaturaService;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaREST {

	private Logger log = LoggerFactory.getLogger(AsignaturaREST.class);
	
	@Autowired
	private AsignaturaService service;
	
	@GetMapping
	public ResponseEntity<?> getAllAsignaturas() {
		ArrayList<AsignaturaDTO> lista = new ArrayList<AsignaturaDTO>();
		service.findAll().forEach(a -> lista.add(a.toDTO()));
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAsignaturaById (@PathVariable("id") Integer id) {
		Optional<Asignatura> asignatura = service.findById(id);
		if (!asignatura.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(asignatura.get().toDTO());
	}
}
