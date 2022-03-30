package br.com.cineclube.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;
import br.com.cineclube.tmdb.model.FilmeTMDB;
import br.com.cineclube.tmdb.service.MoviedbService;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	MoviedbService filmeService;
	
	
	@Autowired
	private FilmeRepository dao;
	
	// http://localhost:8080/filmes/save

	@PostMapping("/save")
	public String save(@Valid Filme filme, BindingResult result, 
			Model model) {
		if (result.hasErrors()) {
			// voltar para a manter.html
			System.out.println("ERROR ERROR ERROR ERROR ERROR ");
			return "filmes/manter.html";
		}
		dao.save(filme);
		return "redirect:/filmes/list";
	}
	
	@RequestMapping("/new") // cadastro do filme
	public String newForm(Model model) {
		// objeto filme sera mapeado ao ${filme} da view
		Filme filme = new Filme(); 
		model.addAttribute("filme", filme);
		
		// criar um lista de categorias
		model.addAttribute("categories",Category.values());
		
		return "filmes/manter.html";
	}
	
	@RequestMapping("/delete/{id}") // remove o filme
	public String delete(Model model, 
			@PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/filmes/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Filme> filmeList = dao.findAll();
		model.addAttribute("filmeList", filmeList);
		return "filmes/list.html";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Filme filme = dao.findById(id).get();
		model.addAttribute("filme", filme);
		
		FilmeTMDB filmedb = filmeService.searchMovie(
				filme.getTitle()
		);
		filme.setFilmedb(filmedb);
		
		model.addAttribute("categories",Category.values());
		
		return "filme/manter.html";
	}
}
