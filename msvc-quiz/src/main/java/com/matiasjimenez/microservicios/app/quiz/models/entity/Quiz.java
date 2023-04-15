package com.matiasjimenez.microservicios.app.quiz.models.entity;

import java.util.ArrayList;
import java.util.List;

import com.matiasjimenez.microservicios.commons.categoria.models.entity.Categoria;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Personaje;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String pregunta;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Categoria> categorias;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Personaje> personajes;
	/* */

	public Quiz() {
		this.categorias = new ArrayList<>();
		this.personajes = new ArrayList<>();
	}

	/* */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	/**/
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void addCategorias(List<Categoria> categorias) {
		categorias.forEach(this.categorias::add);
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias.clear();
		this.addCategorias(categorias);
	}
	
	public void removeCategoria(Categoria categoria) {
		this.categorias.remove(categoria);
	}
	
	/* */
	public List<Personaje> getPersonajes() {
		return this.personajes;
	}
	
	public void addPersonajes(List<Personaje> personaje) {
		personaje.forEach(this.personajes::add);
	}
	
	public void setPersonajes(List<Personaje> personajes) {
		this.personajes.clear();
		this.addPersonajes(personajes);
	}
	
	public void removePersonaje(Personaje personaje) {
		this.personajes.remove(personaje);
	}
	
}
