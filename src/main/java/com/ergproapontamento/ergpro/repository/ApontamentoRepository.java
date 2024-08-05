package com.ergproapontamento.ergpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Apontamento;

@Repository
public interface ApontamentoRepository extends JpaRepository<Apontamento, Long>{

	boolean existsByAtividadeId(Long id);

	boolean existsByOrdemServicoId(Long id);

}
