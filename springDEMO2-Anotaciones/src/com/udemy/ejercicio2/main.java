package com.udemy.ejercicio2;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext.xml");

		InstrumentoMusical i = contexto.getBean("miGuitarra", InstrumentoMusical.class);
		
		System.out.println(i.obtenerSonido());
		
		contexto.close();
	}

}
