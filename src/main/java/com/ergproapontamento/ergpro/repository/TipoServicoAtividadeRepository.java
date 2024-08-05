package com.ergproapontamento.ergpro.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Atividade;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;
import com.ergproapontamento.ergpro.repository.entity.TipoServicoAtividade;

@Repository
public interface TipoServicoAtividadeRepository extends JpaRepository<TipoServicoAtividade, Long>{

	 Optional<TipoServicoAtividade> findByTipoServicoAndAtividade(TipoServico tipoServico, Atividade atividade);

	Collection<TipoServicoAtividade> findByTipoServicoId(Long idTipoServico);

}
