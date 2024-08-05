package com.ergproapontamento.ergpro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ergproapontamento.ergpro.dto.EmpresaDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosDaEmpresaDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;

import com.ergproapontamento.ergpro.repository.ClienteRepository;
import com.ergproapontamento.ergpro.repository.EmpresaRepository;
import com.ergproapontamento.ergpro.repository.FuncionarioRepository;
import com.ergproapontamento.ergpro.repository.entity.Empresa;

@Service
@Transactional
public class EmpresaService {

	private static final String MSG_STATUS_OBRIGATORIO = "Campo StatusEmpresa é obrigatório";
	private static final String MSG_CAMPO_NOME_OBRIGATORIO = "Campo Nome empresa é obrigatório";
	
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	
	public List<DadosDaEmpresaDto> listarEmpresas(){
//		List<Empresa> empresas = empresaRepository.findAll();
//        return empresas.stream()
//                .map(e -> e.convertEntityToDto())
//                .collect(Collectors.toList());
		
		List<Empresa> empresas = empresaRepository.findAll();
		List<DadosDaEmpresaDto> listaDados = new ArrayList<DadosDaEmpresaDto>();
	    for (Empresa empresa : empresas ) {
	    	DadosDaEmpresaDto dados = new DadosDaEmpresaDto();
	    	dados.setId(empresa.getId());
	    	dados.setNome(empresa.getNome());
	    	dados.setEmail(empresa.getEmail());
	    	dados.setCnpj(empresa.getCnpj());
	    	dados.setTel1(empresa.getTel1());
	    	dados.setUf(empresa.getUf());
	    	dados.setStatusEmpresa(empresa.getStatusEmpresa());
	   
	    	listaDados.add(dados);
	    }
	    return listaDados;
	}
	
		
	public EmpresaDTO buscarEmpresaPorId(Long id) throws NotFoundException {
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Empresa não encontrada com o ID: " + id));
		return empresa.convertEntityToDto();
	}
	
	public List<String> getNomeEmpresa(){
		return empresaRepository.findAllNomes();
	}
	
		
	public EmpresaDTO salvarEmpresa(EmpresaDTO empresasDTO) throws ValidacoesException {
		validarEmpresas(empresasDTO);
		
		Empresa empresa = convertDtoToEntity(empresasDTO);
		empresa = empresaRepository.save(empresa);
		 
		return empresa.convertEntityToDto();
	}
	
	public EmpresaDTO atualizarEmpresa(EmpresaDTO empresasDTO) throws NotFoundException, ValidacoesException {
		validarEmpresaExistente(empresasDTO.getId());
		validarEmpresas(empresasDTO);
		
		Empresa empresa = convertDtoToEntity(empresasDTO);
		empresa = empresaRepository.save(empresa);
		
		return empresa.convertEntityToDto();
    }

	
	public void excluirEmpresa(Long id) throws NotFoundException, ValidacoesException {
        validarEmpresaExistente(id);
        validarPossibilidadeExclusaoEmpresa(id);
        empresaRepository.deleteById(id);
    }
	
	
	
	
	
	
	public void validarEmpresas(EmpresaDTO empresasDTO) throws ValidacoesException {				
        if(empresasDTO.getNome() == null || empresasDTO.getNome().trim().isEmpty()) {
			throw new ValidacoesException(MSG_CAMPO_NOME_OBRIGATORIO);
			}        
       
        if (empresasDTO.getStatusEmpresa() == null) {
            throw new ValidacoesException(MSG_STATUS_OBRIGATORIO);
        }
        
        if(empresasDTO.getId() != null) {
			Empresa existingEmpresas = empresaRepository.findById(empresasDTO.getId()).orElse(null);
			if(existingEmpresas != null && !existingEmpresas.getNome().equals(empresasDTO.getNome())) {
				throw new ValidacoesException("Não é permitido editar o nome da empresa");
			}
		}
	}		
	
	
	private void validarEmpresaExistente(Long id) throws NotFoundException {
        if (!empresaRepository.existsById(id)) {
            throw new NotFoundException("Empresa não encontrado com o ID: " + id);
        }
    }
	
	
	public boolean validarPossibilidadeExclusaoEmpresa(Long id) throws ValidacoesException {
		if(clienteRepository.existsByEmpresaId(id)) {
	        throw new ValidacoesException("Não é possível excluir a empresa pois existem Clientes associados a ela");
	    } 
		if(funcionarioRepository.existsByEmpresaId(id)) {
			throw new ValidacoesException("Não é possível excluir a empresa pois existem Funcionários associados a ela");
		} 
		return true;
	}

	

	private Empresa convertDtoToEntity(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setNome(empresaDTO.getNome());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setTel1(empresaDTO.getTel1());
        empresa.setEmail(empresaDTO.getEmail());
        empresa.setRua(empresaDTO.getRua());
        empresa.setNumero(empresaDTO.getNumero());
        empresa.setComplemento(empresaDTO.getComplemento());
        empresa.setBairro(empresaDTO.getBairro());
        empresa.setCep(empresaDTO.getCep());
        empresa.setCidade(empresaDTO.getCidade());
        empresa.setUf(empresaDTO.getUf());
        empresa.setStatusEmpresa(empresaDTO.getStatusEmpresa());
       
        return empresa;
    } 
	
}
