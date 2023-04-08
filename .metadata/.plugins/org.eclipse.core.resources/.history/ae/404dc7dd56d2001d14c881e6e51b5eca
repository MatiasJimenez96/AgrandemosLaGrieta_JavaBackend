package com.matiasjimenez.microservicios.app.quiz.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matiasjimenez.microservicios.app.quiz.models.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query("SELECT q.id FROM Quiz q")
	public List<Long> obtenerIdsQuiz();

}
