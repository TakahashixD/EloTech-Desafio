package com.desafio.cadastro.config;


import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.desafio.cadastro.entities.Pessoa;
import com.desafio.cadastro.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void run(String... args) throws Exception {
		Pessoa p1 = new Pessoa(null, "Dante", "12360043852", LocalDate.of(1999, 10, 23));
		Pessoa p2 = new Pessoa(null, "Vergil", "12360043853", LocalDate.of(1999, 10, 23));
		
		pessoaRepository.saveAll(Arrays.asList(p1, p2));
	}
}
