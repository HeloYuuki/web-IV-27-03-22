package br.com.cineclube.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.cineclube.tmdb.model.FilmeTMDB;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@JsonIgnore
	@NotNull
	@Past(message = "nao aceita data futura") // nao aceita data futura
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate release;
	
//	@JsonIgnore
	@NotNull
	private Category category;
	
	@Transient
	public FilmeTMDB filmedb; 
	
	// validar [0..10]
//	@JsonIgnore
	@Min(message = "deve ser >=0", value = 0)
	@Max(message ="deve ser <= 10", value = 10)
	@NotNull
	private BigDecimal score;
	

	//  @NotEmpty // pega o caso de entrar "   " 
//	@NotNull // nao aceita valor null
	@NotBlank // nao aceita null e string vazia ""
	private String title;
	
	public Filme() {}
	
	public Filme(String title, LocalDate release, Category cat,
			BigDecimal score) {
		this.title = title;
		this.release = release;
		this.category = cat;
		this.score = score;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getRelease() {
		return release;
	}
	public void setRelease(LocalDate release) {
		this.release = release;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public FilmeTMDB getFilmedb() {
		return filmedb;
	}

	public void setFilmedb(FilmeTMDB filmedb) {
		this.filmedb = filmedb;
	}

}