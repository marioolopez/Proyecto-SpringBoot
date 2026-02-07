package com.udemy.ejercico2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Guitarra implements InstrumentoMusical {
	
	@Autowired  //Con esto estamos inyectando esta dependencia a la clase 'Guitarra'
	private Afinar afinar;
	
	@Override
	public String obtenerSonido() {
		return "obtener sonido de la guitarra";
	}

	@Override
	public String obtenerAfinacion() {
		return "Guitarra - "+afinar.afinar();
	}	

}
