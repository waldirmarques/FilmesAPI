package com.br.waldir;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.waldir.domain.CategoriaFilme;
import com.br.waldir.domain.Filme;
import com.br.waldir.repositories.CategoriaFilmeRepository;

@SpringBootApplication
public class FilmesApiApplication {
	
	@Autowired
	private CategoriaFilmeRepository categoriaFilmeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FilmesApiApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		
		CategoriaFilme cf1 = new CategoriaFilme(null,"Ação");
		CategoriaFilme cf2 = new CategoriaFilme(null,"Terror");
		
		Filme f1 = new Filme(null,"Veloses e Furiosos",true,"23/07/2019");
		Filme f2 = new Filme(null,"Boneca assasina",false,"");
		
		cf1.getFilmes().addAll(Arrays.asList(f1));
		cf2.getFilmes().addAll(Arrays.asList(f2));
		
		categoriaFilmeRepository.saveAll(Arrays.asList(cf1));
		categoriaFilmeRepository.saveAll(Arrays.asList(cf2));
		
		
		
		
	}
}
