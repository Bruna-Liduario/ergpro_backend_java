package com.ergproapontamento.ergpro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomizacaoExceptions {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionsCustomizado(Exception e) {
		String erro = "Ocorreu um erro: " + e.getMessage(); 
		return new ResponseEntity<String> (erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
