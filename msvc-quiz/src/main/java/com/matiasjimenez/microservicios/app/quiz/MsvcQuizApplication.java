package com.matiasjimenez.microservicios.app.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({ "com.matiasjimenez.microservicios.commons.categoria.models.entity",
		"com.matiasjimenez.microservicios.app.quiz.models.entity",
		"com.matiasjimenez.microservicios.commons.personajes.models.entity" })
public class MsvcQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcQuizApplication.class, args);
	}

}
