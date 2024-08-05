package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.CentroCustoTipoServicoRepository;
import com.ergproapontamento.ergpro.repository.entity.CentroCustoTipoServico;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;


@Service
@Transactional
public class CentroCustoTipoServicoService {
	
	@Autowired
	private CentroCustoTipoServicoRepository centroCustoTipoServicoRepository;
	
	public List<CentroCustoTipoServico> listarCentroCustoTipoServico() {
		return centroCustoTipoServicoRepository.findAll();
	}
	
    public CentroCustoTipoServico salvarCentroCustoTipoServico(CentroCustoTipoServico centroCustoTipoServico) {
    	validarDuplicidade(centroCustoTipoServico);;
    	
        return centroCustoTipoServicoRepository.save(centroCustoTipoServico);
    }
    
    
    public List<TipoServico> findTiposServicosByCentroCusto(Long idCentroCusto) {
        return centroCustoTipoServicoRepository.findByCentroCustoId(idCentroCusto)
                                               .stream()
                                               .map(CentroCustoTipoServico::getTipoServico)
                                               .collect(Collectors.toList());
    }
    
    public void deletarCentroCustoTipoServico(Long id) throws NotFoundException {
    	CentroCustoTipoServico centroCustoTipoServico = centroCustoTipoServicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associação não encontrada para ID: " + id));
    	centroCustoTipoServicoRepository.delete(centroCustoTipoServico);;
    }
	
    
	
    private void validarDuplicidade(CentroCustoTipoServico centroCustoTipoServico) {
        Optional<CentroCustoTipoServico> relacaoExistente = centroCustoTipoServicoRepository
                .findByCentroCustoAndTipoServico(centroCustoTipoServico.getCentroCusto(), centroCustoTipoServico.getTipoServico());

        if (relacaoExistente.isPresent()) {
            throw new IllegalArgumentException("Essa associação de Centro de Custo e Tipo de Serviço já existe.");
        }
    }



}
