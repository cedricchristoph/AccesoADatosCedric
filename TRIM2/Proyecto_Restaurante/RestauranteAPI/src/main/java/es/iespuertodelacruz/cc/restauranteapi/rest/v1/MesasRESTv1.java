package es.iespuertodelacruz.cc.restauranteapi.rest.v1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.service.MesaService;

@RestController
@RequestMapping("/api/v1/mesas")
public class MesasRESTv1 {

	@Autowired
	private MesaService mesaService;
	
	@GetMapping
	public ResponseEntity<?> getMesasLibres(
			@RequestParam(required = true, name = "time") long time,
			@RequestParam(required = true, name = "ocupantes") int ocupantes) {
		
		if (time < new Date().getTime())
			return ResponseEntity.badRequest().body("No se puede revisar mesas libres en el pasado");
		
		if (ocupantes < 1)
			return ResponseEntity.badRequest().body("Los ocupantes de una mesa deben ser mínimo 1");
		
		List<MesaSinServiciosDTO> mesas = new ArrayList<>();
		mesaService.findMesasLibres(time, ocupantes).forEach(m -> mesas.add(new MesaSinServiciosDTO(m)));
		return ResponseEntity.ok(mesas);
		
	}
	
}
