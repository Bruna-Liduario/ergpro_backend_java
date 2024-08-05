package com.ergproapontamento.ergpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
	
	@Query("SELECT o.descricao FROM OrdemServico o")
	List<String> findAllDescricao();

	boolean existsByTipoServicoId(Long id);

	boolean existsByCentroCustoId(Long id);

}
