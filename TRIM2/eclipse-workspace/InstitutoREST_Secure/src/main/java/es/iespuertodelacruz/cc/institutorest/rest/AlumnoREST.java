package es.iespuertodelacruz.cc.institutorest.rest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private AlumnoService alumnoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	/**
	 * Funcion que devuelve una lista de todos los alumnos
	 * @return
	 * Devuelve ok si todo ha ido bien
	 */
	@GetMapping
	public ResponseEntity<?> getAllAlumnos(@RequestParam(required=false, name="nombre")String nombre) {
		if (nombre == null) {
			ArrayList<AlumnoDTO> lista = new ArrayList<AlumnoDTO>();
			alumnoService.findAll().forEach(a -> lista.add(a.toDTO()));
			return ResponseEntity.ok(lista);
		} else {
			ArrayList<AlumnoDTO> lista = new ArrayList<AlumnoDTO>();
			alumnoService.findByNombre("%" + nombre + "%").forEach(a -> lista.add(a.toDTO()));
			return ResponseEntity.ok(lista);
		}
	}
	
	/**
	 * Funcion que devuelve la informacion de un alumno dado su dni
	 * @param id Dni del alumno
	 * @return
	 * Devuelve notFound si no se encontro el alumno
	 * Devuelve ok si todo ha ido bien
	 */
	@GetMapping("/{dni}")
	public ResponseEntity<?> getAlumnoById (@PathVariable("dni") String id) {
		Optional<Alumno> alumno = alumnoService.findById(id);
		if (!alumno.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(alumno.get().toDTO());
	}
	
	/**
	 * Funcion para devolver una lista de matriculas dado el dni de un alumno
	 * @param dni del alumno
	 * @return
	 * Devuelve 
	 */
	@GetMapping("/{dni}/matriculas")
	public ResponseEntity<?> getMatriculas(@PathVariable("dni") String dni) {
		ArrayList<MatriculaDTO> matriculas = new ArrayList<MatriculaDTO>();
		Optional<Alumno> alumno = alumnoService.findById(dni);
		if (!alumno.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el alumno con el dni indicado");
		matriculaService.findByAlumno(dni).forEach(m -> matriculas.add(m.toDTO()));
		return ResponseEntity.ok(matriculas);
	}
	
	/**
	 * Funcion para crear una matricula para un alumno indicado
	 * @param dni Dni del alumno
	 * @param dto Objeto MatriculaDTO a crear
	 * @return 
	 * Devuelve BadRequest si la matricula ya existe
	 * Devuelve Created si la matricula ha sido creado
	 */
	@PostMapping("/{dni}/matriculas")
	public ResponseEntity<?> insertMatricula(@PathVariable("dni") String dni, @RequestBody MatriculaDTO dto) {
		Optional<Matricula> matricula = matriculaService.findById(dto.getIdmatricula());
		if (matricula.isPresent()) return ResponseEntity.badRequest().body("La matricula ya existe");
		Optional<Alumno> alumno = alumnoService.findById(dni);
		if (!alumno.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el alumno con el dni indicado");
		Matricula newMatricula = dto.toMatricula();
		newMatricula.setAlumno(alumno.get());
		matriculaService.save(newMatricula);
		return ResponseEntity.status(HttpStatus.CREATED).body("Matricula creada correctamente");
	}
	
	/**
	 * Funcion para eliminar una matricula de un alumno
	 * @param dni Del alumno
	 * @param id De la matricula a eliminar
	 * @return 
	 * Devuelve ResponseEntity (not Found si no se encuentra la matricula o dicho id no pertenece al alumno indicado)
	 * Devuelve Ok si se ha eliminado correctamente la matricula
	 */
	@DeleteMapping("/{dni}/matriculas/{id}")
	public ResponseEntity<?> deleteMatricula(@PathVariable("dni") String dni, @PathVariable("id") Integer id) {
		Optional<Matricula> matricula = matriculaService.findById(id);
		if (!matricula.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ninguna matricula con el identificador indicado");
		if (!matricula.get().getAlumno().getDni().equals(dni)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La matricula no pertenece al alumno indicado");
		matriculaService.deleteById(matricula.get());
		return ResponseEntity.ok("Eliminado correctamente la matricula");
	}
}
