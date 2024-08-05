package com.ergproapontamento.ergpro.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ergproapontamento.ergpro.repository.entity.CentroCusto;
import com.ergproapontamento.ergpro.repository.entity.CentroCustoTipoServico;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;

@Repository
public interface CentroCustoTipoServicoRepository extends JpaRepository<CentroCustoTipoServico, Long> {


	Optional<CentroCustoTipoServico> findByCentroCustoAndTipoServico(CentroCusto centroCusto, TipoServico tipoServico);

	Collection<CentroCustoTipoServico> findByCentroCustoId(Long idCentroCusto);

}
