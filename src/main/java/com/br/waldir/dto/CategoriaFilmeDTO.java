package com.br.waldir.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.waldir.domain.CategoriaFilme;

public class CategoriaFilmeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório") //faz com que o preenchimento da categoria fique obrigatorio
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
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
