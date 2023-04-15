package com.matiasjimenez.microservicios.app.quiz.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matiasjimenez.microservicios.app.quiz.models.entity.Quiz;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query("SELECT q.id FROM Quiz q")
	public List<Long> obtenerIdsQuiz();
	
	@Query("SELECT p FROM Personaje  p where p.nombre like %?1%")
	public Optional<Personaje> buscarPersonajePorNombre(String nombre);


}
