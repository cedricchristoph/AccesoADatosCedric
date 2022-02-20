package es.iespuertodelacruz.cc.restauranteapi.rest.v2;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.restauranteapi.dto.MesaSinServiciosDTO;
import es.iespuertodelacruz.cc.restauranteapi.dto.detallefactura.DetalleFacturaDTO;
import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ServicioConDetallesDTO;
import es.iespuertodelacruz.cc.restauranteapi.dto.servicio.ServicioSinDetallesDTO;
import es.iespuertodelacruz.cc.restauranteapi.entity.Detallefactura;
import es.iespuertodelacruz.cc.restauranteapi.entity.Mesa;
import es.iespuertodelacruz.cc.restauranteapi.entity.Plato;
import es.iespuertodelacruz.cc.restauranteapi.entity.Servicio;
import es.iespuertodelacruz.cc.restauranteapi.service.DetallefacturaService;
import es.iespuertodelacruz.cc.restauranteapi.service.MesaService;
import es.iespuertodelacruz.cc.restauranteapi.service.PlatoService;
import es.iespuertodelacruz.cc.restauranteapi.service.ServicioService;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil;
import es.iespuertodelacruz.cc.restauranteapi.util.DateUtil.DateFormat;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/mesas")
public class MesasRESTv2 {
	
	@Autowired
	MesaService mesaService;
	
	@Autowired
	ServicioService servicioService;
	
	@Autowired
	DetallefacturaService detalleService;
	
	@Autowired
	PlatoService platoService;
	
	/* MESAS */
	
	@Operation(summary="Devuelve la lista de todas las mesas")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido")})
	@GetMapping
	public ResponseEntity<?> getAllMesas() {
		List<MesaSinServiciosDTO> mesas = new ArrayList<>();
		mesaService.findAll().forEach(m -> mesas.add(new MesaSinServiciosDTO(m)));
		return ResponseEntity.ok(mesas);
	}
	
