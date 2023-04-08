package com.matiasjimenez.microservicios.app.categorias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.matiasjimenez.microservicios.commons.categoria.models.entity"})
public class MsvcCategoriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCategoriasApplication.class, args);
	}

}
