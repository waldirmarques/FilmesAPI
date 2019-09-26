package com.br.waldir.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.CategoriaFilme;
import com.br.waldir.domain.Filme;
import com.br.waldir.domain.Usuario;
import com.br.waldir.repositories.CategoriaFilmeRepository;
import com.br.waldir.repositories.FilmeRepository;
import com.br.waldir.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaFilmeRepository categoriaFilmeRepository;
	@Autowired
	private FilmeRepository filmeRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public boolean instantiateDatabase(){

		Usuario user1 = new Usuario(null, "Waldir", "waldir.marques@dcx.ufpb.br", "12345678");
		
		usuarioRepository.saveAll(Arrays.asList(user1));
		
		CategoriaFilme cf1 = new CategoriaFilme(null,"Ação");
		CategoriaFilme cf2 = new CategoriaFilme(null,"Terror");
		
		Filme f1 = new Filme(null,"Veloses e Furiosos",true,"23/07/2019");
		Filme f2 = new Filme(null,"Boneca assasina",false,null);
		Filme f3 = new Filme(null,"Motoqueiro Fantasma",false,null);
		
		cf1.getFilmes().addAll(Arrays.asList(f1));
		cf2.getFilmes().addAll(Arrays.asList(f2,f3));
		
		f1.getCategoriaFilmes().addAll(Arrays.asList(cf1));
		f1.getCategoriaFilmes().addAll(Arrays.asList(cf2));
		
		usuarioRepository.saveAll(Arrays.asList(user1));
		categoriaFilmeRepository.saveAll(Arrays.asList(cf1,cf2));
		filmeRepository.saveAll(Arrays.asList(f1,f2));
		
		return true;
	}
}
