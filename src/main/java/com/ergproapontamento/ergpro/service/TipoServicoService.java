package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.TipoServicoDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.OrdemServicoRepository;
import com.ergproapontamento.ergpro.repository.TipoServicoRepository;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;

@Service
@Transactional
public class TipoServicoService {
	
	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public List<TipoServicoDTO> listar () {
		List<TipoServico> tipoServico = tipoServicoRepository.findAll();
		return tipoServico.stream()
				.map(e -> e.convertEntityToDto())
				.collect(Collectors.toList());
	}
	
	
	//buscar
	public TipoServicoDTO buscarTipoServico(Long id) throws NotFoundException {
		TipoServico tipoServico = tipoServicoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Tipo de Servico não encontrado: " + id));
		return tipoServico.convertEntityToDto();
	}
	
	public List<String> getDescricaoServico(){
		return tipoServicoRepository.findAllDescricao();
	}
	
	
	//salvar
	public TipoServicoDTO salvarTipoServico(TipoServicoDTO tipoServicoDTO) throws ValidacoesException {
		validarTipoServico(tipoServicoDTO);
		
		TipoServico tipoServico = convertDtoToEntity(tipoServicoDTO);
		tipoServico = tipoServicoRepository.save(tipoServico);
			
		return tipoServico.convertEntityToDto();
	}
	
	
	//atualizar
	public TipoServicoDTO atualizarTipoServico(TipoServicoDTO tipoServicoDto) throws ValidacoesException, NotFoundException {
		validarTipoServico(tipoServicoDto);
		validarTiposServicosExistentes(tipoServicoDto.getId());
		
		TipoServico tipoServico = convertDtoToEntity(tipoServicoDto);
		tipoServico = tipoServicoRepository.save(tipoServico);
		
		return tipoServico.convertEntityToDto();		
	}
	
	
	//deletar
	public void excluirTipoServico (Long id) throws NotFoundException, ValidacoesException {
		validarPossibilidadeExclusaoTipoServico(id);
		validarTiposServicosExistentes(id);
		tipoServicoRepository.deleteById(id);
	}
	
	
	
	
	
	
	public void validarTipoServico (TipoServicoDTO tipoServicoDto) throws ValidacoesException {
		if(tipoServicoDto.getDescricao() == null || tipoServicoDto.getDescricao().isEmpty()) {
			throw new ValidacoesException("Campo Obrigatório");
		}
	}
	
	private void validarTiposServicosExistentes(Long id) throws NotFoundException {
        if (!tipoServicoRepository.existsById(id)) {
            throw new NotFoundException("Tipo de Servico não encontrado com o ID: " + id);
        }        
    }
	
	
	public boolean validarPossibilidadeExclusaoTipoServico(Long id) throws ValidacoesException {		
		if(ordemServicoRepository.existsByTipoServicoId(id)) {
	        throw new ValidacoesException("Não é possível excluir esse tipo de Servico pois existem ordem de serviço associada a ele");
	    } 
	    return true;
	}
	
	
	
	private TipoServico convertDtoToEntity(TipoServicoDTO tipoServicoDto) {
		TipoServico tipoServico = new TipoServico();
		tipoServico.setId(tipoServicoDto.getId());
		tipoServico.setDescricao(tipoServicoDto.getDescricao());
		
		return tipoServico;
	}
}
