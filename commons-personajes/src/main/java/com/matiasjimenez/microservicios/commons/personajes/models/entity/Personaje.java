package com.matiasjimenez.microservicios.commons.personajes.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personajes")
public class Personaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;

	/* */
	public Personaje() {
	}
	
	public Personaje(String nombre) {
		this.nombre = nombre;
	}
	
	/* */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Personaje)) {
			return false;
		}

		Personaje p = (Personaje) obj;
		return this.id != null && this.id.equals(p.getId());
	}
	
	

}
