package com.br.waldir.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.CategoriaFilme;
import com.br.waldir.repositories.CategoriaFilmeRepository;
import com.br.waldir.servives.exceptions.DataIntegrityException;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

import io.netty.handler.codec.http2.Http2FrameLogger.Direction;

@Service
public class CategoriaFilmeService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private CategoriaFilmeRepository repo;
	
	public CategoriaFilme find(Integer id) {
		Optional <CategoriaFilme> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaFilme.class.getName()));
	}

	public CategoriaFilme insert(CategoriaFilme obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public CategoriaFilme update(CategoriaFilme obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivevel excluir uma Categoria que possui Filmes");
		}
	}

	public List<CategoriaFilme> findAll() {
		return repo.findAll();
	}

	public Page<CategoriaFilme> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}	

}
