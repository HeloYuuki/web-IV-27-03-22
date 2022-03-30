package br.com.cineclube.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.cineclube.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
		@Transactional
		@Modifying(clearAutomatically = true, flushAutomatically = true)
		@Query("delete from Pessoa p where p.id = :id")
		void removerPessoa(Long id);
	}

