package com.ergproapontamento.ergpro.dto;

import java.time.LocalDate;

import com.ergproapontamento.ergpro.enuns.StatusCentroCustoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentroCustoDTO {

	private Long id;
	private String numero;
	private String descricao;
	private LocalDate datainicio;
	private LocalDate datafim;
	private StatusCentroCustoEnum status;
	
}
