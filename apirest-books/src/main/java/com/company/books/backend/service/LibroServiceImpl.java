package com.company.books.backend.service;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.books.backend.dao.ILibroDao;
import com.company.books.backend.model.Libro;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.response.LibroResponseRest;

@Service
public class LibroServiceImpl implements ILibroService{

	private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class); //para meter mensaje
	
	@Autowired
	private ILibroDao libroDao;	
	
	//OBTENEMOS TODOS LOS LIBROS
	@Override
	@Transactional(readOnly = true) //“solo voy a leer de la BD, no voy a modificar nada”
	public ResponseEntity<LibroResponseRest> buscarLibros() { //sacamos libros de la bbdd (GET)
		LibroResponseRest response = new LibroResponseRest(); //para almacenar respuesta y estado
		try {
			List <Libro> libro = (List<Libro>) libroDao.findAll(); //devuelve todos los libros de la bbdd en una LISTA... "Al ser VARIOS libros tenemos que tratarlo como LISTA"
			response.getLibroresponse().setLibro(libro); //se meten los libros dentro de la respuesta MEDIANTE "SET"
			response.setMetada("Respuesta ok", "200", "Respuesta exitosa"); //son mensajitos por pantalla
		}catch(Exception e) {
			response.setMetada("Respuesta nok", "-1", "Respuesta incorrecta");
			log.error("error al consultar libros", e);
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //devuelve 
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200 y el estado de la respuesta
	}

	
	
	
	//OBTENEMOS LIBRO POR ID
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> obtenerLibroPorId(Long id) {
		
		LibroResponseRest response = new LibroResponseRest(); //para almacenar respuesta y estado
		List<Libro> list = new  ArrayList<>();
		try {

			Optional<Libro> libro =  libroDao.findById(id); //el OPTIONAL es para obtener un solo libro el cual quieres buscar por ID
			
			if(libro.isPresent()) { //si el libro existe
				list.add(libro.get());
				response.getLibroresponse().setLibro(list);
			} else {
				log.error("error en consultar libro");
				response.setMetada("Respuesta NOK", "-1", "Libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("error en consultar libro");
			response.setMetada("Respuesta NOK", "-1", "Libro no encontrado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
		
	}


	
	//METODO QUE CREA UN LIBRO
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro) {

		LibroResponseRest response = new LibroResponseRest(); //para almacenar respuesta y estado
		List<Libro> list = new ArrayList<>();
		
		try{
			
			Libro libroGuardado = libroDao.save(libro);
			
			if(libroGuardado != null) { // si no esta vacio...
				list.add(libroGuardado);
				response.getLibroresponse().setLibro(list);
			} else {
				log.error("error al crear el libro");
				response.setMetada("Respuesta NOK", "-1", "Libro no guardado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("error en crear libro");
			response.setMetada("Respuesta NOK", "-1", "Libro no creado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta OK", "00", "Libro creado");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}



	
	
	//METODO QUE MODIFICA UN LIBRO POR ID
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id) {

		LibroResponseRest response = new LibroResponseRest(); //para almacenar respuesta y estado
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Optional<Libro> libroBuscado = libroDao.findById(id); //cogemos el id del libro que vamos a modificar
			if(libroBuscado.isPresent()) { //si existe el ID...
				
				libroBuscado.get().setNombre(libro.getNombre());
				libroBuscado.get().setDescripcion(libro.getDescripcion());
				libroBuscado.get().setCategoria(libro.getCategoria());
				
				Libro libroActualizar = libroDao.save(libroBuscado.get()); //guardo el libro buscado
				
				if(libroActualizar != null){ //si no esta vacio...
					response.setMetada("Respuesta OK", "00", "Libro Actualizado!");
					list.add(libroActualizar); //se agrega a la lista
					response.getLibroresponse().setLibro(list); //agregamos al objeto de respuesta el valor
				}else { //si esta vacío
					log.error("error en actualizar el libro");
					response.setMetada("Respuesta NOK", "-1", "libro no actualizado");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
			}else { //si no existe
				log.error("error en actualizar el libro");
				response.setMetada("Respuesta NOK", "-1", "Libro no actualizado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("error al modificar libro");
			response.setMetada("Respuesta NOK", "-1", "libro no modificado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}




	
	
	//METODO QUE ELIMINA UN LIBRO POR ID
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> eliminarLibro(Long id) {
		
		LibroResponseRest response = new LibroResponseRest(); //para almacenar respuesta y estado
		
		try {
			libroDao.deleteById(id);
			response.setMetada("Respuesta OK", "00", "Libro eliminado!");
			
		}catch(Exception e) {
			log.error("error en eliminar libro");
			response.setMetada("Respuesta NOK", "-1", "libro no eliminada");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); //devuelve 200
	}
	
	

}
