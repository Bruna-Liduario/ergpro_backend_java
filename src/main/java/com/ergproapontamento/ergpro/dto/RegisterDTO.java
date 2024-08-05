package com.ergproapontamento.ergpro.dto;

import com.ergproapontamento.ergpro.enuns.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {	
	

}
