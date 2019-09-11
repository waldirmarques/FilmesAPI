package com.br.waldir.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seriado implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nomeSeriado;
	private String quantidadeTemporadas;
	private String descricao;
	private boolean assistido;
	private String dataFilmeAssistido;
	
	public Seriado () {
		
	}

	public Seriado(Integer id, String nomeSeriado, String quantidadeTemporadas, String descricao, boolean assistido,
			String dataFilmeAssistido) {
		super();
		this.id = id;
		this.nomeSeriado = nomeSeriado;
		this.quantidadeTemporadas = quantidadeTemporadas;
		this.descricao = descricao;
		this.assistido = assistido;
		this.dataFilmeAssistido = dataFilmeAssistido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeSeriado() {
		return nomeSeriado;
	}

	public void setNomeSeriado(String nomeSeriado) {
		this.nomeSeriado = nomeSeriado;
	}

	public String getQuantidadeTemporadas() {
		return quantidadeTemporadas;
	}

	public void setQuantidadeTemporadas(String quantidadeTemporadas) {
		this.quantidadeTemporadas = quantidadeTemporadas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (assistido ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seriado other = (Seriado) obj;
		if (assistido != other.assistido)
			return false;
		return true;
	}
	
}
