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

import com.br.waldir.domain.Usuario;
import com.br.waldir.dto.UsuarioDTO;
import com.br.waldir.repositories.UsuarioRepository;
import com.br.waldir.servives.exceptions.DataIntegrityException;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obgOptional = repo.findById(id);
		return obgOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}


	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage , Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Usuario fromDTO(@Valid UsuarioDTO objDto) {
		return new Usuario(objDto.getId(),objDto.getName(),objDto.getEmail(),objDto.getSenha());
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

}
