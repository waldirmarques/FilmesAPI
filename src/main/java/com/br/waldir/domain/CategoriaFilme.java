package com.br.waldir.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CategoriaFilme implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nomeCategoriaFilme;
	
	@JsonBackReference //Cola-se essa anotção do lado que é para vir os produtos
	@ManyToMany(mappedBy = "categoriaFilmes")
	private List<Filme> filmes = new ArrayList<>();
	
	public CategoriaFilme() {
		
	}
	
	public CategoriaFilme(Integer id, String nomoCategoriaFilme) {
		super();
		this.id = id;
		this.nomeCategoriaFilme = nomoCategoriaFilme;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCategoriaFilme() {
		return nomeCategoriaFilme;
	}

	public void setNomeCategoriaFilme(String nomoCategoriaFilme) {
		this.nomeCategoriaFilme = nomoCategoriaFilme;
	}
	

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
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
		CategoriaFilme other = (CategoriaFilme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	
	

}
