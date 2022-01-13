package es.iespuertodelacruz.cc.institutorest.rest.v2;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.institutorest.entity.User;
import es.iespuertodelacruz.cc.institutorest.service.UserService;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRESTv2 {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioRESTv2.class);
	

	
	@Autowired
	private UserService usuarioService;
	

	

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMonedaById(@PathVariable("id") String id) {
		
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		

		String nombreAutenticado = ((UserDetails)principal).getUsername();

		Optional<User> optional = usuarioService.findById(id);
		
		if( optional.isPresent()) {
			User u = optional.get();
			//dado que son nombres únicos se pemite tomar información únicamente
			//para sí mismo
			if( u.getUser().equals(nombreAutenticado)) {
				return ResponseEntity.ok(new User(u));
						
			}else {
				return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("usuario solicitado diferente del autenticado");
			}
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody User uDTO){
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		

		String nombreAutenticado = ((UserDetails)principal).getUsername();

		Optional<User> optional = usuarioService.findById(id);
		
		if( optional.isPresent()) {
			User u = optional.get();
			
			if( u.getUser().equals(nombreAutenticado)) {
				if( uDTO.getUser() != null && !uDTO.getUser().isEmpty())
					u.setUser(uDTO.getUser());
				if( uDTO.getHashPwd() != null && !uDTO.getHashPwd().isEmpty()) {
					String hash =  BCrypt.hashpw(uDTO.getHashPwd(), BCrypt.gensalt());
					u.setHashPwd(hash);
				}
	
				usuarioService.save(u);
				return ResponseEntity.ok(new User(u));
				
			}else {
				return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body("usuario solicitado diferente del autenticado");
			}
		}else {
			return ResponseEntity.notFound().build();
		}

	}	
}
