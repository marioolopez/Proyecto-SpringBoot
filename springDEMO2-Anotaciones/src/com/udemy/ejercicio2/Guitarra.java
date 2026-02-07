package com.udemy.ejercicio2;

import org.springframework.stereotype.Component;

@Component("miGuitarra")
public class Guitarra implements InstrumentoMusical {

	@Override
	public String obtenerSonido() {
		return "Obteniendo el sonido de la guitarra";
	}

	
}
