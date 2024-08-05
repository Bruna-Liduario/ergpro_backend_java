package com.ergproapontamento.ergpro.dto.dados;

import com.ergproapontamento.ergpro.enuns.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosUsuario {
	
	private Long id;
	private String login;
	private UserRole role;

}
