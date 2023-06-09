package com.desafio.cadastro.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome não deve estar em branco.")
	@NotNull
	private String nome;
	
	@NotBlank(message = "CPF não deve estar em branco.")
	@NotNull
	private String cpf;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data de nascimento não deve ser vazia.")
	@Past(message = "Data de nascimento deve estar no passado.")
	private LocalDate dataNascimento;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contatos> contatos = new ArrayList<>();
	
	//Constructors
	public Pessoa() {
	}
	
	public Pessoa(Long id, String name, String cpf, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = name;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<Contatos> getContatos() {
		return contatos;
	}
	
	
	//hashCode e equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
