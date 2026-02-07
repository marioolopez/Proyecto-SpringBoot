package com.udemy.ejercicio1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MiAppSpring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Cargar configuración de spring desde un archivo xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		
		
		//INYECCION DE DEPENDENCIA POR CONSTRUCTOR
		//Hemos cargado nuestro objeto Piano desde el applicationContext.xml
		instrumentoMusical i = context.getBean("miInstrumento", instrumentoMusical.class);
		
		//Imprimimos los resultado (actualmente esta en guitarra)
		System.out.println(i.obtenerSonido());
		System.out.println(i.obtenerAfinacion());
		
		
		
		
		//INYECCION DE DEPENDENCIA POR METODO SETTERS
		//Violin
		Violin violin = context.getBean("miViolin", Violin.class);
		System.out.println(violin.obtenerSonido());
		System.out.println(violin.obtenerAfinacion());
		
		
		
		
		//Para cerrar el conexto (osea el objeto 'ClassPathXmlApplicationContext' hacemos esto...)
		context.close();
		
	}
}
