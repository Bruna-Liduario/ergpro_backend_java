package com.ergproapontamento.ergpro.repository.entity;

import java.time.LocalDate;

import com.ergproapontamento.ergpro.dto.FuncionarioDTO;
import com.ergproapontamento.ergpro.enuns.EstadoCivilEnum;
import com.ergproapontamento.ergpro.enuns.GeneroEnum;
import com.ergproapontamento.ergpro.enuns.GrauEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Getter
@Setter
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "admissao")
	private LocalDate admissao;
	
	@Column(name = "matricula")
	private Integer matricula;

	@Column(name = "nascimento")
	private LocalDate nascimento;
	
	@Enumerated(EnumType.STRING)
	private GeneroEnum genero;
	 
	@Enumerated(EnumType.STRING)
	private EstadoCivilEnum estadoCivil;
	 
	@Enumerated(EnumType.STRING)
	private GrauEnum grau;
	
	@Column(name = "tel1")
	private String tel1;
	
	@Column(name = "email")
	private String email;	
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "uf")
	private String uf;		
	
	
	//id_empresa
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresas", referencedColumnName = "id", nullable = true)
    private Empresa empresa;
	
	//id_cCusto
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_centrocusto", referencedColumnName = "id", nullable = true)
     private CentroCusto centroCusto;
	
	//id_cargo
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_cargos", referencedColumnName = "id", nullable = true)
     private CargoFuncionario cargos;

	 
	 
	public FuncionarioDTO convertEntityToDto() {
		FuncionarioDTO funcionarioDto = new FuncionarioDTO(id, nome, cpf, admissao, matricula, nascimento, genero,  
				estadoCivil, grau, tel1, email, cidade, uf, empresa != null ? empresa.getId() : null,
				centroCusto != null ? centroCusto.getId() : null, cargos != null ? cargos.getId() : null  );
		
		return funcionarioDto;
	}
}
