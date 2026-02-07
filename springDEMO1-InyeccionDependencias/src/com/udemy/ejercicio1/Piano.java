package com.udemy.ejercicio1;

public class Piano implements instrumentoMusical {

	private Afinar afinar;
	
	public Piano(Afinar afinar){
		this.afinar = afinar;
	}
	
	@Override //para indicar que el metodo esta implementado en la clase instrumentoMusical
	public String obtenerSonido() {
		return "Obteniendo sonido del piano";
	}

	@Override
	public String obtenerAfinacion() {
		// TODO Auto-generated method stub
		return "Piano - "+afinar.afinacion();
	}

}

