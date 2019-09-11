package com.br.waldir.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Filme implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nomeFilme;
	private boolean assistido;
	private String dataFilmeAssistido;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(name="FILME_CATEGORIA",
	 	joinColumns = @JoinColumn(name="filme_id"),
		inverseJoinColumns = @JoinColumn(name="categoriaFilmes_id") // isso faz para criar uma chave estrangeira.
		)
	private List<CategoriaFilme> categoriaFilmes = new ArrayList<>();
	
	public Filme() {}	
	
	public Filme(Integer id, String nomeFilme, boolean assistido, String dataFilmeAssistido) {
		super();
		this.id = id;
		this.nomeFilme = nomeFilme;
		this.assistido = assistido;
		this.dataFilmeAssistido = dataFilmeAssistido;
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


	public List<CategoriaFilme> getCategoriaFilmes() {
		return categoriaFilmes;
	}


	public void setCategoriaFilmes(List<CategoriaFilme> categoriaFilmes) {
		this.categoriaFilmes = categoriaFilmes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Filme other = (Filme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
