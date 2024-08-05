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
public class DadosApontamentoDto {
	
	private Long id;
	private String local;
	private LocalDate data;
    private String minutos;
    private String minutosExtra;
	private String observacao;
	
	private String nomeFuncionario;
	private String descricaoAtividade;
	private String descricaoOrdemServico;
	private String centroCusto;
	private String descricaoTipoServico;
	
	
}
