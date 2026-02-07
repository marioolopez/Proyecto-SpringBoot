package com.company.books.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.Libro;
import com.company.books.backend.response.LibroResponseRest;
import com.company.books.backend.service.ILibroService;

//ESTA CLASE GESTIONA LAS RUTAS (ES como el SERVICE DE ANGULAR) //Pones esta ruta - "Y LA LLEVAS AL SERVICE DONDE HACES LAS CONSULTAS A LA BBDD"
@RestController
@RequestMapping("/v1") //Genero la primera ruta de ingreso (para POSTMAN)
public class LibroRestController {
	
	@Autowired
	private ILibroService service;
	
	@GetMapping("/libros") //Obtenemos todos los libros con GET
	public ResponseEntity<LibroResponseRest> devuelveLibros(){
		ResponseEntity<LibroResponseRest> response = service.buscarLibros();
		return response;
	}
	
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> obtenerLibroPorId(@PathVariable Long id){
		ResponseEntity<LibroResponseRest> response = service.obtenerLibroPorId(id);
		return response;
	}
	
	
	@PostMapping("/libros")
	public ResponseEntity<LibroResponseRest> crearLibro(@RequestBody Libro request){
		ResponseEntity<LibroResponseRest> response = service.crearLibro(request);
		return response;
	}
	
	
	@PutMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> actualizarLibro(@RequestBody Libro request, @PathVariable Long id){
		ResponseEntity<LibroResponseRest> response = service.actualizarLibro(request, id);
		return response;
	}
	
	
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> eliminarLibro(@PathVariable Long id){
		ResponseEntity<LibroResponseRest> response = service.eliminarLibro(id);
		return response;
	}
	
	
	
}
