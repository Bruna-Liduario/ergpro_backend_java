package com.ergproapontamento.ergpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
	
	@Query("SELECT a.descricao FROM Atividade a")
	List<String> findAllDescricao();

}
