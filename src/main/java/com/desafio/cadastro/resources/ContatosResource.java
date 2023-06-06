package com.desafio.cadastro.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cadastro.entities.Contatos;
import com.desafio.cadastro.services.ContatosService;



@RestController
@RequestMapping(value = "/contatos")
public class ContatosResource {
	
	@Autowired
	private ContatosService service;
	
	@GetMapping
	public ResponseEntity<List<Contatos>> findAll() {
		List<Contatos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Contatos> findById(@PathVariable Long id){
		Contatos obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
