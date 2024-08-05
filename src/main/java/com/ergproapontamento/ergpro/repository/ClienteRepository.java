package com.ergproapontamento.ergpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("select c from Cliente c")
	public List<Cliente> listar();

	public boolean existsByEmpresaId(Long id);


}
