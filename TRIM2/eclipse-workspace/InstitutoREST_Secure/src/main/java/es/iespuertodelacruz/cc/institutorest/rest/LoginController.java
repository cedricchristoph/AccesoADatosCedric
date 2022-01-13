package es.iespuertodelacruz.cc.institutorest.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.cc.institutorest.entity.User;
import es.iespuertodelacruz.cc.institutorest.security.GestorDeJWT;
import es.iespuertodelacruz.cc.institutorest.service.UserService;

@RestController
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/* acepta request del tipo: form urlencode */
	@PostMapping("/api/login")
	public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
		String token = getJWTToken(username, pwd);
		//si token nulo es que usuario/pass no es válido
		if (token != null) {
			return ResponseEntity.ok(token);
		} else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("usuario/passerróneos");
	}

	@Autowired
	UserService usuarioService;

	private String getJWTToken(String username, String passTextoPlanoRecibida) {
		String respuesta = null;
		GestorDeJWT gestorDeJWT = GestorDeJWT.getInstance();
		User usuario = usuarioService.findById(username).get();
		String passwordUsuarioEnHash = usuario.getHashPwd();
		boolean autenticado = BCrypt.checkpw(passTextoPlanoRecibida, passwordUsuarioEnHash);
		if (autenticado) {
			logger.info("los roles obtenidos: " + usuario.getAccessLevel());
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(usuario.getAccessLevelStr());
			List<String> roles = grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
			int duracionMinutos = 600;
			String token = gestorDeJWT.generarToken(username, roles, duracionMinutos);
			respuesta = gestorDeJWT.BEARERPREFIX + token;
		}
		return respuesta;
	}
}