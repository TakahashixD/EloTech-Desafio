package com.desafio.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cadastro.entities.Contatos;


public interface ContatosRepository extends JpaRepository<Contatos, Long> {
	
}
