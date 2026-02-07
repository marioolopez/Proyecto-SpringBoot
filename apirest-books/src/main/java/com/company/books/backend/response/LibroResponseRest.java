package com.company.books.backend.response;

public class LibroResponseRest extends ResponseRest{  //sirve para enviar la respuesta completa del servicio (El estado de la respuesta y Los datos de los libros)
	
	private LibroResponse libroresponse = new LibroResponse();

	public LibroResponse getLibroresponse() {
		return libroresponse;
	}

	public void setLibroresponse(LibroResponse libroresponse) {
		this.libroresponse = libroresponse;
	}
	
}
