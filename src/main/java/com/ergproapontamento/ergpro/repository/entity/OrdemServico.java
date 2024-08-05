package com.ergproapontamento.ergpro.repository.entity;

import java.time.LocalDate;

import com.ergproapontamento.ergpro.dto.OrdemServicoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordemservico")
@Getter
@Setter
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "datainicio")
	private LocalDate datainicio;
	
	@Column(name = "datafim")
	private LocalDate datafim;
	
	//id_cCusto
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_centrocusto", referencedColumnName = "id", nullable = true)
    private CentroCusto centroCusto;
	 
		//id_tipo_servico
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_tiposervicos", referencedColumnName = "id", nullable = true)
     private TipoServico tipoServico; 
	
	
	public OrdemServicoDTO convertEntityToDto() {
		OrdemServicoDTO ordemServicoDto = new OrdemServicoDTO(id, descricao, datainicio, datafim,
				centroCusto != null ? centroCusto.getId() : null,
						 tipoServico != null ? tipoServico.getId() : null);
		
		return ordemServicoDto;
	}
}
