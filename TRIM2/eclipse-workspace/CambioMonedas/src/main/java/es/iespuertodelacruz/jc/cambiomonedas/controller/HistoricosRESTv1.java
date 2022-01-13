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

@RequestMapping("/api/v1/historicos")
public class HistoricosRESTv1 {

	private static final Logger logger = LoggerFactory.getLogger(HistoricosRESTv1.class);
	
	@Autowired
	private MonedasService monedasService;
	
	@Autowired
	private HistoricocambioeuroService historicocambioeuroService;

	@GetMapping
	public List<HistoricocambioeuroDTO> getAll(){

		List l = new ArrayList<Historicocambioeuro>();
		for(Historicocambioeuro m: historicocambioeuroService.findAll()  ) {
			l.add(new HistoricocambioeuroDTO(m));
		}
		
		return 	l;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMonedaById(@PathVariable("id") Integer id) {
		
		Optional<Historicocambioeuro> optM = historicocambioeuroService.findById(id);
		//List l = new ArrayList<MonedasDTO>();
		//l.add(new MonedasDTO(optMonedas.get() ));
		//return l;
		if( optM.isPresent()) {

			return ResponseEntity.ok().body(optM.get());
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el histórico no existe");
		}		
		
	}
	

	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody HistoricocambioeuroDTO mDTO){
		Historicocambioeuro m = mDTO.toHistorico();
		historicocambioeuroService.save(m);
		
		return ResponseEntity.ok().body(new HistoricocambioeuroDTO(m));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Historicocambioeuro> optM = historicocambioeuroService.findById(id);
		if(optM.isPresent()) {
			historicocambioeuroService.deleteById(id);
			return ResponseEntity.ok("histórico borrada");
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id histórico del registro no existe");
		}

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody HistoricocambioeuroDTO mDTO){
		Optional<Historicocambioeuro> optM = historicocambioeuroService.findById(id);
		if(optM.isPresent()) {
			Historicocambioeuro m = optM.get();
			m.setFecha(mDTO.getFecha());
			m.setEquivalenteeuro(mDTO.getEquivalenteeuro());
			if( mDTO.getFkidmoneda() > 0) {
				Optional<Monedas> omoneda = monedasService.findById(mDTO.getFkidmoneda());
				if( omoneda.isPresent()) {
					m.setFkidmoneda(omoneda.get());
				}
			}
			
			return ResponseEntity.ok(historicocambioeuroService.save(m));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id histórico del registro no existe");
		}

	}

	
}
