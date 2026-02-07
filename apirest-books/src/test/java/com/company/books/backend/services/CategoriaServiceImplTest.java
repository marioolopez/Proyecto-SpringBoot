package com.company.books.backend.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.company.books.backend.dao.ICategoriaDao;
import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.CategoriaServiceImpl;

public class CategoriaServiceImplTest {
	
	@InjectMocks //mete los objetos falsos en la clase que vas a probar
	CategoriaServiceImpl service;
	
	@Mock //usamos mockito para crear una version FALSA	del DAO de esta clase. De esta forma no vamos a la BBDD
	ICategoriaDao categoriaDao; //OBJETO FALSO que no contiene nada	
	
	//definimos un arraylist para guardar las categorias
	List<Categoria> list = new ArrayList<>();


	@BeforeEach //preparamos lo que vamos a utilizar. En este caso inicializo el Mocks de la clase
	public void init(){
		MockitoAnnotations.openMocks(this);//enciende todo para que los mocks funcionen
		this.cargarCategorias(); //cargamos las categorias con elementos fijos
	}
	 
	
	@Test //llama al metodo findAll() que devuelve todas las categorias en una lista
	public void buscarCategoriasTest(){
		when(categoriaDao.findAll()).thenReturn(list);
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();//REALIZA LA OPERACIÓN
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size()); //COMPRUEBA QUE LA prueba unitaria ES EXITOSA
		verify(categoriaDao, times(1)).findAll();//verificar que se haya llamado a findAll();
	}
	
	
	//metodo donde creas las categorias
	public void cargarCategorias(){
		Categoria catUno = new Categoria(Long.valueOf(1),"Abarrotes", "distintos abarrotes"); //1
		Categoria catDos = new Categoria(Long.valueOf(1),"Lacteos", "variedad de abarrotes"); //2
		Categoria catTres = new Categoria(Long.valueOf(1),"Bebidas", "bebidas sin azucar"); //3
		Categoria catCuatro = new Categoria(Long.valueOf(1),"carnes blancas", "distintas carnes"); //4
		list.add(catUno); 
		list.add(catDos); 
		list.add(catTres); 
		list.add(catCuatro); 
	}
	
}