package com.matiasjimenez.microservicios.app.personajes.controller;

//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matiasjimenez.microservicios.app.personajes.services.PersonajeService;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

@RestController
public class PersonajeController {
	
	@Autowired
	private PersonajeService service;
	
	@GetMapping("/")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	/* Por cada Opcion creo un Personaje y lo guardo
	@PostMapping("/parsear")
	public ResponseEntity<?> listarOpciones(){
		List<Opcion> listaOpcion = service.listarOpciones();
		List<Personaje> listaPersonaje = listaOpcion.stream().map(o->{
			return new Personaje(o.getTexto());
		}).toList();
		return ResponseEntity.ok(service.guardarLista(listaPersonaje));
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Optional<Personaje> p = service.buscarPorId(id);
		if(!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(p.get());
	}
	
	@PostMapping("/")
	public ResponseEntity<?> guardar(@RequestBody Personaje personaje){
		return ResponseEntity.ok(service.guardar(personaje));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrar(@PathVariable Long id){
		Optional<Personaje> p = service.buscarPorId(id);
		if(!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		service.eliminarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
