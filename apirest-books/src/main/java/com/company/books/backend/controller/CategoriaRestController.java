package com.company.books.backend.controller;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.PutMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import com.company.books.backend.model.Categoria;import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;
//ESTA CLASE GESTIONA LAS RUTAS (ES como el SERVICE DE ANGULAR) //Pones esta ruta - "Y LA LLEVAS AL SERVICE DONDE HACES LAS CONSULTAS A LA BBDD"@CrossOrigin(origins = {"*"})@RestController
@RequestMapping("/v1")
public class CategoriaRestController { //Se comunica con la clave se servicio para obtener la informacion de las categorias

	@Autowired
	private ICategoriaService service;	//importamos esta clase que es la interfaz para crear los metodos
    		@GetMapping("/categorias") //GET 
	public ResponseEntity<CategoriaResponseRest> devuelveCategorias() {
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();		return response;
	}
			@GetMapping("/categorias/{id}") //GET	public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable Long id){		ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(id);		return response;	}			@PostMapping("/categorias") //POST	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria request){		ResponseEntity<CategoriaResponseRest> response = service.crear(request);		return response;	}			@PutMapping("/categorias/{id}") //PUT	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@RequestBody Categoria request, @PathVariable Long id){		ResponseEntity<CategoriaResponseRest> response = service.actualizar(request, id);		return response;	}
			@DeleteMapping("/categorias/{id}") //DELETE	public ResponseEntity<CategoriaResponseRest> eliminarPorId(@PathVariable Long id){		ResponseEntity<CategoriaResponseRest> response = service.eliminarPorId(id);		return response;	}	
}
