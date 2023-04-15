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

import com.matiasjimenez.microservicios.app.quiz.models.entity.Quiz;
import com.matiasjimenez.microservicios.app.quiz.service.QuizService;

@RestController
//@CrossOrigin({"*"})
public class QuizController {

	@Autowired
	private QuizService service;

	/*@PostMapping("/parseo") 									Parseo las opciones y las transformo en Personajes
	public ResponseEntity<?> parseo() {
		List<Quiz> quizzes = service.listar();		
		List<Quiz> quizzisAGuardar = new ArrayList<>();
		
		quizzisAGuardar =  quizzes.stream().map(quiz -> {
			List<Personaje> personajes = new ArrayList<>();
			personajes = quiz.getOpciones().stream().map(opcion -> {
				Optional<Personaje> p = service.buscarPersonajePorNombre(opcion.getTexto());
				if(!p.isPresent()) {
					System.out.println("NO ESTA EL PERSONAJE");
				}
				return p.get();
			}).toList();
			quiz.getOpciones().clear();
			quiz.addPersonajes(personajes);
			return quiz;
		}).toList();

		return ResponseEntity.ok(service.guardarTodo(quizzisAGuardar));
	}*/

	@GetMapping("/")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@PostMapping("/")
	public ResponseEntity<?> crear(@RequestBody Quiz quiz) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.guardar(quiz));
	}

	@PutMapping("/editar")
	public ResponseEntity<?> editar(@RequestBody Quiz quiz) {
		Map<String, Object> error = new HashMap<>();
		Optional<Quiz> o = service.buscarPorId(quiz.getId());
		if (!o.isPresent()) {
			error.put("mensaje", "No se encontro la quiz con id: ".concat(quiz.getId().toString()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		Quiz quizDb = o.get();

		quizDb.setPregunta(quiz.getPregunta());
		quizDb.setCategorias(quiz.getCategorias());
		quizDb.setPersonajes(quiz.getPersonajes());
		return ResponseEntity.ok(service.guardar(quizDb));
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarQuiz(@PathVariable Long id) {
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
	public ResponseEntity<?> obtenerQuizAleatorio() {
		Map<String, Object> error = new HashMap<>();
		Optional<Quiz> o = service.obtenerQuizAleatorio();
		if (!o.isPresent()) {
			error.put("mensaje", "error al buscar Quiz Aleatoria");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		return ResponseEntity.ok(o.get());
	}

	@GetMapping("/filtrar-categoria/{term}")
	public ResponseEntity<?> obtenerQuizPorCategoria(@PathVariable String term) {
		List<Quiz> quizz = service.listar();
		Random random = new Random();

		List<Quiz> quizesFiltrados = quizz.stream()
				.filter(q -> q.getCategorias().stream().anyMatch(c -> c.getTexto().equalsIgnoreCase(term)))
				.collect(Collectors.toList());

		Quiz quizReturn = quizesFiltrados.get(random.nextInt(quizesFiltrados.size()));
		return ResponseEntity.ok(quizReturn);
	}

}
