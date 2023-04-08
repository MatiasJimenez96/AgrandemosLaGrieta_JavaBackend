package com.matiasjimenez.microservicios.app.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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

import com.matiasjimenez.microservicios.app.quiz.models.entity.Opcion;
import com.matiasjimenez.microservicios.app.quiz.models.entity.Quiz;
import com.matiasjimenez.microservicios.app.quiz.service.QuizService;

@RestController
//@CrossOrigin({"*"})
public class QuizController {

	@Autowired
	private QuizService service;
	
	@GetMapping("/")
	public ResponseEntity<?>listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping("/")
	public ResponseEntity<?>crear(@RequestBody Quiz quiz){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.guardar(quiz));
	}
	
	@PutMapping("/{id}/agregar-opciones")
	public ResponseEntity<?> agregarOpciones(@RequestBody List<Opcion> opciones, @PathVariable Long id){
		Map<String, Object> error = new HashMap<>();
		Optional<Quiz> o = service.buscarPorId(id);
		if (!o.isPresent()) {
			error.put("mensaje", "No se encontro la quiz con id: ".concat(id.toString()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		
		Quiz quizDb = o.get();
		quizDb.addOpciones(opciones);
		return ResponseEntity.accepted().body(service.guardar(quizDb));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?>editar(@RequestBody Quiz quiz){
		Map<String, Object> error = new HashMap<>();
		Optional<Quiz> o = service.buscarPorId(quiz.getId());
		if (!o.isPresent()) {
			error.put("mensaje", "No se encontro la quiz con id: ".concat(quiz.getId().toString()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		Quiz quizDb = o.get();
		
		quizDb.setPregunta(quiz.getPregunta());
		quizDb.setOpciones(quiz.getOpciones());
		quizDb.setCategorias(quiz.getCategorias());
		return ResponseEntity.ok(service.guardar(quizDb));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?>eliminarQuiz(@PathVariable Long id){
		Map<String, Object> error = new HashMap<>();
		Optional<Quiz> o = service.buscarPorId(id);
		if (!o.isPresent()) {
			error.put("mensaje", "No se encontro la quiz con id: ".concat(id.toString()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		service.eliminarPorId(id);
		error.put("mensaje", "Quiz id:".concat(id.toString()).concat(" eliminada!"));
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}
	
	@GetMapping("/aleatorio")
	public ResponseEntity<?>obtenerQuizAleatorio(){
		Map<String, Object> error = new HashMap<>();
		Optional<Quiz> o = service.obtenerQuizAleatorio();
		if (!o.isPresent()) {
			error.put("mensaje", "error al buscar Quiz Aleatoria");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		return ResponseEntity.ok(o.get());
	}
	
	@GetMapping("/filtrar-categoria/{term}")
	public ResponseEntity<?>obtenerQuizPorCategoria(@PathVariable String term){
		List<Quiz> quizz = service.listar();
		Random random = new Random();
		
		List<Quiz> quizesFiltrados = quizz.stream()
                .filter(q -> q.getCategorias().stream()
                                              .anyMatch(c -> c.getTexto().equalsIgnoreCase(term)))
                .collect(Collectors.toList());
		
		Quiz quizReturn = quizesFiltrados.get(random.nextInt(quizesFiltrados.size()));
		return ResponseEntity.ok(quizReturn);
	}
	
	
}
