package com.company.books.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.books.backend.dao.ICategoriaDao;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class); //para meter mensaje
	
	@Autowired //con esto atraes los metodos que implementes de CrudRepository
	private ICategoriaDao categoriaDao;
	
	
	//BUSCAR TODAS LAS CATEGORIAS
	@Override
	@Transactional(readOnly = true)  //se suele poner cuando no vas a modificar, eliminar y crear NADA
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() { //respuesta de la base de datos
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetada("Respuesta ok", "200", "Respuesta exitosa"); //son mensajitos por pantalla
			
		} catch (Exception e) {
			response.setMetada("Respuesta nok", "-1", "Respuesta incorrecta");
			log.error("error al consultar categorias", e);
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //devuelve 200
	}

	
	
	
	
	
	//BUSCAR CATEGORIAS POR ID
	@Override
	@Transactional(readOnly = true) //manejar transaccion (Esta operación va a leer datos de la base de datos, no va a modificar)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria>list = new ArrayList<>(); //para almacenar objetos de tipo categoria
		
		try {
			
			Optional<Categoria> categoria =  categoriaDao.findById(id); //el OPTIONAL es para obtener un solo libro el cual quieres buscar por ID
			
			if(categoria.isPresent()) { //si existe
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			}else {
				log.error("error en consultar categoria");
				response.setMetada("Respuesta NOK", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("error en consultar categoria");
			response.setMetada("Respuesta NOK", "-1", "Categoria no encontrada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //devuelve 200
	}


	
	
	
	
	
	//CREAR CATEGORIAS
	@Override
	@Transactional //manejar transaccion
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria>list = new ArrayList<>();
		
		try {
	
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			if(categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("error en crear categoria");
				response.setMetada("Respuesta NOK", "-1", "Categoria no creada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("error en crear categoria");
			response.setMetada("Respuesta NOK", "-1", "Categoria no creada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //devuelve 200
	}


	
	
	
	
	

	//ACTUALIZAR POR ID
	@Override
	@Transactional //manejar transaccion
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			if(categoriaBuscada.isPresent()) { //si existe
				
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get()); //acrtualizando
			
				if(categoriaActualizar != null) { //si no esta vacia y no hay nada raro...
					response.setMetada("Respuesta OK", "00", "Categoria Actualizada!");
					list.add(categoriaActualizar); //actualizamos
					response.getCategoriaResponse().setCategoria(list);
					
				} else {
					log.error("error en actualizar la categoria");
					response.setMetada("Respuesta NOK", "-1", "Categoria no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else { //si no existe
				log.error("error en actualizar la categoria");
				response.setMetada("Respuesta NOK", "-1", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}

			
		}catch(Exception e) {
			log.error("error en actualizar categoria");
			response.setMetada("Respuesta NOK", "-1", "Categoria no actualizada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //devuelve 200
	}



	
	
	
	
	
	//BORRAR POR ID
	@Override
	public ResponseEntity<CategoriaResponseRest> eliminarPorId(Long id) {
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			//eliminamos registro
			categoriaDao.deleteById(id);
			response.setMetada("Respuesta OK", "00", "Categoria Eliminada!");
			
		}catch(Exception e) {
			log.error("error en eliminar categoria");
			response.setMetada("Respuesta NOK", "-1", "Categoria no eliminada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); //devuelve 200
	}
}




//PREGUNTAR A CHATGPT QUE ES EL DAO osea el categoria DAO