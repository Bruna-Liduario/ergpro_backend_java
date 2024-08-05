package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.AtividadeDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.ApontamentoRepository;
import com.ergproapontamento.ergpro.repository.AtividadeRepository;
import com.ergproapontamento.ergpro.repository.entity.Atividade;


@Service
@Transactional
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private ApontamentoRepository apontamentoRepository;

	public List<AtividadeDTO> listar () {
		List<Atividade> atividade = atividadeRepository.findAll();
		return atividade.stream()
				.map(e -> e.convertEntityToDto())
				.collect(Collectors.toList());
	}
	
	
	//buscar
	public AtividadeDTO buscarAtividade(Long id) throws NotFoundException {
		Atividade atividade = atividadeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Atividade não encontrada: " + id));
		return atividade.convertEntityToDto();
	}
	
	public List<String> getDescricaoAtividade(){
		return atividadeRepository.findAllDescricao();
	}
	
	
	//salvar
	public AtividadeDTO salvarAtividade(AtividadeDTO atividadeDTO) throws ValidacoesException {
		validarAtividade(atividadeDTO);
		
		Atividade atividade = convertDtoToEntity(atividadeDTO);
		atividade = atividadeRepository.save(atividade);
			
		return atividade.convertEntityToDto();
	}
	
	
	//atualizar
	public AtividadeDTO atualizarAtividade(AtividadeDTO atividadeDTO) throws ValidacoesException, NotFoundException {
		validarAtividade(atividadeDTO);
		validarAtividadesExistentes(atividadeDTO.getId());
		
		Atividade atividade = convertDtoToEntity(atividadeDTO);
		atividade = atividadeRepository.save(atividade);
		
		return atividade.convertEntityToDto();		
	}
	
	
	//deletar
	public void excluirAtividade (Long id) throws NotFoundException, ValidacoesException {
		validarPossibilidadeExclusaoAtividade(id);
		validarAtividadesExistentes(id);
		atividadeRepository.deleteById(id);
	}
	
	
	
	
	
	
	public void validarAtividade (AtividadeDTO atividadeDTO) throws ValidacoesException {
		if(atividadeDTO.getDescricao() == null || atividadeDTO.getDescricao().isEmpty()) {
			throw new ValidacoesException("Campo Obrigatório");
		}
	}
	
	private void validarAtividadesExistentes(Long id) throws NotFoundException {
        if (!atividadeRepository.existsById(id)) {
            throw new NotFoundException("Atividade não encontrado com o ID: " + id);
        }
    }
	
		
	public boolean validarPossibilidadeExclusaoAtividade(Long id) throws ValidacoesException {		
		if(apontamentoRepository.existsByAtividadeId(id)) {
	        throw new ValidacoesException("Não é possível excluir atividade pois existem apontamentos associados a ela");
	    } 
	    return true;
	}
	
	
	
	private Atividade convertDtoToEntity(AtividadeDTO atividadeDto) {
		Atividade atividade = new Atividade();
		atividade.setId(atividadeDto.getId());
		atividade.setDescricao(atividadeDto.getDescricao());
		
		return atividade;
	}

}
