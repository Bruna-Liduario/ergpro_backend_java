package com.ergproapontamento.ergpro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>  {

	@Query("SELECT f.nome from Funcionario f")
	public List<String> findAllFuncionarios();
	
	boolean existsByMatricula(Integer matricula);

	public boolean existsByCentroCustoId(Long id);
	public boolean existsByEmpresaId(Long id);
	public boolean existsByCargosId(Long id);

}
