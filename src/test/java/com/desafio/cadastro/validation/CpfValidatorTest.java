package com.desafio.cadastro.validation;

import static org.junit.Assert.assertTrue;



import org.junit.jupiter.api.Test;


public class CpfValidatorTest {
	
	@Test
	public void isCPF(){
		assertTrue(CpfValidator.isCPF("12345678987"));
	}
}
