package com.udemy.ejercicio1;

public class Guitarra implements instrumentoMusical{

	private Afinar afinar;
	
	public Guitarra(Afinar afinar){ //inyecciones por constructor
		this.afinar = afinar;
	}
	
	@Override //para indicar que el metodo esta implementado en la clase instrumentoMusical
	public String obtenerSonido() {
		return "Obteniendo sonido de la guitarra";
	}

	@Override
	public String obtenerAfinacion() {
		// TODO Auto-generated method stub
		return "Guitarra - "+afinar.afinacion();
	}


}
