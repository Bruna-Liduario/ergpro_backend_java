package com.ergproapontamento.ergpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
