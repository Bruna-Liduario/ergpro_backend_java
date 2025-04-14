package com.ergproapontamento.ergpro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.ApontamentoDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosApontamentoDto;
import com.ergproapontamento.ergpro.dto.dados.RelatorioApontamentosDto;
import com.ergproapontamento.ergpro.dto.dados.SalvarApontamentoDto;
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
	        dadosApontamento.setMinutosSt(formatMinutos(apontamento.getMinutos()));
	        dadosApontamento.setMinutosextraSt(formatMinutos(apontamento.getMinutosExtra()));
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
	
	//listar apontamentos por funcionario
	public List<RelatorioApontamentosDto> listarApontamentosPorFuncionario(Long idFuncionario, LocalDate startDate, LocalDate endDate) {
	    List<Apontamento> apontamentos = apontamentoRepository.findByFuncionariosIdAndDataBetween(idFuncionario, startDate, endDate);
	    List<RelatorioApontamentosDto> listaRelatorioApontamentos = new ArrayList<>();
	    
	    for (Apontamento apontamento : apontamentos) {
	        RelatorioApontamentosDto relatorioApontamento = new RelatorioApontamentosDto();
	        relatorioApontamento.setId(apontamento.getId());
	        relatorioApontamento.setData(apontamento.getData());
	        relatorioApontamento.setMinutos(apontamento.getMinutos());
	        relatorioApontamento.setMinutosExtra(apontamento.getMinutosExtra());
	        relatorioApontamento.setMinutosSt(formatMinutos(apontamento.getMinutos()));
	        relatorioApontamento.setMinutosextraSt(formatMinutos(apontamento.getMinutosExtra()));

	        // Preenche os campos do DTO com base nos valores do Apontamento
	        String nomeFuncionario = (apontamento.getFuncionarios() != null && apontamento.getFuncionarios().getNome() != null) ? 
	                                 apontamento.getFuncionarios().getNome() : null;
	        relatorioApontamento.setNomeFuncionario(nomeFuncionario);
	        
	        String descricaoAtividade = (apontamento.getAtividade() != null && apontamento.getAtividade().getDescricao() != null) ? 
	                                    apontamento.getAtividade().getDescricao() : null;
	        relatorioApontamento.setDescricaoAtividade(descricaoAtividade);
	        
	        String descricaoOrdemServico = (apontamento.getOrdemServico() != null && apontamento.getOrdemServico().getDescricao() != null) ? 
	                                       apontamento.getOrdemServico().getDescricao() : null;
	        relatorioApontamento.setDescricaoOrdemServico(descricaoOrdemServico);
	        
	        String centroCusto = (apontamento.getOrdemServico() != null && apontamento.getOrdemServico().getCentroCusto() != null) ? 
	                             apontamento.getOrdemServico().getCentroCusto().getDescricao() + " - " +  apontamento.getOrdemServico().getCentroCusto().getNumero() : null;
	        relatorioApontamento.setCentroCusto(centroCusto);
	        
	        // Soma dos minutos e minutos extras
	        int totalMinutos = (apontamento.getMinutos() != null ? apontamento.getMinutos() : 0) +
	                           (apontamento.getMinutosExtra() != null ? apontamento.getMinutosExtra() : 0);
	        relatorioApontamento.setTotalMinutos(totalMinutos);
	        
	        // Formata o total de minutos para HH:mm
	        String totalHorasFormatadas = formatMinutos(totalMinutos);
	        relatorioApontamento.setTotalhorasSt(totalHorasFormatadas);

	        listaRelatorioApontamentos.add(relatorioApontamento);
	    }
	    
	    return listaRelatorioApontamentos;
	}
	
	
	// Método para formatar minutos para string HH:mm
	private String formatMinutos(Integer minutos) {
	    if (minutos == null) {
	        return null;
	    }
	    int hours = minutos / 60;
	    int minutes = minutos % 60;
	    return String.format("%02d:%02d", hours, minutes);
	}
	
	
	
	//buscar
	public ApontamentoDTO buscarApontamentosPorId(Long id) throws NotFoundException {
		Apontamento apontamento = apontamentoRepository.findById(id)
		        .orElseThrow(() -> new NotFoundException("Apontamento não criado"));
		
		return apontamento.convertEntityToDto();
	}
	
	//salvar
	public ApontamentoDTO salvarApontamento(SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException {		
		validacaoCampoObrigatorio(salvarApontamentoDto);
		verificarExistenciaIdRelacionamentos(salvarApontamentoDto);
		
		Apontamento apontamento = convertDtoToEntity(salvarApontamentoDto);
		apontamento = apontamentoRepository.save(apontamento);
		
		return apontamento.convertEntityToDto();
	}
	
	//atualizar
	public ApontamentoDTO atualizarApontamentos(SalvarApontamentoDto salvarApontamentoDto) throws NotFoundException, ValidacoesException {
		validarApontamentoExistente(salvarApontamentoDto.getId());
		validacaoCampoObrigatorio(salvarApontamentoDto);
		verificarExistenciaIdRelacionamentos(salvarApontamentoDto);
		
		Apontamento apontamento = convertDtoToEntity(salvarApontamentoDto);
		apontamento = apontamentoRepository.save(apontamento);
		
		return apontamento.convertEntityToDto();
	}
	
	//excluir
	public void excluirApontamento(Long id) throws NotFoundException {
		validarApontamentoExistente(id);
		apontamentoRepository.deleteById(id);
	}
	
	



	
	
	
	
	
	
	public void validacaoCampoObrigatorio(SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException {
    	if(salvarApontamentoDto.getData() == null || salvarApontamentoDto.getMinutosSt() == null || 
    			salvarApontamentoDto.getMinutosextraSt() == null || salvarApontamentoDto.getIdFuncionarios() == null || 
    					salvarApontamentoDto.getIdOrdemServico() == null || salvarApontamentoDto.getIdAtividade() == null ) {
			throw new ValidacoesException("Campo de preenchimento obrigatório!");
		}		
    }
	
	public void validarApontamentoExistente(Long id) throws NotFoundException {
		if (!apontamentoRepository.existsById(id)) {
            throw new NotFoundException("Apontamento não cadastrado: " + id);
        }
	}
	
	
	public void verificarExistenciaIdRelacionamentos(SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException {
		if(salvarApontamentoDto.getIdFuncionarios() != null && !funcionarioRepository.existsById(salvarApontamentoDto.getIdFuncionarios())) {
	        throw new ValidacoesException("Funcionário não encontrado!");
	    }
		if(salvarApontamentoDto.getIdAtividade() != null && !atividadeRepository.existsById(salvarApontamentoDto.getIdAtividade())) {
	        throw new ValidacoesException("Atividade não encontrada!");
	    }
		if(salvarApontamentoDto.getIdOrdemServico() != null && !ordemServicoRepository.existsById(salvarApontamentoDto.getIdOrdemServico())) {
	        throw new ValidacoesException("Ordem de Serviço não encontrada!");
	    }
	}
	
	
	private Apontamento convertDtoToEntity(SalvarApontamentoDto salvarApontamentoDto) {
		Apontamento apontamento = new Apontamento();
		apontamento.setId(salvarApontamentoDto.getId());
		apontamento.setLocal(salvarApontamentoDto.getLocal());
		apontamento.setData(salvarApontamentoDto.getData());
		apontamento.setMinutos(convertHHmmToMinutes(salvarApontamentoDto.getMinutosSt()));
	    apontamento.setMinutosExtra(convertHHmmToMinutes(salvarApontamentoDto.getMinutosextraSt()));
		apontamento.setObservacao(salvarApontamentoDto.getObservacao());
		
		 // Relacionamentos	 
	    if (salvarApontamentoDto.getIdFuncionarios() != null) {
	        Funcionario funcionarios = new Funcionario();
	        funcionarios.setId(salvarApontamentoDto.getIdFuncionarios());
	        apontamento.setFuncionarios(funcionarios);;
	     }
        
        if (salvarApontamentoDto.getIdAtividade() != null) {
            Atividade atividade = new Atividade();
            atividade.setId(salvarApontamentoDto.getIdAtividade());
            apontamento.setAtividade(atividade);;
        }
        
        if (salvarApontamentoDto.getIdOrdemServico() != null) {
            OrdemServico ordemServico = new OrdemServico();
            ordemServico.setId(salvarApontamentoDto.getIdOrdemServico());
            apontamento.setOrdemServico(ordemServico);;
        }
        return apontamento;
	}
	
	private Integer convertHHmmToMinutes(String hhmm) {
        if (hhmm == null || hhmm.isEmpty()) {
            return null;
        }
        String[] parts = hhmm.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
	
		
}
