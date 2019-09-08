package com.br.waldir.dto;

import java.io.Serializable;

import com.br.waldir.domain.CategoriaFilme;

public class CategoriaFilmeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nomeCategoriaFilme;
	
	public CategoriaFilmeDTO() {
		
	}
	
	public CategoriaFilmeDTO(CategoriaFilme categoria) {
		id = categoria.getId();
		nomeCategoriaFilme = categoria.getNomeCategoriaFilme();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nomeCategoriaFilme;
	}

	public void setNome(String nome) {
		this.nomeCategoriaFilme = nome;
	}
	
	
}
