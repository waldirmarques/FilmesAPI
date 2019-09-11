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

import com.br.waldir.domain.Filme;
import com.br.waldir.dto.FilmeDTO;
import com.br.waldir.repositories.FilmeRepository;
import com.br.waldir.servives.exceptions.DataIntegrityException;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

@Service
public class FilmeService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private FilmeRepository repo;
	
	public Filme find(Integer id) {
		Optional <Filme> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Filme.class.getName()));
	}

	public Filme insert(Filme obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Filme update(Filme obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivevel excluir uma Filme que possui Filmes");
		}
	}

	public List<Filme> findAll() {
		return repo.findAll();
	}

	public Page<Filme> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Filme fromDTO(@Valid FilmeDTO objDto) {
		return new Filme(objDto.getId(),objDto.getNomeFilme(),objDto.isAssistido(),objDto.getDataFilmeAssistido());
	}

}

