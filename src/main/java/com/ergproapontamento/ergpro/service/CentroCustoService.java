package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.CentroCustoDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.CentroCustoRepository;
import com.ergproapontamento.ergpro.repository.FuncionarioRepository;
import com.ergproapontamento.ergpro.repository.OrdemServicoRepository;
import com.ergproapontamento.ergpro.repository.entity.CentroCusto;

@Service
@Transactional
public class CentroCustoService {
	
	@Autowired
	private CentroCustoRepository centroCustoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	//listar
	public List<CentroCustoDTO> listarCentroCusto() {
		List<CentroCusto> centroCusto = centroCustoRepository.findAll();
		return centroCusto.stream()
				.map(e -> e.convetEntityToDto())
				.collect(Collectors.toList());
	}
	
	//buscar
	public CentroCustoDTO buscarCentroCustoPorId(Long id) throws NotFoundException {
		CentroCusto centroCusto = centroCustoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Centro de Custo não encontrado com ID: " + id));
		return centroCusto.convetEntityToDto();
	}
	
	public List<String> getNumerosCentroCusto(){
		return centroCustoRepository.findAllNumeros();
	}
	
	//salvar
	public CentroCustoDTO salvarCentroCusto (CentroCustoDTO centroCustoDTO) throws ValidacoesException {
		validarCentroCusto(centroCustoDTO);
		validacaoCampoObrigatorio(centroCustoDTO);
		
		CentroCusto centroCusto = convertDtoToEntity(centroCustoDTO);
		centroCusto = centroCustoRepository.save(centroCusto);
		
		return centroCusto.convetEntityToDto();
	}
	
	//atualizar
	public CentroCustoDTO atualizarCentroCustoDto(CentroCustoDTO centroCustoDTO) throws NotFoundException, ValidacoesException {
		validarCentroCustoExistente(centroCustoDTO.getId());
		validacaoCampoObrigatorio(centroCustoDTO);
		
		CentroCusto centroCusto = convertDtoToEntity(centroCustoDTO);
		centroCusto = centroCustoRepository.save(centroCusto);
		
		return centroCusto.convetEntityToDto();
	}
	
	//excluir
	public void excluirCentroCusto(Long id) throws NotFoundException, ValidacoesException {
		validarCentroCustoExistente(id);
		validarPossibilidadeExclusaoCentroCusto(id);
		centroCustoRepository.deleteById(id);		
	}
	
	
	
	
	
	
	public void validarCentroCusto(CentroCustoDTO centroCustoDTO) throws ValidacoesException {
		if (centroCustoRepository.existsByNumero(centroCustoDTO.getNumero())) {
	        throw new ValidacoesException("Centro de Custo já cadastrado!");
	    }
	}
	
	public void validacaoCampoObrigatorio(CentroCustoDTO centroCustoDTO) throws ValidacoesException {
		 if (centroCustoDTO.getNumero() == null || centroCustoDTO.getDescricao() == null || centroCustoDTO.getDescricao().isEmpty()
				 || centroCustoDTO.getStatus() == null) {
			        throw new ValidacoesException("Campos Número, Descrição e Status são campos obrigatórios");
	   }
	}
		
	public boolean validarPossibilidadeExclusaoCentroCusto(Long id) throws ValidacoesException {		
		if(funcionarioRepository.existsByCentroCustoId(id)) {
	        throw new ValidacoesException("Não é possível excluir o Centro de Custo pois existem funcionários associados a ele");
	    }
		if(ordemServicoRepository.existsByCentroCustoId(id)) {
	        throw new ValidacoesException("Não é possível excluir esse tipo de Servico pois existem ordem de serviço associada a ele");
	    } 
	    return true;
	}
	
   private void validarCentroCustoExistente(Long id) throws NotFoundException {
        if (!centroCustoRepository.existsById(id)) {
            throw new NotFoundException("Centro Custo não encontrado com o ID: " + id);
        }
    }
   
   
	
	//conversão DtoToEntity
	private CentroCusto convertDtoToEntity(CentroCustoDTO centroCustoDto) {
		CentroCusto centroCusto = new CentroCusto();
		centroCusto.setId(centroCustoDto.getId());
		centroCusto.setNumero(centroCustoDto.getNumero());
		centroCusto.setDescricao(centroCustoDto.getDescricao());
		centroCusto.setDatainicio(centroCustoDto.getDatainicio());
		centroCusto.setDatafim(centroCustoDto.getDatafim());
		centroCusto.setStatus(centroCustoDto.getStatus());
		
		return centroCusto;
	}	

}
