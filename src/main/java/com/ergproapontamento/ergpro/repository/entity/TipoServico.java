package com.ergproapontamento.ergpro.repository.entity;

import java.util.List;

import com.ergproapontamento.ergpro.dto.TipoServicoDTO;
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
@Table(name = "tiposervicos")
@Setter
@Getter
public class TipoServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToMany(mappedBy = "tipoServico")
	@JsonIgnore
	private List<TipoServicoAtividade> atividades;
	
	
		
	public TipoServicoDTO convertEntityToDto() {
		TipoServicoDTO tipoServicoDto = new TipoServicoDTO();
		tipoServicoDto.setId(this.getId());
		tipoServicoDto.setDescricao(this.getDescricao());
		
		return tipoServicoDto;		
	}

}
