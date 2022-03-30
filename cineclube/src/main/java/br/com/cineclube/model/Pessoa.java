package br.com.cineclube.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.cineclube.tmdb.model.PessoaTMDB;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=3, max=50, message="Nome precisa conter no mínimo {min} caracteres!!")
	@Column(nullable = false)
	private String nome;
	
	@Transient
	public PessoaTMDB pessoaTMDB;
	
	public Pessoa() {}
	
	public Pessoa(String nome, LocalDate dataNasc) {
		this.nome = nome;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PessoaTMDB getpessoaTMDB() {
		return pessoaTMDB;
	}

	public void setpessoaTMDB(PessoaTMDB pessoaTMDB) {
		this.pessoaTMDB = pessoaTMDB;
	}

}
	
