package com.desafio.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.desafio.cadastro.entities.Contatos;
import com.desafio.cadastro.repositories.ContatosRepository;
import com.desafio.cadastro.services.exceptions.DatabaseException;
import com.desafio.cadastro.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatosService {
	
	@Autowired
	private ContatosRepository repository;
	
	public List<Contatos> findAll() {
		return repository.findAll();
	}
	
	public Contatos findById(Long id) {
		Optional<Contatos> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Contatos insert(Contatos obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			if(repository.existsById(id) == true) {
				repository.deleteById(id);				
			}
			else {
				throw new ResourceNotFoundException(id);
			}
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Contatos update(Long id, Contatos obj) {
		try {
		Contatos entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Contatos entity, Contatos obj) {
		entity.setNome(obj.getNome());
		entity.setTelefone(obj.getTelefone());
		entity.setEmail(obj.getEmail());

	}
}

