package com.desafio.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cadastro.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
}
