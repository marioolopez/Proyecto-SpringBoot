package com.company.books.backend.ejemplos.junit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class CalculadoraTest {
	
	
	@BeforeAll //SE EJECUTA ESTE EL PRIMERO DE TODOS Y UNA SOLA VEZ
	public static void primero() {
		System.out.println("primero");
	}
	
	
	@AfterAll //SE EJECUTA EL ULTIMO DE TODOS Y UNA SOLA VEZ 
	public static void segundo() {
		System.out.println("ultimo");
	}
	
	
	@BeforeEach //SE EJECUTA ANTES DE CADA TEST. SE ENCARGA DE DEJAR TODO LISTO ANTES DE USARLO (CREAR OBJETO, INICIALIZAR ALGO...)
	public void instanciaObjeto() {
		Calculadora calc = new Calculadora();
		System.out.println("@BeforeEach");
	}
	
	
	@AfterEach //SE EJECUTA DESPUES DE CADA TEST Y SIRVE PARA LIMPIAR, CERRAR COSAS...
	public void despuesTest() {
		System.out.println("@AfterEach");
	}
	
	
	
	@Test //SE EJECUTA LA PARTE LOGICA
	@DisplayName("prueba que ocupa assertEqual")
	@Disabled("esta prueba no se ejecutará")
	public void calculadoraAssertEqualTest() {
		Calculadora calc = new Calculadora();
		
		assertEquals(3, calc.sumar(2, 1));
		assertEquals(3, calc.restar(4, 1));
		assertEquals(3, calc.multiplicar(3, 1));
		assertEquals(3, calc.dividir(9, 3));
		
		System.out.println("calculadoraAssertEqualTest");
	}
	
	
	@Test //SE EJECUTA LA PARTE LOGICA
	public void calculadoraTrueFalse() { 
		Calculadora calc = new Calculadora();
		
		assertTrue(calc.sumar(1, 1) == 2); //esta bien porque espera un true
		assertTrue(calc.restar(4,1) == 3); //esta bien porque espera un true
	
		assertFalse(calc.multiplicar(3,3) == 8); //esta bien porque espera un false
		assertFalse(calc.dividir(4,2) == 1); //esta bien porque espera un false
			
		System.out.println("calculadoraTrueFalse");
	}
	
	
	
	//beforeEach --> prepara
	//test --> prueba
	//aftereach --> limpia
	
	
	
}
