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

import es.iespuertodelacruz.cc.institutorest.entity.Matricula;
import es.iespuertodelacruz.cc.institutorest.service.MatriculaService;


@RestController
@RequestMapping("/api/matriculas")
public class MatriculaREST {

private Logger log = LoggerFactory.getLogger(MatriculaREST.class);
	
	@Autowired
	private MatriculaService service;
	
	@GetMapping
	public List<Matricula> getAllMatriculas() {
		ArrayList<Matricula> lista = new ArrayList<Matricula>();
		service.findAll().forEach(a -> lista.add(a));
		return lista;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAsignaturaById (@PathVariable("id") Integer id) {
		Optional<Matricula> matricula = service.findById(id);
		if (!matricula.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(matricula);
	}
	
}
