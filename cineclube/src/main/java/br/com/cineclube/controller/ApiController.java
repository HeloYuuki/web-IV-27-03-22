package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;

@RestController
public class ApiController {
	
	@Autowired
	private FilmeRepository daoFilme;

	@Autowired
	private PessoaRepository daoPessoa;
	
	// planejar os endpoints
	// GET /filmes/{id}
	// TESTAR:
	// http://localhost:8080/api/filme/3
	@GetMapping("/api/filme/{id}")
	public Filme getFilme(@PathVariable Long id) {
		return daoFilme.getById(id);
	}
	
	@GetMapping("/api/pessoa/get/{id}")
	public Pessoa getPessoa(@PathVariable Long id) {
		Pessoa p = daoPessoa.findById(id).get();
		return p;
	}
	@GetMapping("/api/person/new/{name}")
	public Pessoa newPessoa(@PathVariable String nome) {
		Pessoa p = newPessoa(nome);
		daoPessoa.save(p);
		return p;		
	}
	
	

}