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
public class SalvarApontamentoDto {	
	
	private Long id;
	private String local;
	private LocalDate data;
	private Integer minutos;
	private Integer minutosExtra;
	private String observacao;
	
	private Long idFuncionarios;
	private Long idAtividade;
	private Long idOrdemServico;
	
	private String minutosSt;
	private String minutosextraSt;

}
