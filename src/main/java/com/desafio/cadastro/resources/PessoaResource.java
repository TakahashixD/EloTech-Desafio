package com.desafio.cadastro.resources;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cadastro.entities.Pessoa;



@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	
	@GetMapping
	public ResponseEntity<Pessoa> findAll() {
		Pessoa p = new Pessoa(1L, "Diego", "12360043851", LocalDate.of(1999, 7, 2));
		return ResponseEntity.ok().body(p);
		
	}
}
