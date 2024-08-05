package com.ergproapontamento.ergpro.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonMappingException;

public enum EstadoCivilEnum {

	SOLTEIRO,
	CASADO,
	DIVORCIADO,
	VIÚVO,
	SEPARADO,
	UNIÃO_ESTÁVEL,
	OUTROS;
	
	@JsonCreator
	public static EstadoCivilEnum fromValue(String value) throws JsonMappingException {
	    try {
	        return EstadoCivilEnum.valueOf(value);
	    } catch (IllegalArgumentException e) {
	        throw new JsonMappingException(null, "Campo Estado Civil é obrigatório");
	    }
	}
	

	
}
