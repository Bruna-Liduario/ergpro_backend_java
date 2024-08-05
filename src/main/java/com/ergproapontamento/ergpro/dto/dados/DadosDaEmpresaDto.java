package com.ergproapontamento.ergpro.dto.dados;

import com.ergproapontamento.ergpro.enuns.StatusEmpresaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosDaEmpresaDto {
	
	private Long id;
	private StatusEmpresaEnum statusEmpresa;
	private String cnpj;
	private String nome;
	private String tel1;
	private String email;
//	private String rua;
//	private String numero;
//	private String complemento;
//	private String bairro;
//	private String cep;
//	private String cidade;
	private String uf;

}
