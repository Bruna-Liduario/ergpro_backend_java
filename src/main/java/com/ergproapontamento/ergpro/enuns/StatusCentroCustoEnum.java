package com.ergproapontamento.ergpro.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonMappingException;

public enum StatusCentroCustoEnum {
       
	ATIVO,
    FINALIZADO;
	
	@JsonCreator
	public static StatusCentroCustoEnum fromValue(String value) throws JsonMappingException {
	    try {
	        return StatusCentroCustoEnum.valueOf(value);
	    } catch (IllegalArgumentException e) {
	        throw new JsonMappingException(null, "Campo Status Centro de Custo é obrigatório");
	    }
	}   


}
