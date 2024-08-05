package com.ergproapontamento.ergpro.enuns;


import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.databind.JsonMappingException;

public enum StatusEmpresaEnum {

    ATIVA,
    DESATIVADA;
	
	@JsonCreator
	public static StatusEmpresaEnum fromValue(String value) throws JsonMappingException {
	    try {
	        return StatusEmpresaEnum.valueOf(value);
	    } catch (IllegalArgumentException e) {
	        throw new JsonMappingException(null, "Campo Status da Empresa é obrigatório");
	    }
	}
	
}
