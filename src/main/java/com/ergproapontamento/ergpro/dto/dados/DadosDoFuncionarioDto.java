package com.ergproapontamento.ergpro.dto.dados;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosDoFuncionarioDto {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate admissao;
	private Integer matricula;
//	private LocalDate nascimento;
//	private GeneroEnum genero;
//	private EstadoCivilEnum estadoCivil;	 
//	private GrauEnum grau;
//	private String tel1;
//	private String email;	
	private String cidade;
	private String uf;
	
	private String nomeEmpresa;
	private String centroCusto;
	private String descricaoCargo;
}