	@Operation(summary="Devuelve la mesa con el identificador proporcionado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado") })
	@GetMapping("/{mesaid}")
	public ResponseEntity<?> getMesaById(@PathVariable("mesaid") Integer mesaId) {
		Optional<Mesa> mesa = mesaService.findById(mesaId);
		if (!mesa.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la mesa con el identificador indicado");
		return ResponseEntity.ok(mesa.get());
	}
	
	
	/* SERVICIOS */
	
	@Operation(summary="Devuelve la lista de servicios de una mesa específica")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado") })
	@GetMapping("/{mesaid}/servicios")
	public ResponseEntity<?> getServiciosFromMesa(@PathVariable("mesaid") Integer mesaId) {
		List<ServicioSinDetallesDTO> servicios = new ArrayList<>();
		servicioService.findByMesa(mesaId).forEach(s -> servicios.add(new ServicioSinDetallesDTO(s)));
		return ResponseEntity.ok(servicios);
	}
	
	@Operation(summary="Devuelve un servicio según identificador indicado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado") })
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
			return ResponseEntity.ok(new ServicioConDetallesDTO(servicio.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio");
		}
		
	}
	
	@Operation(summary="Recibe un JSON de servicio y lo inserta a la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@PostMapping("/{mesaid}/servicios")
	public ResponseEntity<?> insertNewServicio(
			@PathVariable("mesaid") Integer mesaid) {
		
		// Creamos un nuevo servicio vacío
		Servicio servicio = new Servicio();
		
		// Insertamos el objeto mesa segun id proporcionado
		Optional<Mesa> mesa = mesaService.findById(mesaid);
		if (!mesa.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la mesa deseada");
		servicio.setMesa(mesa.get());
		
		// Cargamos fecha y hora actual y se lo guardamos al objeto
		servicio.setFechacomienzo(new BigInteger(String.valueOf(new Date().getTime())));
		
		// Guardamos en BBDD
		Servicio ok = servicioService.save(servicio);
		if (ok != null)
			return ResponseEntity.ok("Se ha creado correctamente un nuevo servicio");
		
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo crear el servicio");
	}
	
	@Operation(summary="Recibe un JSON de servicio y lo actualiza en la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	
	@PutMapping("/{mesaid}/servicios/{servicioid}")
	public ResponseEntity<?> updateServicio(
			@PathVariable("mesaid") Integer mesaid,
			@PathVariable("servicioid") Integer servicioid,
			@RequestBody ServicioSinDetallesDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha proporcionado un objeto servicio a actualizar");
		
		if (dto.getIdservicio() == null)
			return ResponseEntity.badRequest().body("El cuerpo del servicio no especifica un identificador");
		
		if (dto.getIdservicio() != servicioid)
			return ResponseEntity.badRequest().body("Los identificadores de servicio en la url no coinciden con el cuerpo");
		
		Optional<Mesa> mesa = mesaService.findById(mesaid);
		if (!mesa.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la mesa");
		
		Optional<Servicio> servicio = servicioService.findById(servicioid);
		if (!servicio.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio que se quiere modificar");
		
		// Actualizamos contenidos de servicio con contenidos del dto proporcionado del usuario
		try {
			servicio.get().setFechacomienzo(new BigInteger(String.valueOf(DateUtil.stringDateToMillis(DateFormat.DD_MM_YYYY_HH_MM, dto.getFechacomienzo()))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			servicio.get().setFechafin(new BigInteger(String.valueOf(DateUtil.stringDateToMillis(DateFormat.DD_MM_YYYY_HH_MM, dto.getFechafin()))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		servicio.get().setPagada(dto.getPagada());
		servicio.get().setReservada(dto.getReservada());
		
		Servicio ok = servicioService.save(servicio.get());
		if (ok != null)
			return ResponseEntity.ok("Se ha actualizado el servicio correctamente");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se modificó el servicio. Ocurrió un problema");
		
		
	}
	
	@Operation(summary="Elimina el objeto servicio con identificador proporcionado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
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
	
	@Operation(summary="Devuelve los detallefactura de un servicio")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido")})
	@GetMapping("/{mesaid}/servicios/{servicioid}/detallesfactura")
	public ResponseEntity<?> getDetallesFacturaDeServicio(
			@PathVariable("mesaid") Integer mesaId, 
			@PathVariable("servicioid") Integer servicioId) {
		
		List<DetalleFacturaDTO> detalles = new ArrayList<>();
		detalleService.findByIdServicio(servicioId).forEach(s -> detalles.add(new DetalleFacturaDTO(s)));
		return ResponseEntity.ok(detalles);
		
	}
	
	@Operation(summary="Devuelve el objeto detallefactura con id proporcionado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")})
	@GetMapping("/{mesaid}/servicios/{servicioid}/detallesfactura/{detalleid}")
	public ResponseEntity<?> selectDetalleById(
			@PathVariable("mesaid") Integer mesaid,
			@PathVariable("servicioid") Integer servicioid,
			@PathVariable("detalleid") Integer detalleid) {
		
		Optional<Detallefactura> df = detalleService.findById(detalleid);
		if (!df.isPresent())
			return ResponseEntity.notFound().build();
		
		if (df.get().getServicio().getIdservicio() != servicioid)
			return ResponseEntity.badRequest().body("El detalle solicitado no pertenece al servicio indicado");
		
		if (df.get().getServicio().getMesa().getNummesa() != mesaid)
			return ResponseEntity.badRequest().body("El detalle solicitado no pertenece a la mesa indicada");
		
		return ResponseEntity.ok(new DetalleFacturaDTO(df.get()));
		
	}
	
	@Operation(summary="Recibe un JSON de detallefactura y lo inserta a la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@PostMapping("/{mesaid}/servicios/{servicioid}/detallesfactura")
	public ResponseEntity<?> insertNewDetalleFactura(
			@PathVariable("mesaid") Integer mesaid,
			@PathVariable("servicioid") Integer servicioid,
			@RequestBody DetalleFacturaDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha proporcionado un objeto detallefactura a añadir");
		
		Optional<Servicio> servicio = servicioService.findById(servicioid);
		if (!servicio.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio indicado");
		
		Optional<Plato> plato = platoService.findById(dto.getPlato().getIdplato());
		if (!plato.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el plato indicado");
			
		
		Detallefactura df = new Detallefactura();
		df.setCantidad(dto.getCantidad());
		df.setPlato(plato.get());
		df.setPreciounidad(plato.get().getPreciounidad());
		df.setServicio(servicio.get());
		
		Detallefactura ok = detalleService.save(df);
		if (ok != null)
			return ResponseEntity.ok("Detalle factura añadido correctamente");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo añadir el detalle factura");
	}
	
	@Operation(summary="Recibe un JSON de detallefactura y lo actualiza a la base de datos")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Solicitud mal formada o erronea"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado"),
            @ApiResponse(code = 304, message = "No se ha modificado la base de datos")})
	@PutMapping("/{mesaid}/servicios/{servicioid}/detallesfactura/{detalleid}")
	public ResponseEntity<?> updateDetalleFactura(
			@PathVariable("mesaid") Integer mesaid,
			@PathVariable("servicioid") Integer servicioid,
			@PathVariable("detalleid") Integer detalleid,
			@RequestBody DetalleFacturaDTO dto) {
		
		if (dto == null)
			return ResponseEntity.badRequest().body("No se ha proporcionado un objeto detallefactura a añadir");
		
		Optional<Detallefactura> detalle = detalleService.findById(detalleid);
		if (!detalle.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el detalle factura indicado");
		
		Optional<Servicio> servicio = servicioService.findById(servicioid);
		if (!servicio.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio indicado");
		
		Optional<Plato> plato = platoService.findById(dto.getPlato().getIdplato());
		if (!plato.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el plato indicado");
				
		Detallefactura df = detalle.get();
		
		// Solo se permite cambiar la cantidad del producto seleccionado
		df.setCantidad(dto.getCantidad());
		
		if (detalleService.save(df) != null)
			return ResponseEntity.ok("Se ha actualizado el detalle pedido");
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se pudo actualizar el detalle factura");
	}
	
	@Operation(summary="Elimina el detallefactura con id proporcionado")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "No encontrado")})
	@DeleteMapping("/{mesaid}/servicios/{servicioid}/detallesfactura/{detalleid}")
	public ResponseEntity<?> deleteDetalleFactura(
			@PathVariable("mesaid") Integer mesaid,
			@PathVariable("servicioid") Integer servicioid,
			@PathVariable("detalleid") Integer detalleid) {
		
		Optional<Detallefactura> detalle = detalleService.findById(detalleid);
		if (!detalle.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el detalle factura indicado");
		
		Optional<Servicio> servicio = servicioService.findById(servicioid);
		if (!servicio.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el servicio indicado");
		
		detalleService.deleteById(detalle.get());
		return ResponseEntity.ok("Detalle factura eliminado");
		
	}
	
}
