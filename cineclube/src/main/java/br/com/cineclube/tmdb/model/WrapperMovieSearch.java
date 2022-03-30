package br.com.cineclube.tmdb.model;

import java.util.List;

public class WrapperMovieSearch {
	// representa o json retornado por essa url:
	// http https://api.themoviedb.org/3/search/movie?api_key=d1da20fbfa65312b857fb7b517bf855c query==avatar year==2009 
	
	private List<FilmeTMDB> results;

	public List<FilmeTMDB> getResults() {
		return results;
	}

	public void setResults(List<FilmeTMDB> results) {
		this.results = results;
	}

}
