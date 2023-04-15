package com.matiasjimenez.microservicios.app.personajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.matiasjimenez.microservicios.commons.personajes.models.entity"})
public class MsvcPersonajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcPersonajesApplication.class, args);
		
		System.out.println("Hola mundo");
	}

}
