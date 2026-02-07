package com.company.books.backend.response;
import java.util.*;
import com.company.books.backend.model.Categoria;
public class CategoriaResponse { //creamos esta clase por si tenemos que almacenar informacion que venga de la BBDD

	private List<Categoria> categoria;
	
	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	
}
