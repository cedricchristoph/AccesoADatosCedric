package es.iespuertodelacruz.cc.demo.rest;

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

import es.iespuertodelacruz.cc.demo.entity.Moneda;
import es.iespuertodelacruz.cc.demo.service.MonedaService;

@RestController
@RequestMapping("/api/monedas")
public class MonedaREST {
	
	private Logger log = LoggerFactory.getLogger(MonedaREST.class);
	
	@Autowired
	private MonedaService monedaService;
	
	@GetMapping
	public List<Moneda> getAllMonedas() {
		ArrayList<Moneda> monedas = new ArrayList<Moneda>();
		log.info("Ejecutando getAllMonedas");
		monedaService.findAll().forEach(m -> monedas.add(m));
		return monedas;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMonedaById(
			@PathVariable("id") Integer id) {
		Optional<Moneda> moneda = monedaService.findById(id);
		if (!moneda.isPresent()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(moneda);
	}
	
}
