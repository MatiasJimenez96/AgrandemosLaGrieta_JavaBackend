package com.matiasjimenez.microservicios.app.quiz.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matiasjimenez.microservicios.app.quiz.models.entity.Quiz;
import com.matiasjimenez.microservicios.app.quiz.models.repository.QuizRepository;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	private QuizRepository repositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Quiz> listar() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Quiz guardar(Quiz quiz) {
		return repositorio.save(quiz);
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Quiz> buscarPorId(Long id) {
		return repositorio.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Quiz> obtenerQuizAleatorio() {
		Random random = new Random();
		List<Long> ids = repositorio.obtenerIdsQuiz();
        Long idAleatorio = ids.get(random.nextInt(ids.size()));
		return repositorio.findById(idAleatorio);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Personaje> buscarPersonajePorNombre(String nombre) {
		return repositorio.buscarPersonajePorNombre(nombre);
	}

	@Override
	public List<Quiz> guardarTodo(List<Quiz> quizzis) {
		return repositorio.saveAll(quizzis);
	}

}
