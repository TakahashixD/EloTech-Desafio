package com.desafio.cadastro.services.exceptions;

public class CpfInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CpfInvalidException(Object cpf) {
		super("CPF:" + cpf + " inválido.");
	}
	
}
