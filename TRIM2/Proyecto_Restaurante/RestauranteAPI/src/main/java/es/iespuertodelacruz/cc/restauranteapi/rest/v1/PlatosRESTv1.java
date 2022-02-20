package es.iespuertodelacruz.cc.restauranteapi.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.plato.PlatoDTO;
import es.iespuertodelacruz.cc.restauranteapi.service.PlatoService;

@RestController
@RequestMapping("/api/v1/platos")
public class PlatosRESTv1 {

	@Autowired
	private PlatoService platoService;
	
	@GetMapping
	public ResponseEntity<?> getPlatos() {
		List<PlatoDTO> platos = new ArrayList<>();
		platoService.findAll().forEach(p -> platos.add(new PlatoDTO(p)));
		return ResponseEntity.ok(platos);		
	}
	
}
