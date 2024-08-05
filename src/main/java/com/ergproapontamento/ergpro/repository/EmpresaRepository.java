package com.ergproapontamento.ergpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Query("SELECT e.nome FROM Empresa e")
	List<String> findAllNomes();

}
