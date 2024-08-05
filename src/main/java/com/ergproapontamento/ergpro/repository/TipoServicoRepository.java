package com.ergproapontamento.ergpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.TipoServico;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long>{

	
	@Query("SELECT t.descricao FROM TipoServico t")
	List<String> findAllDescricao();
}
