package com.desafio.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.desafio.cadastro.entities.Pessoa;
import com.desafio.cadastro.repositories.PessoaRepository;
import com.desafio.cadastro.services.exceptions.CpfInvalidException;
import com.desafio.cadastro.services.exceptions.DatabaseException;
import com.desafio.cadastro.services.exceptions.ResourceNotFoundException;
import com.desafio.cadastro.validation.CpfValidator;

import javax.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Pessoa insert(Pessoa obj) {
		if (CpfValidator.isCPF(obj.getCpf()) == true) {
			return repository.save(obj);
		} else {
			throw new CpfInvalidException(obj.getCpf());
		}
	}

	public void delete(Long id) {
		try {
			if (repository.existsById(id) == true) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Pessoa update(Long id, Pessoa obj) {
		try {
			Pessoa entity = repository.getReferenceById(id);
			if(CpfValidator.isCPF(entity.getCpf()) == true) {
				updateData(entity, obj);
				return repository.save(entity);
			}
			else {
				throw new CpfInvalidException(entity.getCpf());			
			}
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Pessoa entity, Pessoa obj) {
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setDataNascimento(obj.getDataNascimento());

	}
}
