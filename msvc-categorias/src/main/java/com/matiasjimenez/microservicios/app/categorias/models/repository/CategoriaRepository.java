package com.matiasjimenez.microservicios.app.categorias.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matiasjimenez.microservicios.commons.categoria.models.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
