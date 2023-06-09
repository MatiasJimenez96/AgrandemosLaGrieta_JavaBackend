package com.matiasjimenez.microservicios.app.categorias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matiasjimenez.microservicios.app.categorias.models.repository.CategoriaRepository;
import com.matiasjimenez.microservicios.commons.categoria.models.entity.Categoria;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listar() {
		return repositorio.findAll();
	}

	@Override
	@Transactional
	public Categoria guardar(Categoria categoria) {
		return repositorio.save(categoria);
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> buscarPorId(Long id) {
		return repositorio.findById(id);
	}

}
