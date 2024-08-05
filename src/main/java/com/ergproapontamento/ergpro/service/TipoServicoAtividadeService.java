package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.TipoServicoAtividadeRepository;
import com.ergproapontamento.ergpro.repository.entity.Atividade;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;
import com.ergproapontamento.ergpro.repository.entity.TipoServicoAtividade;

@Service
@Transactional
public class TipoServicoAtividadeService {
	
	@Autowired
    private TipoServicoAtividadeRepository tipoServicoAtividaderepository;

	
    public List<TipoServicoAtividade> listarTiposServicosAtividades() {
        return tipoServicoAtividaderepository.findAll();
    }
    
    public List<Atividade> findAtividadesByTiposDeServicos(Long idTipoServico) {
        return tipoServicoAtividaderepository.findByTipoServicoId(idTipoServico)
                                               .stream()
                                               .map(TipoServicoAtividade::getAtividade)
                                               .collect(Collectors.toList());
    }

    public TipoServicoAtividade salvarTipoServicoAtividade(TipoServicoAtividade tipoServicoAtividade) {
    	validarDuplicidade(tipoServicoAtividade);
    	
        return tipoServicoAtividaderepository.save(tipoServicoAtividade);
    }

   
    public TipoServicoAtividade alterarTipoServicoAtividade(TipoServicoAtividade tipoServicoAtividadeDetails) throws NotFoundException {
        TipoServico tipoServico = tipoServicoAtividadeDetails.getTipoServico();
        Atividade atividade = tipoServicoAtividadeDetails.getAtividade();

        TipoServicoAtividade tipoServicoAtividade = tipoServicoAtividaderepository.findByTipoServicoAndAtividade(tipoServico, atividade)
                .orElseThrow(() -> new NotFoundException("Associação não encontrada para TipoServico: "
                        + tipoServico.getDescricao() + " e Atividade: " + atividade.getDescricao()));

        tipoServicoAtividade.setTipoServico(tipoServicoAtividadeDetails.getTipoServico());
        tipoServicoAtividade.setAtividade(tipoServicoAtividadeDetails.getAtividade());
    

        return tipoServicoAtividaderepository.save(tipoServicoAtividade);
    }

    public void deletarTipoServicoAtividade(Long id) throws NotFoundException {
        TipoServicoAtividade tipoServicoAtividade = tipoServicoAtividaderepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associação não encontrada para ID: " + id));
        tipoServicoAtividaderepository.delete(tipoServicoAtividade);
    }
    
   
    
    private void validarDuplicidade(TipoServicoAtividade tipoServicoAtividade) {
        Optional<TipoServicoAtividade> existente = tipoServicoAtividaderepository
                .findByTipoServicoAndAtividade(tipoServicoAtividade.getTipoServico(), tipoServicoAtividade.getAtividade());

        if (existente.isPresent()) {
            throw new IllegalArgumentException("Essa associação de Tipo de Serviço e Atividade já existe.");
        }
    }

}
