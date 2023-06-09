package com.matiasjimenez.microservicios.app.categorias.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matiasjimenez.microservicios.app.categorias.service.CategoriaService;
import com.matiasjimenez.microservicios.commons.categoria.models.entity.Categoria;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaService servicio;

	@GetMapping("/")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(servicio.listar());
	}
	
	@PostMapping("/")
	public ResponseEntity<?> guardar(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardar(categoria));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
		Map<String, Object> error = new HashMap<>();
		Optional<Categoria> o = servicio.buscarPorId(id);
		if (!o.isPresent()) {
			error.put("mensaje", "No se encontro la categoria con id: ".concat(id.toString()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		servicio.eliminarPorId(id);
		error.put("mensaje", "Categoria id:".concat(id.toString()).concat(" eliminada!"));
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}

	@PutMapping("/editar")
	public ResponseEntity<?>editar(@RequestBody Categoria categoria){
		Map<String, Object> error = new HashMap<>();
		Optional<Categoria> c = servicio.buscarPorId(categoria.getId());
		if (!c.isPresent()) {
			error.put("mensaje", "No se encontro la categoria con id: ".concat(categoria.getId().toString()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		Categoria categoriaDb = c.get();
		
		categoriaDb.setTexto(categoria.getTexto());
		return ResponseEntity.ok(servicio.guardar(categoriaDb));
	}

}
