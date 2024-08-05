package com.ergproapontamento.ergpro.repository.entity;

import java.time.LocalDate;

import com.ergproapontamento.ergpro.dto.CentroCustoDTO;
import com.ergproapontamento.ergpro.enuns.StatusCentroCustoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "centrocusto")
public class CentroCusto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "datainicio")
	private LocalDate datainicio;
	
	@Column(name = "datafim")
	private LocalDate datafim;	
	
	@Enumerated(EnumType.STRING)
	private StatusCentroCustoEnum status;
	
	public StatusCentroCustoEnum [] getStatusValues() {
		return StatusCentroCustoEnum.values();
	}
	
	public CentroCustoDTO convetEntityToDto() {
		CentroCustoDTO centroCustoDTO = new CentroCustoDTO();
		centroCustoDTO.setId(this.getId());
		centroCustoDTO.setNumero(this.getNumero());
		centroCustoDTO.setDescricao(this.getDescricao());
		centroCustoDTO.setDatainicio(this.getDatainicio());
		centroCustoDTO.setDatafim(this.getDatafim());
		centroCustoDTO.setStatus(this.getStatus());

		return centroCustoDTO;
	}
	
}
