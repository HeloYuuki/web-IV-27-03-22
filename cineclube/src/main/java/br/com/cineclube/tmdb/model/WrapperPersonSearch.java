package br.com.cineclube.tmdb.model;

import java.util.List;

public class WrapperPersonSearch {
	
	private List<PessoaTMDB> results;

    public List<PessoaTMDB> getResults() {
    	// descending order
    	results.sort( (f1,f2) -> Double.compare(f2.getPopularity(), f1.getPopularity()) );
        return results;
    }
    public void setResults(List<PessoaTMDB> results) {
        this.results = results;
    }

}
