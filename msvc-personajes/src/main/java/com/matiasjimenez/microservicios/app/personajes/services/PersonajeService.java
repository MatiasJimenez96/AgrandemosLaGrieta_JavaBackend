package com.matiasjimenez.microservicios.app.personajes.services;

import java.util.List;
import java.util.Optional;

import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

public interface PersonajeService {
	
	public List<Personaje>  listar();
	
	public Optional<Personaje> buscarPorId(Long id);
	
	public Personaje guardar(Personaje personaje);
	
	public List<Personaje> guardarLista(List<Personaje> personajes);

	public void eliminarPorId(Long id);
	
}
