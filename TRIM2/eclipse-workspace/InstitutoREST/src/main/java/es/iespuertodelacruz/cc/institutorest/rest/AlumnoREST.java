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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.institutorest.entity.Alumno;
import es.iespuertodelacruz.cc.institutorest.entity.Matricula;
import es.iespuertodelacruz.cc.institutorest.service.AlumnoService;
import es.iespuertodelacruz.cc.institutorest.service.MatriculaService;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoREST {

	private Logger log = LoggerFactory.getLogger(AlumnoREST.class);
	
	@Autowired
	private AlumnoService alumnoServices;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@GetMapping
	public ResponseEntity<?> getAllAlumnos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		alumnoServices.findAll().forEach(a -> lista.add(a));
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<?> getAlumnoById (@PathVariable("dni") String id) {
		Optional<Alumno> alumno = alumnoServices.findById(id);
		if (!alumno.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(alumno);
	}
	
	@GetMapping("/{dni}/matriculas")
	public ResponseEntity<?> getMatriculas(@PathVariable("dni") String dni) {
		ArrayList<Matricula> matriculas = (ArrayList<Matricula>) matriculaService.findByAlumno(dni);
		return ResponseEntity.ok(matriculas);
	}
}
