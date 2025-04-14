package com.ergproapontamento.ergpro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Apontamento;

@Repository
public interface ApontamentoRepository extends JpaRepository<Apontamento, Long>{

	boolean existsByAtividadeId(Long id);

	boolean existsByOrdemServicoId(Long id);

	boolean existsByFuncionariosId(Long id);


	List<Apontamento> findByFuncionariosIdAndDataBetween(Long idFuncionario, LocalDate startDate, LocalDate endDate);

}
