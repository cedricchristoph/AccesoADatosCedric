package es.iespuertodelacruz.cc.restauranteapi.rest.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ServicioConDetallesDTO;
import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ServicioSinDetallesDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
import es.iespuertodelacruz.cc.restauranteapi.service.DetallefacturaService;
import es.iespuertodelacruz.cc.restauranteapi.service.MesaService;
import es.iespuertodelacruz.cc.restauranteapi.service.ServicioService;

@RestController
@RequestMapping("/api/v2/mesas")
public class MesasREST {
	
	@Autowired
	MesaService mesaService;
	
	@Autowired
	ServicioService servicioService;
	
	@Autowired
	DetallefacturaService detalleService;
	
	
	/* MESAS */
	
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
	
	
	/* SERVICIOS */
	
	@GetMapping("/{mesaid}/servicios")
	public ResponseEntity<?> getServiciosFromMesa(@PathVariable("mesaid") Integer mesaId) {
		List<ServicioSinDetallesDTO> servicios = new ArrayList<>();
		servicioService.findByMesa(mesaId).forEach(s -> servicios.add(new ServicioSinDetallesDTO(s)));
		return ResponseEntity.ok(servicios);
	}
	
	@GetMapping("/{mesaid}/servicios/{servicioid}")
	public ResponseEntity<?> getServicioById(
			@PathVariable("mesaid") Integer mesaId, 
			@PathVariable("servicioid") Integer servicioId) {
		
		Optional<Servicio> servicio = servicioService.findById(servicioId);
		if (servicio.isPresent()) {
			if (servicio.get().getMesa().getNummesa() != mesaId) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El servicio solicidato no pertenece a la mesa indicada");
			}
			System.out.println("DETALLES EN ESTE SERVICIO: " + servicio.get().getDetallefacturas().size());
			return ResponseEntity.ok(servicio.get()); //new ServicioConDetallesDTO(servicio.get())
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio");
		}
		
	}
	
	@DeleteMapping("/{mesaid}/servicios/{servicioid}")
	public ResponseEntity<?> deleteServicioById(
			@PathVariable("mesaid") Integer mesaId, 
			@PathVariable("servicioid") Integer servicioId) {
		
		Optional<Servicio> servicio = servicioService.findById(servicioId);
		if (servicio.isPresent()) {
			if (servicio.get().getMesa().getNummesa() != mesaId) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El servicio solicidato no pertenece a la mesa indicada");
			}
			servicioService.deleteById(servicio.get());
			if (!servicioService.findById(servicioId).isPresent()) 
				return ResponseEntity.ok("Servicio eliminado correctamente");
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo eliminar el servicio");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio");
		}
		
	}
	
	
	/* DETALLES FACTURA */
	
	@GetMapping("/api/v2/mesas/{mesaid}/servicios/{servicioid}/detallesfactura")
	public ResponseEntity<?> getDetallesFacturaDeServicio(
			@PathVariable("mesaid") Integer mesaId, 
			@PathVariable("servicioid") Integer servicioId) {
		
		List<Detallefactura> detalles = detalleService.findByIdServicio(servicioId);
		return ResponseEntity.ok(detalles);
		
	}
	
	
	
	/* PLATOS */
	
	
}
