package com.matiasjimenez.microservicios.app.categorias.service;

import java.util.List;
import java.util.Optional;

import com.matiasjimenez.microservicios.commons.categoria.models.entity.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listar();
	
	public Categoria guardar(Categoria categoria);
	
	public void eliminarPorId(Long id);
	
	public Optional<Categoria> buscarPorId(Long id);
	
	//public Optional<Categoria> obtenerQuizAleatorio();

}
