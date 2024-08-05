package com.ergproapontamento.ergpro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.ApontamentoDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosApontamentoDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.ApontamentoRepository;
import com.ergproapontamento.ergpro.repository.AtividadeRepository;
import com.ergproapontamento.ergpro.repository.FuncionarioRepository;
import com.ergproapontamento.ergpro.repository.OrdemServicoRepository;
import com.ergproapontamento.ergpro.repository.entity.Apontamento;
import com.ergproapontamento.ergpro.repository.entity.Atividade;
import com.ergproapontamento.ergpro.repository.entity.Funcionario;
import com.ergproapontamento.ergpro.repository.entity.OrdemServico;


@Service
@Transactional
public class ApontamentoService {

	@Autowired
	private ApontamentoRepository apontamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	
	
	public List<DadosApontamentoDto> listarApontamentos() {
		List<Apontamento> apontamentos = apontamentoRepository.findAll();
		List<DadosApontamentoDto> listaDadosApontamentos = new ArrayList<DadosApontamentoDto>();
		
		for(Apontamento apontamento : apontamentos) {
			DadosApontamentoDto dadosApontamento = new DadosApontamentoDto();
			dadosApontamento.setId(apontamento.getId());
			dadosApontamento.setLocal(apontamento.getLocal());
			dadosApontamento.setData(apontamento.getData());
		    dadosApontamento.setMinutos(apontamento.getMinutos());
	        dadosApontamento.setMinutosExtra(apontamento.getMinutosExtra());
			dadosApontamento.setObservacao(apontamento.getObservacao());
			
			String nomeFuncionario = apontamento.getFuncionarios().getNome() != null ? apontamento.getFuncionarios().getNome() : null;
			dadosApontamento.setNomeFuncionario(nomeFuncionario);
			
			String descricaoAtividade = apontamento.getAtividade().getDescricao() != null ? apontamento.getAtividade().getDescricao() : null;
			dadosApontamento.setDescricaoAtividade(descricaoAtividade);
			
			String descricaoOrdemServico = apontamento.getOrdemServico().getDescricao() != null ? apontamento.getOrdemServico().getDescricao() : null;
			dadosApontamento.setDescricaoOrdemServico(descricaoOrdemServico);
			
	        String centroCusto = apontamento.getOrdemServico() != null && apontamento.getOrdemServico().getCentroCusto() != null ? apontamento.getOrdemServico().getCentroCusto().getDescricao() + " - " +  apontamento.getOrdemServico().getCentroCusto().getNumero(): null;
	        dadosApontamento.setCentroCusto(centroCusto);
			
	        String descricaoTipoServico = apontamento.getOrdemServico() != null && apontamento.getOrdemServico().getTipoServico() != null ? apontamento.getOrdemServico().getTipoServico().getDescricao() : null;
	        dadosApontamento.setDescricaoTipoServico(descricaoTipoServico);
	        	       
	                
			listaDadosApontamentos.add(dadosApontamento);			
		}
		
		return listaDadosApontamentos;
	}
	
	
	//buscar
	public ApontamentoDTO buscarApontamentosPorId(Long id) throws NotFoundException {
		Apontamento apontamento = apontamentoRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Apontamento não criado"));
		
		return apontamento.convertEntityToDto();
	}
	
	//salvar
	public ApontamentoDTO salvarApontamento(ApontamentoDTO apontamentoDto) throws ValidacoesException {		
		validacaoCampoObrigatorio(apontamentoDto);
		verificarExistenciaIdRelacionamentos(apontamentoDto);
		
		Apontamento apontamento = convertDtoToEntity(apontamentoDto);
		apontamento = apontamentoRepository.save(apontamento);
		
		return apontamento.convertEntityToDto();
	}
	
	//atualizar
	public ApontamentoDTO atualizarApontamentos(ApontamentoDTO apontamentoDto) throws NotFoundException, ValidacoesException {
		validarApontamentoExistente(apontamentoDto.getId());
		validacaoCampoObrigatorio(apontamentoDto);
		verificarExistenciaIdRelacionamentos(apontamentoDto);
		
		Apontamento apontamento = convertDtoToEntity(apontamentoDto);
		apontamento = apontamentoRepository.save(apontamento);
		
		return apontamento.convertEntityToDto();
	}
	
	//excluir
	public void excluirApontamento(Long id) throws NotFoundException {
		validarApontamentoExistente(id);
		apontamentoRepository.deleteById(id);
	}

	
	
	
	
	
	
	public void validacaoCampoObrigatorio(ApontamentoDTO apontamentoDto) throws ValidacoesException {
    	if(apontamentoDto.getData() == null || apontamentoDto.getMinutos() == null || 
    			apontamentoDto.getMinutosExtra() == null || apontamentoDto.getIdFuncionarios() == null || 
    			apontamentoDto.getIdOrdemServico() == null || apontamentoDto.getIdAtividade() == null ) {
			throw new ValidacoesException("Campo de preenchimento obrigatório!");
		}		
    }
	
	public void validarApontamentoExistente(Long id) throws NotFoundException {
		if (!apontamentoRepository.existsById(id)) {
            throw new NotFoundException("Apontamento não cadastrado: " + id);
        }
	}
	
	
	public void verificarExistenciaIdRelacionamentos(ApontamentoDTO apontamentoDto) throws ValidacoesException {
		if(apontamentoDto.getIdFuncionarios() != null && !funcionarioRepository.existsById(apontamentoDto.getIdFuncionarios())) {
	        throw new ValidacoesException("Funcionário não encontrado!");
	    }
		if(apontamentoDto.getIdAtividade() != null && !atividadeRepository.existsById(apontamentoDto.getIdAtividade())) {
	        throw new ValidacoesException("Atividade não encontrada!");
	    }
		if(apontamentoDto.getIdOrdemServico() != null && !ordemServicoRepository.existsById(apontamentoDto.getIdOrdemServico())) {
	        throw new ValidacoesException("Ordem de Serviço não encontrada!");
	    }
	}
	
	
	private Apontamento convertDtoToEntity(ApontamentoDTO apontamentoDto) {
		Apontamento apontamento = new Apontamento();
		apontamento.setId(apontamentoDto.getId());
		apontamento.setLocal(apontamentoDto.getLocal());
		apontamento.setData(apontamentoDto.getData());
		apontamento.setMinutos(apontamentoDto.getMinutos());
		apontamento.setMinutosExtra(apontamentoDto.getMinutosExtra());
		apontamento.setObservacao(apontamentoDto.getObservacao());
		
		 // Relacionamentos	 
	    if (apontamentoDto.getIdFuncionarios() != null) {
	        Funcionario funcionarios = new Funcionario();
	        funcionarios.setId(apontamentoDto.getIdFuncionarios());
	        apontamento.setFuncionarios(funcionarios);;
	     }
        
        if (apontamentoDto.getIdAtividade() != null) {
            Atividade atividade = new Atividade();
            atividade.setId(apontamentoDto.getIdAtividade());
            apontamento.setAtividade(atividade);;
        }
        
        if (apontamentoDto.getIdOrdemServico() != null) {
            OrdemServico ordemServico = new OrdemServico();
            ordemServico.setId(apontamentoDto.getIdOrdemServico());
            apontamento.setOrdemServico(ordemServico);;
        }
        return apontamento;
	}
	
	
	
	
}
