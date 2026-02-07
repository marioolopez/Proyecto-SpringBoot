package com.company.books.backend.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService { //CLASE QUE CREA, LEE Y VALIDA TOKENS
	
	  private static final String JWT_SECRET_KEY = "bbddbsjfhedfkvjj"; //llave secreta para armar el token
	  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 1; // 1 hora (DURACION DEL TOKEN)

	  public String extractUsername(String token) { //se usa cuando llega una peticion y Spring necesitas saber ¿quien es este?
	    return extractClaim(token, Claims::getSubject);
	  }

	  public Date extractExpiration(String token) { //devuelve la fecha de caducidad del TOKEN
	    return extractClaim(token, Claims::getExpiration);
	  }

	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { //metodo GENERICO para sacar cualquier dato del TOKEN
	    return claimsResolver.apply(extractAllClaims(token));
	  }

	  private Claims extractAllClaims(String token) { //abre el JWT usando la llave secreta y devuelve TODO su contenido (sin esto no puedes leer el TOKEN)
	    return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
	  }

	  private Boolean isTokenExpired(String token) { //mira si murió
	    return extractExpiration(token).before(new Date());
	  }

	  public String generateToken(UserDetails userDetails) { //crea el TOKEN (coge el rol del usuario, lo mete dentro de JWT y llama a "createToken()")
	    Map<String, Object> claims = new HashMap<>();
	    var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
	    claims.put("rol", rol);
	    return createToken(claims, userDetails.getUsername());
	  }

	  private String createToken(Map<String, Object> claims, String subject) { //aqui se construye fisicamente el JWT (mete usuario, rol, fechacreacion, fechaexpiracion y firma secreta)
	    return Jwts
	        .builder()
	        .setClaims(claims)
	        .setSubject(subject)
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
	        .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
	        .compact();
	  }

	  public boolean validateToken(String token, UserDetails userDetails) { //comprueba si es válido
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	  }

}
