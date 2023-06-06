package com.desafio.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cadastro.entities.Contatos;
import com.desafio.cadastro.repositories.ContatosRepository;

@Service
public class ContatosService {
	
	@Autowired
	private ContatosRepository repository;
	
	public List<Contatos> findAll() {
		return repository.findAll();
	}
	
	public Contatos findById(Long id) {
		Optional<Contatos> obj = repository.findById(id);
		return obj.get();
	}
}
