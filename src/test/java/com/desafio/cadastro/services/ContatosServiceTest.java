package com.desafio.cadastro.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desafio.cadastro.entities.Contatos;
import com.desafio.cadastro.entities.Pessoa;
import com.desafio.cadastro.repositories.ContatosRepository;


import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
public class ContatosServiceTest {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ContatosService contatosService;
	
	@Autowired
	private ContatosRepository repository;
	
	
	@Test
	public void findById(){
		Contatos c1 = new Contatos(null, "Xico", "4499553448", "xico@gmail.com", null);
		contatosService.insert(c1);
		Contatos contatos = contatosService.findById(c1.getId());
		assertEquals("Xico", contatos.getNome());
		assertEquals("4499553448", contatos.getTelefone());
		assertEquals("xico@gmail.com", contatos.getEmail());
	}
	
	@Test
	public void insert() {
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		Contatos c1 = new Contatos(null, "Xico", "4499553448", "xico@gmail.com", p1);
		contatosService.insert(c1);
		assertTrue(repository.findById(c1.getId()).isPresent());
		
	}
	
	@Test
	public void update() {
		Contatos c1 = new Contatos(null, "Xico", "4499553448", "xico@gmail.com", null);
		contatosService.insert(c1);
		Contatos updatedData = new Contatos(null, "Diego", "4499333448", "diego@gmail.com", null);
		contatosService.update(c1.getId(), updatedData);
		assertEquals("Diego", c1.getNome());
		assertEquals("4499333448", c1.getTelefone());
		assertEquals("diego@gmail.com", c1.getEmail());

	}
	
	@Test
	public void delete(){
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		Contatos c1 = new Contatos(null, "Xico", "4499553448", "xico@gmail.com", p1);
		pessoaService.insert(p1);
		contatosService.insert(c1);
		contatosService.delete(c1.getId());
		assertFalse(repository.findById(c1.getId()).isPresent());
	}
}
