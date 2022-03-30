package br.com.cineclube.tmdb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.tmdb.model.FilmeTMDB;
import br.com.cineclube.tmdb.model.WrapperMovieSearch;

@Service // acessada via autowired (uso interno das nossas classes)
public class MoviedbService {
	
	/* realiza a busca por filme, pois essa busca eh usada em diversas partes do sistema */
	// MoviedbService apiService = new MoviedbService();
	
	@Value("${api.moviedb.key}")
    private String apiKey;

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
	RestTemplate apiRequest;
    //@Autowired
    //private RestTemplate apiRequest;
    
	public FilmeTMDB searchMovie(String title){
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/search/movie?api_key=" 
        			+  apiKey + "&query=" + title;
   	
    	WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
    	
    	FilmeTMDB filme = new FilmeTMDB();
    		filme = searchResult.getResults().get(0);

    	
    	return filme;
    }		
    public FilmeTMDB getMovieById(Long id) {
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/movie/" + id + "?api_key=" +  apiKey;
    	
        FilmeTMDB filme = apiRequest.getForObject(filmeUrl, FilmeTMDB.class);
        
        return filme; // serializado em JSON
        
    }
 
    
}
