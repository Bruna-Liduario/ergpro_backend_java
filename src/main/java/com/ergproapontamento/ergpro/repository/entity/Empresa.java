package com.ergproapontamento.ergpro.repository.entity;

import com.ergproapontamento.ergpro.dto.EmpresaDTO;
import com.ergproapontamento.ergpro.enuns.StatusEmpresaEnum;
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
@Table(name = "empresas")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StatusEmpresaEnum statusEmpresa;

	//@CreationTimestamp
	//@Column(nullable = false, columnDefinition = "datetime")
	//private LocalDateTime datacadastro;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tel1")
	private String tel1;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "uf")
	private String uf;
	  
	public StatusEmpresaEnum[] getStatusValues() {
        return StatusEmpresaEnum.values();
    }
	
	
	public EmpresaDTO convertEntityToDto() {
      EmpresaDTO empresaDto = new EmpresaDTO(id, statusEmpresa, cnpj, nome, tel1, email, rua, 
    		  numero, complemento,bairro, cep, cidade, uf);
    		          
        return empresaDto;
    }
	
	
	
}
