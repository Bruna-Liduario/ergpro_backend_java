package com.ergproapontamento.ergpro.repository.entity;

import com.ergproapontamento.ergpro.dto.ClienteDTO;
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
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@CreationTimestamp
	//@Column(name = "datacadastro", nullable = false, columnDefinition = "datetime")
	//private LocalDateTime datacadastro;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "razaosocial")
	private String razaoSocial;
	
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
	
	//id_empresa
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_empresas", referencedColumnName = "id", nullable = true)
	 private Empresa empresa;
	 
	 public ClienteDTO convertEntityToDto() {
		    ClienteDTO clienteDTO = new ClienteDTO(id, cnpj, nome, razaoSocial, tel1, email, 
		    		rua, numero, complemento, bairro, cep, cidade, uf, empresa != null ? empresa.getId() : null);  
		    
	        return clienteDTO;
	    }
}
