package com.ergproapontamento.ergpro.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "centrocustotiposervico")
@Getter
@Setter
public class CentroCustoTipoServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_centrocusto", nullable = false)
	private CentroCusto centroCusto;
	
	@ManyToOne
	@JoinColumn(name = "id_tiposervico", nullable = false)
	private TipoServico tipoServico;

	
	public CentroCustoTipoServico() {
		
	}
	
	public CentroCustoTipoServico(CentroCusto centroCusto, TipoServico tipoServico) {
		this.centroCusto = centroCusto;
		this.tipoServico = tipoServico;
	}
}
