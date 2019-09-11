package com.br.waldir.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
			throw new DataIntegrityException("Não é possivevel excluir uma CategoriaFilme que possui Filmes");
		}
	}

	public List<CategoriaFilme> findAll() {
		return repo.findAll();
	}

	public Page<CategoriaFilme> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public CategoriaFilme fromDTO(@Valid CategoriaFilme objDto) {
		return new CategoriaFilme(objDto.getId(),objDto.getNomeCategoriaFilme());
	}

}
