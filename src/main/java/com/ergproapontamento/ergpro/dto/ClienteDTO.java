package com.ergproapontamento.ergpro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long id;
	private String cnpj;
	private String nome;
	private String razaoSocial;
	private String tel1;
	private String email;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private Long idEmpresa;
}
