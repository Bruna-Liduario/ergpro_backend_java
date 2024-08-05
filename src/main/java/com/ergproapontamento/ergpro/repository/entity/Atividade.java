package com.ergproapontamento.ergpro.repository.entity;


import java.util.List;

import com.ergproapontamento.ergpro.dto.AtividadeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "atividades")
@Getter
@Setter
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToMany(mappedBy = "atividade")
	@JsonIgnore
	private List<TipoServicoAtividade> tiposServicos;
	
	public AtividadeDTO convertEntityToDto() {
	   AtividadeDTO atividadeDto = new AtividadeDTO();
	   atividadeDto.setId(this.getId());
	   atividadeDto.setDescricao(this.getDescricao());
	
	   return atividadeDto;	
	}
	
}
