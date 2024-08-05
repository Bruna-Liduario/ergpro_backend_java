package com.ergproapontamento.ergpro.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.CargoFuncionario;

@Repository
public interface CargoFuncionarioRepository extends JpaRepository<CargoFuncionario, Long> {

	@Query("SELECT c.descricao FROM CargoFuncionario c")
	List<String> findAllNomes();


}
