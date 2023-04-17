package com.matiasjimenez.microservicios.app.personajes.models.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}
