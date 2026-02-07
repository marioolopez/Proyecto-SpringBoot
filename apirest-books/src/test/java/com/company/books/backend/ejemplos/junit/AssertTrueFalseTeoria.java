package com.company.books.backend.ejemplos.junit;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AssertTrueFalseTeoria {
	
	@Test
	public void test1() {
		assertTrue(true); //pasa el test si la condición es true, falla si es false
		assertFalse(false); //pasa el test si la condición es false, falla si es true
	}
	
	@Test
	public void test2() {
		boolean expresion = 4 == 4;
		boolean expresion2 = 4 != 4;
		assertTrue(expresion);
		assertFalse(expresion2); //--> esto esta mal porque espera un "valor verdadero"
	}
	

}
