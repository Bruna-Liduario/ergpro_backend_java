package com.ergproapontamento.ergpro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidacoesException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidacoesException(String mensagem) {
		super(mensagem);		
	}
}
