package es.iespuertodelacruz.cc.institutorest.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.institutorest.dto.AlumnoDTO;
import es.iespuertodelacruz.cc.institutorest.dto.MatriculaDTO;
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
		ArrayList<AlumnoDTO> lista = new ArrayList<AlumnoDTO>();
		alumnoServices.findAll().forEach(a -> lista.add(a.toDTO()));
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<?> getAlumnoById (@PathVariable("dni") String id) {
		Optional<Alumno> alumno = alumnoServices.findById(id);
		if (!alumno.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(alumno.get().toDTO());
	}
	
	@GetMapping("/{dni}/matriculas")
	public ResponseEntity<?> getMatriculas(@PathVariable("dni") String dni) {
		ArrayList<MatriculaDTO> matriculas = new ArrayList<MatriculaDTO>();
		matriculaService.findByAlumno(dni).forEach(m -> matriculas.add(m.toDTO()));
		return ResponseEntity.ok(matriculas);
	}
	
	@PostMapping("/{dni}/matriculas")
	public ResponseEntity<?> insertMatricula(@PathVariable("dni") String dni, @RequestBody MatriculaDTO dto) {
		Optional<Matricula> matricula = matriculaService.findById(dto.getIdmatricula());
		if (matricula.isPresent()) return ResponseEntity.badRequest().body("La matricula ya existe");
		Matricula m = matricula.get();
		m = dto.toMatricula();
		matriculaService.save(m);
		return ResponseEntity.status(HttpStatus.CREATED).body("Matricula creada correctamente");
	}
}
