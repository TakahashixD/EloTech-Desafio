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

import com.desafio.cadastro.entities.Pessoa;
import com.desafio.cadastro.repositories.PessoaRepository;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
public class PessoaServiceTest {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository repository;
	
	
	@Test
	public void findById(){
		Pessoa pessoa = pessoaService.findById(1L);
		assertEquals("Dante", pessoa.getNome());
		assertEquals("294.556.990-60", pessoa.getCpf());
		assertEquals(LocalDate.of(1999, 10, 23), pessoa.getDataNascimento());
	}
	
	@Test
	public void insert() {
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		pessoaService.insert(p1);
		assertTrue(repository.findById(p1.getId()).isPresent());
		
	}
	
	@Test
	public void update(){
		Pessoa updatedData = new Pessoa(null, "Diego", "983.726.820-46", LocalDate.of(1999, 02, 07));
		pessoaService.update(1L, updatedData);
		assertEquals(repository.findById(1L).get().getNome(), updatedData.getNome());
		assertEquals(repository.findById(1L).get().getCpf(), updatedData.getCpf());
		assertEquals(repository.findById(1L).get().getDataNascimento(), updatedData.getDataNascimento());
	}
	
	@Test
	public void delete(){
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		pessoaService.insert(p1);
		pessoaService.delete(p1.getId());
		assertFalse(repository.findById(p1.getId()).isPresent());
	}
	
}
