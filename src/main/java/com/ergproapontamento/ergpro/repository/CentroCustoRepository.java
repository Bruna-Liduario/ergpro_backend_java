package com.ergproapontamento.ergpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.CentroCusto;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

	boolean existsByNumero(String string);

	@Query("SELECT c.numero FROM CentroCusto c")
	List<String> findAllNumeros();

}
