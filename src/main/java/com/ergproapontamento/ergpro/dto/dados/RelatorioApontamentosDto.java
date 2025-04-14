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
public class RelatorioApontamentosDto {
	
	private Long id;
	
	private LocalDate data;
    private Integer minutos;
    private Integer minutosExtra;	
	
	private String nomeFuncionario;
	private String descricaoAtividade;
	private String descricaoOrdemServico;
	private String centroCusto;	
	
	private String minutosSt;
	private String minutosextraSt;
	
	private Integer totalMinutos;
	private String totalhorasSt;

}
