package com.company.books.backend.service;
import org.springframework.http.ResponseEntity;
import com.company.books.backend.model.Libro;
import com.company.books.backend.response.LibroResponseRest;

public interface ILibroService {

	public ResponseEntity<LibroResponseRest> buscarLibros();
	public ResponseEntity<LibroResponseRest> obtenerLibroPorId(Long id);
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro);
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id);
	public ResponseEntity<LibroResponseRest> eliminarLibro(Long id);
}
