package com.company.books.backend.config;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;
import com.company.books.backend.filter.JwtReqFilter; 

@Configuration
public class ConfigSecurity { //CLASE CONFIGURACION CENTRAL DE SEGURIDAD - CONTROLA LA SEGURIDA DE LA API
	
	@Autowired
	@Lazy //evitar tener dependencias circulares
	private JwtReqFilter jwtReqFilter;
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) { //le dice a Spring que los usuarios NO estan en memoria, sino en MySQL
		return new JdbcUserDetailsManager(datasource);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //DICES QUE ROL TENDRAN ACCESO A CIERTAS URLS DEL CONTROLADOR. EJ: LOS EMPLEADOS TENDRAN ACCESO A LA RUTA GET obtenerLibros();
		
		http.authorizeHttpRequests( configure -> {
			configure
				.requestMatchers(HttpMethod.GET, "/v1/libros").hasRole("Empleado") //para esta ruta
				.requestMatchers(HttpMethod.GET, "/v1/libros/**").hasRole("Empleado") //para los IDs 
				.requestMatchers(HttpMethod.POST, "/v1/libros").hasRole("Jefe") //solo el jefe podrá crear 
				.requestMatchers(HttpMethod.PUT, "/v1/libros/**").hasRole("Jefe") //solo el jefe podrá modificar
				.requestMatchers(HttpMethod.DELETE, "/v1/libros/**").hasRole("Jefe") //solo el jefe podra borrar
			
				.requestMatchers(HttpMethod.GET, "/v1/categorias").hasRole("Empleado") //para esta ruta
				.requestMatchers(HttpMethod.GET, "/v1/categorias/**").hasRole("Empleado") //para los IDs 
				.requestMatchers(HttpMethod.POST, "/v1/categorias").hasRole("Jefe") //solo el jefe podrá crear 
				.requestMatchers(HttpMethod.PUT, "/v1/categorias/**").hasRole("Jefe") //solo el jefe podrá modificar
				.requestMatchers(HttpMethod.DELETE, "/v1/categorias/**").hasRole("Jefe") //solo el jefe podra borrar
			
				.requestMatchers("/v1/categorias", "/v1/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll(); //cualquiera puede pedir TOKEN
			
			//RUTA -->  /v1/authenticate --> para enviar usuario y contraseña (POST)
			//RUTA -->  /v3/api-docs/** --> devuelve un JSON enorme
			//RUTA -->  /swagger-ui/** --> interfaz Swagger
			//RUTA -->  /swagger-ui.html --> acceso antiguo
			
		})
		.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class) //estamos diciendo que utilice autorizacion por token.. de tal forma que las demas autorizaciones no son validas
		.sessionManagement( (session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		);
		
		http.httpBasic(org.springframework.security.config.Customizer.withDefaults()); //activa login básico lo que te obliga a poner quien eres...
		http.csrf( csrf -> csrf.disable()); //quita proteccion CSRF
		return http.build(); //construimos el objeto que estamos configurando
	}
	
	
	
	 @Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { //sin esto el login y JWT no funcionan
		 return authenticationConfiguration.getAuthenticationManager();
	 }
	
	
	/*@Bean //DOCUMENTACION PARA GUARDAR USUARIOS EN MEMORIA Y DARLE PERMISOS EN FUNCION DEL ROL PARA ACCEDER A LA INFORMACION MEDIANTE RUTAS EN POSTMAN
	public InMemoryUserDetailsManager userDetailsManager() { //METODO PARA AGREGAR USUARIOS (con sus contraseñas), Y DARLE PERMISOS PARA PROBAR LA API REST EN POSTMAN
		UserDetails mario = User.builder()
				.username("mario")
				.password("{noop}mario1234")
				.roles("Empleado")
				.build();
		
		UserDetails alfredo = User.builder()
				.username("alfredo")
				.password("{noop}alfredo1234")
				.roles("Empleado", "Jefe")
				.build();
		
		UserDetails alejandro = User.builder()
				.username("alejandro")
				.password("{noop}alejandro1234")
				.roles("Empleado", "Jefe")
				.build();
		
		return new InMemoryUserDetailsManager(mario, alfredo, alejandro);
	}*/
	
}