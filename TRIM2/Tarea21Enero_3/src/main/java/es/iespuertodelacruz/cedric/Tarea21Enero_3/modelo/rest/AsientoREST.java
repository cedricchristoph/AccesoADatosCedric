package es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.entity.Asiento;
import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.service.AsientoService;
import es.iespuertodelacruz.cedric.Tarea21Enero_3.modelo.dto.*;


@RestController
@RequestMapping("/api/asientos")
public class AsientoREST {
	
	@Autowired
	private AsientoService service;
	
	@GetMapping
	public ResponseEntity<?> selectAll(@RequestParam(required=false, name="asuntoincluye")String incluye) {
		ArrayList<AsientoDTO> results = new ArrayList<>();
		if (incluye != null) {
			service.findConBusqueda("%" + incluye.toUpperCase() + "%").forEach(a -> results.add(new AsientoDTO(a)));
			return ResponseEntity.ok(results);
		}
		service.findAll().forEach(a -> results.add(new AsientoDTO(a)));
		return ResponseEntity.ok(results);
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody AsientoDTO dto) {
		if (dto == null || dto.getIdasiento() != null)
			return ResponseEntity.badRequest().build();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Asiento asiento = new Asiento();
		asiento.setAsunto(dto.getAsunto().toUpperCase());
		asiento.setCuantia(dto.getCuantia());
		try {
			asiento.setFecha(sdf.parse(dto.getFecha()).getTime());
		} catch (ParseException e) {
			return ResponseEntity.internalServerError().build();
		}
		service.save(asiento);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody AsientoDTO dto) {
		if (dto == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se envió nada");
		Optional<Asiento> found = service.findById(id);
		if (!found.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro");
		if (found.get().getIdasiento() != dto.getIdasiento())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ids no coinciden");
		Asiento asiento = new Asiento();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		asiento.setAsunto(dto.getAsunto().toUpperCase());
		asiento.setCuantia(dto.getCuantia());
		try {
			asiento.setFecha(sdf.parse(dto.getFecha()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok("Actualizado");
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
