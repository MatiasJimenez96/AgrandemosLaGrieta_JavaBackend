package com.matiasjimenez.microservicios.app.quiz.service;

import java.util.List;
import java.util.Optional;

import com.matiasjimenez.microservicios.app.quiz.models.entity.Quiz;

public interface QuizService {
	
	public List<Quiz> listar();
	
	public Quiz guardar(Quiz quiz);
	
	public void eliminarPorId(Long id);
	
	public Optional<Quiz> buscarPorId(Long id);
	
	public Optional<Quiz> obtenerQuizAleatorio();

}
