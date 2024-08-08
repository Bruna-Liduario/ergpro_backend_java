package com.ergproapontamento.ergpro.repository.entity;

import java.time.LocalDate;
import com.ergproapontamento.ergpro.dto.ApontamentoDTO;

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
@Table(name = "apontamentos")
@Getter
@Setter
public class Apontamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "local")
	private String local;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "minutos")
	private Integer minutos;
	
	@Column(name = "minutosextra")
	private Integer minutosExtra;
	
	@Column(name = "obs")
	private String observacao;

	
	//id_func
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_funcionarios", referencedColumnName = "id", nullable = true)
     private Funcionario funcionarios;	

	 
	//id_atividade
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_atividades", referencedColumnName = "id", nullable = true)
     private Atividade atividade; 
	 
	//id_ordemServico
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_ordemservico", referencedColumnName = "id", nullable = true)
     private OrdemServico ordemServico; 

	
	    

	    public ApontamentoDTO convertEntityToDto() {
			 ApontamentoDTO apontamentoDto = new ApontamentoDTO(id, local, data, minutos, minutosExtra, observacao, 
					 funcionarios != null ? funcionarios.getId() : null, 
											 atividade != null ? atividade.getId() : null, 
													 ordemServico != null ? ordemServico.getId() : null);
			 
			 return apontamentoDto;
		 }
	
}
