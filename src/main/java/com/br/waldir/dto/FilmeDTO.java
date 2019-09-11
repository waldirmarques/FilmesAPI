package com.br.waldir.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.br.waldir.domain.Filme;

public class FilmeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório") //faz com que o preenchimento da categoria fique obrigatorio
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nomeFilme;
	private boolean assistido;
	private String dataFilmeAssistido;
	
	public FilmeDTO() {
		
	}
	
	public FilmeDTO(Filme filme) {
		id = filme.getId();
		nomeFilme = filme.getNomeFilme();
		assistido = filme.isAssistido();
		dataFilmeAssistido = filme.getDataFilmeAssistido();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public boolean isAssistido() {
		return assistido;
	}

	public void setAssistido(boolean assistido) {
		this.assistido = assistido;
	}

	public String getDataFilmeAssistido() {
		return dataFilmeAssistido;
	}

	public void setDataFilmeAssistido(String dataFilmeAssistido) {
		this.dataFilmeAssistido = dataFilmeAssistido;
	}
	
}
