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
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		pessoaService.insert(p1);
		Pessoa pessoa = pessoaService.findById(p1.getId());
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
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		pessoaService.insert(p1);
		Pessoa updatedData = new Pessoa(null, "Diego", "983.726.820-46", LocalDate.of(1999, 02, 07));
		pessoaService.update(p1.getId(), updatedData);
		assertEquals(repository.findById(p1.getId()).get().getNome(), updatedData.getNome());
		assertEquals(repository.findById(p1.getId()).get().getCpf(), updatedData.getCpf());
		assertEquals(repository.findById(p1.getId()).get().getDataNascimento(), updatedData.getDataNascimento());
	}
	
	@Test
	public void delete(){
		Pessoa p1 = new Pessoa(null, "Dante", "294.556.990-60", LocalDate.of(1999, 10, 23));
		pessoaService.insert(p1);
		pessoaService.delete(p1.getId());
		assertFalse(repository.findById(p1.getId()).isPresent());
	}
	
}
