package es.iespuertodelacruz.cc.institutorest.rest.v2;

import java.util.ArrayList;
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

import es.iespuertodelacruz.cc.institutorest.dto.PlainPasswordUserDTO;
import es.iespuertodelacruz.cc.institutorest.dto.UserDTO;
import es.iespuertodelacruz.cc.institutorest.entity.User;
import es.iespuertodelacruz.cc.institutorest.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/users")
public class UserRESTv2 {

	private Logger log = LoggerFactory.getLogger(UserRESTv2.class);
	
	
	@Autowired
	private UserService service;
	
	@Operation(summary = "Devuelve todos los usuarios registrados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK. Se devuelve la lista")
	})
	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		service.findAll().forEach(result -> users.add(new UserDTO(result)));
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUser(@PathVariable("username") String username) {
		Optional<User> optional = service.findById(username);
		if (!optional.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(new UserDTO(optional.get()));
	}
	
}
