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
public class ApontamentoDTO {

	private Long id;
	private String local;
	private LocalDate data;
	private String minutos;
	private String minutosExtra;
	private String observacao;
	
	private Long idFuncionarios;
	private Long idAtividade;
	private Long idOrdemServico;
}
