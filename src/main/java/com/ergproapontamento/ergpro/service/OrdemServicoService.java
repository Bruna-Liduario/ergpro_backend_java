package com.ergproapontamento.ergpro.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.OrdemServicoDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosOrdemServicoDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.ApontamentoRepository;
import com.ergproapontamento.ergpro.repository.CentroCustoRepository;
import com.ergproapontamento.ergpro.repository.OrdemServicoRepository;
import com.ergproapontamento.ergpro.repository.TipoServicoRepository;
import com.ergproapontamento.ergpro.repository.entity.CentroCusto;
import com.ergproapontamento.ergpro.repository.entity.OrdemServico;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;

@Service
@Transactional
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ApontamentoRepository apontamentoRepository;
	
	@Autowired
	private CentroCustoRepository centroCustoRepository;
	
	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	
	//listar
	public List<DadosOrdemServicoDto> listarOrdensServicos(){
		List<OrdemServico> ordensServico = ordemServicoRepository.findAll();
		List<DadosOrdemServicoDto> listaDadosOrdemServico = new ArrayList<DadosOrdemServicoDto>();
		
		for(OrdemServico ordemServico : ordensServico) {
			DadosOrdemServicoDto dadosOrdemServicoDto = new DadosOrdemServicoDto();
			dadosOrdemServicoDto.setId(ordemServico.getId());	
			dadosOrdemServicoDto.setDescricao(ordemServico.getDescricao());
			dadosOrdemServicoDto.setDatainicio(ordemServico.getDatainicio());
			dadosOrdemServicoDto.setDatafim(ordemServico.getDatafim());
			
			String centroCusto = ordemServico.getCentroCusto().getNumero() != null ? ordemServico.getCentroCusto().getNumero() + " - " + ordemServico.getCentroCusto().getDescricao()  : null;
			dadosOrdemServicoDto.setCentroCusto(centroCusto);
			
			String descricaoTipoServico = ordemServico.getTipoServico().getDescricao() != null ? ordemServico.getTipoServico().getDescricao() : null;
			dadosOrdemServicoDto.setDescricaoTipoServico(descricaoTipoServico);
			
			listaDadosOrdemServico.add(dadosOrdemServicoDto);
		}
		return listaDadosOrdemServico;
	}
	
	//buscar
	public OrdemServicoDTO buscarOrdemServico(Long id) throws NotFoundException {
		OrdemServico ordemServico = ordemServicoRepository.findById(id)
		   .orElseThrow(() -> new NotFoundException("Ordem de Servico não encontrada"));
		
		   return ordemServico.convertEntityToDto();		
	}
	
	public List<String> getDescricaoOrdemServico(){
		return ordemServicoRepository.findAllDescricao();
	}
	
	//salvar
	public OrdemServicoDTO salvarOrdemServico(OrdemServicoDTO ordemServicoDto) throws ValidacoesException {
		validarOrdemServico(ordemServicoDto);
		verificarExistenciaIdRelacionamentos(ordemServicoDto);
		
		OrdemServico ordemServico = convertDtoToEntity(ordemServicoDto);
		ordemServico = ordemServicoRepository.save(ordemServico);
		
		return ordemServico.convertEntityToDto();
	}
	
	//atualizar
	public OrdemServicoDTO atualizarOrdemServico(OrdemServicoDTO ordemServicoDto) throws ValidacoesException, NotFoundException {
		validarOrdemServico(ordemServicoDto);
		validarOrdemServicoExistente(ordemServicoDto.getId());
		verificarExistenciaIdRelacionamentos(ordemServicoDto);
		
		OrdemServico ordemServico = convertDtoToEntity(ordemServicoDto);
		ordemServico = ordemServicoRepository.save(ordemServico);
		
		return ordemServico.convertEntityToDto();
	}
	
	//deletar
	public void excluirOrdemServico(Long id) throws NotFoundException, ValidacoesException {
		validarPossibilidadeExclusaoOrdemServico(id);
		validarOrdemServicoExistente(id);
		ordemServicoRepository.deleteById(id);
	}
	
	
	
	public void validarOrdemServico (OrdemServicoDTO ordemServicoDto) throws ValidacoesException {
		if(ordemServicoDto.getDescricao() == null || ordemServicoDto.getDescricao().isEmpty() || ordemServicoDto.getDatainicio() == null || ordemServicoDto.getDatafim() == null) {
			throw new ValidacoesException("Campo Obrigatório");
		}
	}
	
	
	private void validarOrdemServicoExistente(Long id) throws NotFoundException {
        if (!ordemServicoRepository.existsById(id)) {
            throw new NotFoundException("OS não encontrada com o ID: " + id);
        }
    }
	
	
	public boolean validarPossibilidadeExclusaoOrdemServico(Long id) throws ValidacoesException {		
		if(apontamentoRepository.existsByOrdemServicoId(id)) {
	        throw new ValidacoesException("Não é possível excluir essa Ordem de Servico pois existem apontamentos associados a ele");
	    } 
	    return true;
	}
	
	public void verificarExistenciaIdRelacionamentos(OrdemServicoDTO ordemServicoDto) throws ValidacoesException {
		if(ordemServicoDto.getIdCentroCusto() != null && !centroCustoRepository.existsById(ordemServicoDto.getIdCentroCusto())) {
	        throw new ValidacoesException("Centro de Custo não existe!");
	    }
		if(ordemServicoDto.getIdTipoServico() != null && !tipoServicoRepository.existsById(ordemServicoDto.getIdCentroCusto())) {
	        throw new ValidacoesException("Tipo Servico não encontrado!");
	    }
	}
	
	
	
	private OrdemServico convertDtoToEntity(OrdemServicoDTO ordemServicoDto) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(ordemServicoDto.getId());
		ordemServico.setDescricao(ordemServicoDto.getDescricao());
		ordemServico.setDatainicio(ordemServicoDto.getDatainicio());
		ordemServico.setDatafim(ordemServicoDto.getDatafim());
		
		//relacionamentos
        if (ordemServicoDto.getIdCentroCusto() != null) {
            CentroCusto centroCusto = new CentroCusto();
            centroCusto.setId(ordemServicoDto.getIdCentroCusto());
            ordemServico.setCentroCusto(centroCusto);
        }
        
        if (ordemServicoDto.getIdTipoServico() != null) {
            TipoServico tipoServico = new TipoServico();
            tipoServico.setId(ordemServicoDto.getIdTipoServico());
            ordemServico.setTipoServico(tipoServico);;
        }
		
		return ordemServico;
	}

}
