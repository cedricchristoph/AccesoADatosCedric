package es.iespuertodelacruz.cc.restauranteapi.rest.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.service.MesaService;

@RestController
@RequestMapping("/api/v2/mesas")
public class MesasREST {
	
	@Autowired
	MesaService mesaService;
	
	@GetMapping
	public ResponseEntity<?> getAllMesas() {
		List<MesaSinServiciosDTO> mesas = new ArrayList<>();
		mesaService.findAll().forEach(m -> mesas.add(new MesaSinServiciosDTO(m)));
		return ResponseEntity.ok(mesas);
	}
	
	@GetMapping("/{mesaid}")
	public ResponseEntity<?> getMesaById(@PathVariable("mesaid") Integer mesaId) {
		Optional<Mesa> mesa = mesaService.findById(mesaId);
		if (!mesa.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la mesa con el identificador indicado");
		return ResponseEntity.ok(mesa.get());
	}
	
	
}
