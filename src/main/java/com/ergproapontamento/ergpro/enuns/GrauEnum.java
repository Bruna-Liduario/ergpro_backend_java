package com.ergproapontamento.ergpro.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonMappingException;

public enum GrauEnum {
   ANALFABETO,
   QUARTA_SÉRIE_INCOMPLETA,
   QUARTA_SÉRIE_COMPLETA,
   PRIMEIRO_GRAU_INCOMPLETO,
   PRIMEIRO_GRAU_COMPLETO,
   SEGUNDO_GRAU_INCOMPLETO,
   SEGUNDO_GRAU_COMPLETO,
   CURSO_TÉCNICO_INCOMPLETO,
   CURSO_TÉCNICO_COMPLETO,
   SUPERIOR_INCOMPLETO,
   SUPERIOR_COMPLETO,
   PÓS_GRADUAÇÃO,
   MESTRADO,
   DOUTORADO,
   PH_D;
	
	@JsonCreator
	public static GrauEnum fromValue(String value) throws JsonMappingException {
	    try {
	        return GrauEnum.valueOf(value);
	    } catch (IllegalArgumentException e) {
	        throw new JsonMappingException(null, "Campo Grau é obrigatório");
	    }
	}
}
