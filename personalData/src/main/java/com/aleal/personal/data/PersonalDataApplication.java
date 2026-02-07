package com.aleal.personal.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.aleal.personal.data.controller") })
@EnableJpaRepositories("com.aleal.personal.data.repository")
@EntityScan("com.aleal.personal.data.model")
public class PersonalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalDataApplication.class, args);
	}

}
