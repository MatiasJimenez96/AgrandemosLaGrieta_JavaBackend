package com.matiasjimenez.microservicios.app.personajes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matiasjimenez.microservicios.commons.personajes.models.entity.Opcion;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
	
	@Query("Select o From Opcion o")
	public List<Opcion> obtenerOpciones();

}
