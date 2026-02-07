package com.company.books.backend.ejemplos.junit;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertEqualsTeoria { // Clase que evalua que dos valores sean iguales

	@Test
	public void miTest() {
		assertEquals(1, 1); //bien
		//assertEquals(1, 2); //mal	
	}
	
}
