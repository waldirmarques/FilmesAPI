package com.br.waldir.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.Usuario;
import com.br.waldir.repositories.UsuarioRepository;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obgOptional = repo.findById(id);
		return obgOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

}
