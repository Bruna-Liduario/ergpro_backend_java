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
@Table(name = "tiposervicoatividade")
@Getter
@Setter
public class TipoServicoAtividade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "id_tiposervico", nullable = false)
	private TipoServico tipoServico;
	
	@ManyToOne
    @JoinColumn(name = "id_atividade", nullable = false)
	private Atividade atividade;
	
	public TipoServicoAtividade() {
	}
	
	public TipoServicoAtividade(TipoServico tipoServico, Atividade atividade) {
        this.tipoServico = tipoServico;
        this.atividade = atividade;
    }
}
