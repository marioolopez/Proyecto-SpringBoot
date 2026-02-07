package com.company.books.backend.service;
import org.springframework.http.ResponseEntity;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
public interface ICategoriaService { //INTERFAZ QUE CREA METODOS, Y QUE LOS CONSUME LA CLASE "CategoriaServiceImpl"

	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);
	public ResponseEntity<CategoriaResponseRest> eliminarPorId(Long id);

}
