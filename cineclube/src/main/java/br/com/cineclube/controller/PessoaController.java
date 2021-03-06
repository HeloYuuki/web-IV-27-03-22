package br.com.cineclube.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.tmdb.model.PessoaTMDB;
import br.com.cineclube.tmdb.service.PersondbService;


@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PersondbService apiService;
	
	
	@Autowired
	PessoaRepository dao;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("pessoaList",dao.findAll());
		return "pessoa/list.html";
	}
	
	@GetMapping("/new")
	public String newForm(Model model) {
		Pessoa p = new Pessoa();
		model.addAttribute("pessoa", p);
		return "pessoa/manterPessoa.html";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.removerPessoa(id);
		return "redirect:/pessoas/list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Pessoa p = dao.findById(id).get();
		
		Optional<PessoaTMDB> personOpt = apiService.searchByName(p.getNome());
		if (personOpt.isPresent()) {
			PessoaTMDB persondb = personOpt.get();
			persondb = apiService.getById(persondb.getId());
			p.setpessoaTMDB(persondb);
		}
		model.addAttribute("pessoa", p);
		
		
		return "pessoa/manterPessoa.html";
	}
	
	@PostMapping("/save")
	public String save(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if(result.hasErrors())
			return "pessoa/manterPessoa.html";
		dao.save(pessoa);
		return "redirect:/pessoas/list";
	}
}
