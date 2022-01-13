package es.iespuertodelacruz.jc.cambiomonedas.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jc.cambiomonedas.dto.HistoricocambioeuroDTO;
import es.iespuertodelacruz.jc.cambiomonedas.dto.MonedasDTO;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Historicocambioeuro;
import es.iespuertodelacruz.jc.cambiomonedas.entity.Monedas;
import es.iespuertodelacruz.jc.cambiomonedas.service.HistoricocambioeuroService;
import es.iespuertodelacruz.jc.cambiomonedas.service.MonedasService;


@RestController

@RequestMapping("/api/v3/monedas")
public class MonedasRESTv3 {

	private static final Logger logger = LoggerFactory.getLogger(MonedasRESTv3.class);
	
	@Autowired
	private MonedasService monedasService;
	
	@Autowired
	private HistoricocambioeuroService historicocambioeuroService;

	@GetMapping
	public List<MonedasDTO> getAll(){

		List l = new ArrayList<Monedas>();
		for(Monedas m: monedasService.findAll()) {
			l.add(m);
		}
		
		return 	l;
	}
	
	@GetMapping("/{id}")
	public Monedas getMonedaById(@PathVariable("id") Integer id) {
		
		Optional<Monedas> optMonedas = monedasService.findById(id);
		//List l = new ArrayList<MonedasDTO>();
		//l.add(new MonedasDTO(optMonedas.get() ));
		//return l;
		return optMonedas.get();
	}
	

	@GetMapping("/{id}/historicos")
	public  ResponseEntity<?> getHistoricosMonedaById(@PathVariable("id") Integer id) {
		
		Optional<Monedas> optMonedas = monedasService.findById(id);
		//List l = new ArrayList<MonedasDTO>();
		//l.add(new MonedasDTO(optMonedas.get() ));
		//return l;
		return  ResponseEntity.ok().body(optMonedas.get().getHistoricocambioeuroCollection());
	}
	
	
	@PostMapping("/{id}/historicos")
	public ResponseEntity<?> save(@PathVariable("id") Integer id, @RequestBody HistoricocambioeuroDTO hDTO){
		Historicocambioeuro h = new Historicocambioeuro();
		h.setEquivalenteeuro(hDTO.getEquivalenteeuro());
		h.setFecha(hDTO.getFecha() );
		Optional<Monedas> optM= monedasService.findById(id);
		if( optM.isPresent()) {
			h.setFkidmoneda(optM.get());
			historicocambioeuroService.save(h);
			return ResponseEntity.ok().body(new HistoricocambioeuroDTO(h));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la moneda referenciada no existe");
		}
	}
	
	
	
	
	@DeleteMapping("/{id}/historicos/{idh}")
	public ResponseEntity<?> deleteHistorico(@PathVariable Integer id,@PathVariable Integer idh){
		Optional<Historicocambioeuro> optM = historicocambioeuroService.findById(idh);
		if(optM.isPresent()) {
			if(optM.get().getFkidmoneda().getIdmoneda() == id ) {
				historicocambioeuroService.deleteById(idh);
				return ResponseEntity.ok("registro de cambio con euro borrado");				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id moneda no coincide");				
			}

			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del histórico no existe +" + idh + " moneda " + id);
		}

	}	
	
	
	@PutMapping("/{id}/historicos/{idh}")
	public ResponseEntity<?> updateHistorico(@PathVariable Integer id, 
			@PathVariable Integer idh, @RequestBody HistoricocambioeuroDTO hDTO){
		Optional<Historicocambioeuro> optH = historicocambioeuroService.findById(idh);
		if(optH.isPresent()) {
			Historicocambioeuro h = optH.get();
			h.setEquivalenteeuro(hDTO.getEquivalenteeuro());
			h.setFecha(hDTO.getFecha() );
			Optional<Monedas> optM= monedasService.findById(id);
			if( optM.isPresent()) {
				h.setFkidmoneda(optM.get());
				
				historicocambioeuroService.save(h);
				return ResponseEntity.ok().body(new HistoricocambioeuroDTO(h));
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la moneda referenciada no tiene ese histórico");
			}			
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del histórico no existe");
		}

	}		
	
	
	
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody MonedasDTO mDTO){
		Monedas m = mDTO.toMoneda();
		monedasService.save(m);
		
		return ResponseEntity.ok().body(new MonedasDTO(m));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Monedas> optM = monedasService.findById(id);
		if(optM.isPresent()) {
			monedasService.deleteById(id);
			return ResponseEntity.ok("moneda borrada");
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MonedasDTO mDTO){
		Optional<Monedas> optM = monedasService.findById(id);
		if(optM.isPresent()) {
			Monedas m = optM.get();
			m.setNombre(mDTO.getNombre());
			m.setPais(mDTO.getPais());
			return ResponseEntity.ok(monedasService.save(m));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}

	}

	
}
