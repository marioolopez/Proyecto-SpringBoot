package com.udemy.ejercico2;
import org.springframework.stereotype.Component;

@Component
public class AfinarService implements Afinar {

	@Override
	public String afinar() {
		return  "afinando el instrumento";
	}

}
