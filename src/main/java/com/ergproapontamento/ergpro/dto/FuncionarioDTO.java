package com.ergproapontamento.ergpro.dto;

import java.time.LocalDate;

import com.ergproapontamento.ergpro.enuns.EstadoCivilEnum;
import com.ergproapontamento.ergpro.enuns.GeneroEnum;
import com.ergproapontamento.ergpro.enuns.GrauEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

	private Long id;
	private String nome;
	private String cpf;
	private LocalDate admissao;
	private Integer matricula;
	private LocalDate nascimento;
	private GeneroEnum genero;
	private EstadoCivilEnum estadoCivil;	 
	private GrauEnum grau;
	private String tel1;
	private String email;	
	private String cidade;
	private String uf;
	
	private Long idEmpresa;
	private Long idCentroCusto;
	private Long idCargo;

}
