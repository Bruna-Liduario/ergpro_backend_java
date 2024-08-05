package com.ergproapontamento.ergpro.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonMappingException;

public enum GeneroEnum {

	FEMININO,
	MASCULINO;
	
	@JsonCreator
	public static GeneroEnum fromValue(String value) throws JsonMappingException {
	    try {
	        return GeneroEnum.valueOf(value);
	    } catch (IllegalArgumentException e) {
	        throw new JsonMappingException(null, "Campo Gênero é obrigatório");
	    }
	}

	
}
