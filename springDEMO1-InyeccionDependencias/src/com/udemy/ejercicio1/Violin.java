package com.udemy.ejercicio1;

public class Violin implements instrumentoMusical {

	private Afinar afinar;
	
	public Violin(){
		super();
	}
	
	//Dependencia por setter
	public void setAfinar(Afinar afinar) { //inyeccion de depends por setters
		this.afinar = afinar;
	}
	
	@Override //Para indicar que el metodo esta implementado en la clase instrumentoMusical
	public String obtenerSonido() {
		// TODO Auto-generated method stub
		return "Obteniendo sonido del violin";
	}

	@Override 
	public String obtenerAfinacion() {
		// TODO Auto-generated method stub
		return "Violin - "+afinar.afinacion();
	}

}
