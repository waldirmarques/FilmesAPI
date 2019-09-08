package com.br.waldir.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.CategoriaFilme;
import com.br.waldir.repositories.CategoriaFilmeRepository;
import com.br.waldir.servives.exceptions.DataIntegrityException;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

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
			throw new DataIntegrityException("Não é possivevel excluir uma Categoria que possui profutos");
		}
		
	}
	
	

}
