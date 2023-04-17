package com.matiasjimenez.microservicios.app.personajes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasjimenez.microservicios.app.personajes.models.repository.PersonajeRepository;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

@Service
public class PersonajeServiceImpl implements PersonajeService {

	@Autowired
	private PersonajeRepository repositorio;

	@Override
	public List<Personaje> listar() {
		return repositorio.findAll();
	}

	@Override
	public Optional<Personaje> buscarPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	public Personaje guardar(Personaje personaje) {
		return repositorio.save(personaje);
	}

	@Override
	public void eliminarPorId(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public List<Personaje> guardarLista(List<Personaje> personajes) {
		return repositorio.saveAll(personajes);
	}

}
