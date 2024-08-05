package com.ergproapontamento.ergpro.repository.entity;

import com.ergproapontamento.ergpro.dto.CargoFuncionarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cargos")
@Getter
@Setter
public class CargoFuncionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	public CargoFuncionarioDTO convertEntityToDto() {
		CargoFuncionarioDTO cargoFuncionarioDTO = new CargoFuncionarioDTO ();
		cargoFuncionarioDTO.setId(this.getId());
		cargoFuncionarioDTO.setDescricao(this.getDescricao());
		
		return cargoFuncionarioDTO;
	}
   
}
