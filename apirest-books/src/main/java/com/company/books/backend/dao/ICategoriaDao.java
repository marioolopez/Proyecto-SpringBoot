package com.company.books.backend.dao; //ESTA CLASE ESTA RELACIONADA CON EL MODELO CATEGORIA (QUE ES LA CLASE)
import org.springframework.data.repository.CrudRepository;
import com.company.books.backend.model.Categoria;
public interface ICategoriaDao extends CrudRepository<Categoria, Long>{ //Esta clase permite hacer operaciones sobre la Base De Datos (CRUD)
	
}
