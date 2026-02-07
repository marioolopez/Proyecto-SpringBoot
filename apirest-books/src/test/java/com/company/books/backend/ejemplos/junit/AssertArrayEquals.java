package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEquals { //para comprar arrays

	@Test
	public void pruebaArregloIguales() {
		String [] arre1 = {"a", "b"};
		String [] arre2 = {"a", "b"};
		String [] arre3 = {"a", "b", "c"};
	
		assertArrayEquals(arre1, arre2); //esta bien porque espera un true
		//assertArrayEquals(arre1, arre3); //esto da un fallo porque los elementos del 1 no son iguales a los del 3
		//assertArrayEquals(arre2, arre3); //esto da un fallo porque los elementos del 2 no son iguales a los del 3
	}
	
	
	
}
