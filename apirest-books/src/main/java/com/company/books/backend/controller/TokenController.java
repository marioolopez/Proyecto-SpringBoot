package com.company.books.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.books.backend.request.AuthRequest;
import com.company.books.backend.response.TokenResponse;
import com.company.books.backend.service.JwtService;

@RestController
@RequestMapping("/v1")
public class TokenController { 

	@Autowired
	private AuthenticationManager authenticationManager; //comprueba si el usuario y contraseña son válidos
	
	@Autowired
	UserDetailsService userDetailsService; //carga la informacion del usuario desde MySQL
	
	@Autowired
	private JwtService jwtService; //crear el token JWT
	
	
	//metodo validar usuario
	@PostMapping("/authenticate") //ruta que recibe credenciales del usuario y valida su identidad (NO ESTA CREANDO NI NADA). a cambio te da un token que es tu informacion codificada
	public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request){
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContrasena()));//valida que este bien
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsuario()); //información del usuario
		final String jwt = jwtService.generateToken(userDetails); //metes la info del usu en JWT
		
		return ResponseEntity.ok(new TokenResponse(jwt)); //te retorna el token cuando metes el usu y la contra
	}
	
}
