package com.ergproapontamento.ergpro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoDTO {

	private Long id;
	private String descricao;
	private LocalDate datainicio;
	private LocalDate datafim;
	
	private Long idCentroCusto;
	private Long idTipoServico;
	
}
