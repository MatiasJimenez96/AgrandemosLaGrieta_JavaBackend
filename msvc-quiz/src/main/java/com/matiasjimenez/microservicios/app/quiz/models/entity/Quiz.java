package com.matiasjimenez.microservicios.app.quiz.models.entity;

import java.util.ArrayList;
import java.util.List;

import com.matiasjimenez.microservicios.commons.categoria.models.entity.Categoria;
import com.matiasjimenez.microservicios.commons.personajes.models.entity.Opcion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String pregunta;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Opcion> opciones;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Categoria> categorias;

	/* */

	public Quiz() {
		this.opciones = new ArrayList<>();
		this.categorias = new ArrayList<>();
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

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones.clear();
		this.addOpciones(opciones);
	}

	public void addOpciones(List<Opcion> opciones) {
		opciones.forEach(this.opciones::add);
	}

	public void removeOpcion(Opcion opcione) {
		this.opciones.remove(opcione);
	}

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
	
	
}
