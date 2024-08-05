package com.ergproapontamento.ergpro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.FuncionarioDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosDoFuncionarioDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.CargoFuncionarioRepository;
import com.ergproapontamento.ergpro.repository.CentroCustoRepository;
import com.ergproapontamento.ergpro.repository.EmpresaRepository;
import com.ergproapontamento.ergpro.repository.FuncionarioRepository;
import com.ergproapontamento.ergpro.repository.entity.CargoFuncionario;
import com.ergproapontamento.ergpro.repository.entity.CentroCusto;
import com.ergproapontamento.ergpro.repository.entity.Empresa;
import com.ergproapontamento.ergpro.repository.entity.Funcionario;

@Service
@Transactional
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CentroCustoRepository centroCustoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private CargoFuncionarioRepository cargoRepository;
	
	//listar
//	public List<Funcionario> listarFuncionarios(){
//		return funcionarioRepository.findAllFuncionarios();	
//	}
	
	public List<DadosDoFuncionarioDto> listarFuncionarios(){
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		List<DadosDoFuncionarioDto> listaDeDadosFuncionarios = new ArrayList<DadosDoFuncionarioDto>();
	   
//Para cada funcionário na lista, ela cria um novo objeto DadosDoFuncionarioDto e preenche com os dados do funcionário correspondente.
		for (Funcionario funcionario : funcionarios) {
			DadosDoFuncionarioDto dadosFuncionario = new DadosDoFuncionarioDto();
			dadosFuncionario.setId(funcionario.getId());
			dadosFuncionario.setNome(funcionario.getNome());
			dadosFuncionario.setCpf(funcionario.getCpf());
			dadosFuncionario.setAdmissao(funcionario.getAdmissao());
			dadosFuncionario.setMatricula(funcionario.getMatricula());
			dadosFuncionario.setCidade(funcionario.getCidade());
			dadosFuncionario.setUf(funcionario.getUf());
			
			String nomeEmpresa = funcionario.getEmpresa().getNome() != null ? funcionario.getEmpresa().getNome() : null;
			dadosFuncionario.setNomeEmpresa(nomeEmpresa);
			
			String centroCusto = funcionario.getCentroCusto().getNumero() != null ? funcionario.getCentroCusto().getNumero() + " - " + funcionario.getCentroCusto().getDescricao() : funcionario.getCentroCusto().getNumero();
			dadosFuncionario.setCentroCusto(centroCusto);
			
			String descricaoCargo = funcionario.getCargos().getDescricao() != null ? funcionario.getCargos().getDescricao() : null;
		    dadosFuncionario.setDescricaoCargo(descricaoCargo);
		    
		    listaDeDadosFuncionarios.add(dadosFuncionario);
		}
		return listaDeDadosFuncionarios;
	
	}
	
	//buscar
	public FuncionarioDTO buscarFuncionarioPorId(Long id) throws NotFoundException {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Funcionario não encontrada com o ID: " + id));
		return funcionario.convertEntityToDto();		
	}

	public List<String> getNomeFuncionario(){
		return funcionarioRepository.findAllFuncionarios();
	}
	
	//salvar
	public FuncionarioDTO salvarFuncionario(FuncionarioDTO funcionarioDto) throws ValidacoesException {
		validarFuncionario(funcionarioDto);
		verificarExistenciaIdRelacionamentos(funcionarioDto);
		validacaoCampoObrigatorio(funcionarioDto);
		
		Funcionario funcionario = convertDtoToEntity(funcionarioDto);
		funcionario = funcionarioRepository.save(funcionario);
		
		return funcionario.convertEntityToDto();
	}
	
	//atualizar
	public FuncionarioDTO atualizarFuncionario(FuncionarioDTO funcionarioDto) throws ValidacoesException, NotFoundException {
		validarFuncionarioAlteracao(funcionarioDto);
		validarFuncionarioExistente(funcionarioDto.getId());
		verificarExistenciaIdRelacionamentos(funcionarioDto);
		validacaoCampoObrigatorio(funcionarioDto);
		
		Funcionario funcionario = convertDtoToEntity(funcionarioDto);
		funcionario = funcionarioRepository.save(funcionario);
		
		return funcionario.convertEntityToDto();
	}	
	
	//excluir
	public void excluirFuncionario(Long id) throws NotFoundException {
	    validarFuncionarioExistente(id);
		funcionarioRepository.deleteById(id);		
	}	
	
	
	
	
	
	
	public void validarFuncionario(FuncionarioDTO funcionarioDto) throws ValidacoesException {		
		if(funcionarioRepository.existsByMatricula(funcionarioDto.getMatricula())) {
			throw new ValidacoesException("Matricula já cadastrada!");
		}				
	}
	
	public void validarFuncionarioAlteracao(FuncionarioDTO funcionarioDto) throws ValidacoesException {
		if(funcionarioDto.getId() != null) {
			Funcionario existingFuncionario = funcionarioRepository.findById(funcionarioDto.getId()).orElse(null);
			if(existingFuncionario != null && !existingFuncionario.getMatricula().equals(funcionarioDto.getMatricula())) {
				throw new ValidacoesException("Não é permitido alterar matrícula"); 
			}
		}		
	}
		
    public void validarFuncionarioExistente(Long id) throws NotFoundException {
		if (!funcionarioRepository.existsById(id)) {
            throw new NotFoundException("Funcionario não encontrado com o ID: " + id);
        }
	}
    
    public void verificarExistenciaIdRelacionamentos(FuncionarioDTO funcionarioDto) throws ValidacoesException {
		if(funcionarioDto.getIdCentroCusto() != null && !centroCustoRepository.existsById(funcionarioDto.getIdCentroCusto())) {
	        throw new ValidacoesException("ID Centro de Custo não existe!");
	    }
		if(funcionarioDto.getIdEmpresa() != null && !empresaRepository.existsById(funcionarioDto.getIdEmpresa())) {
	        throw new ValidacoesException("ID Empresa não existe!");
	    }
		if(funcionarioDto.getIdCargo() != null && !cargoRepository.existsById(funcionarioDto.getIdCargo())) {
	        throw new ValidacoesException("ID Cargo não existe!");
	    }		
	}
    
    public void validacaoCampoObrigatorio(FuncionarioDTO funcionarioDto) throws ValidacoesException {
    	if(funcionarioDto.getMatricula() == null || funcionarioDto.getNome() == null || 
    			funcionarioDto.getIdEmpresa() == null || funcionarioDto.getIdCentroCusto() == null ||
    			funcionarioDto.getIdCargo() == null) {
			throw new ValidacoesException("Campo de preenchimento obrigatório!");
		}		
    }
    
	
    
    
    
    private Funcionario convertDtoToEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setAdmissao(funcionarioDTO.getAdmissao());
        funcionario.setMatricula(funcionarioDTO.getMatricula());
        funcionario.setNascimento(funcionarioDTO.getNascimento());
        funcionario.setGenero(funcionarioDTO.getGenero());
        funcionario.setEstadoCivil(funcionarioDTO.getEstadoCivil());
        funcionario.setGrau(funcionarioDTO.getGrau());
        funcionario.setTel1(funcionarioDTO.getTel1());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setCidade(funcionarioDTO.getCidade());
        funcionario.setUf(funcionarioDTO.getUf());
             
        // Relacionamentos
        if (funcionarioDTO.getIdEmpresa() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(funcionarioDTO.getIdEmpresa());
            funcionario.setEmpresa(empresa);
        }
        
        if (funcionarioDTO.getIdCentroCusto() != null) {
            CentroCusto centroCusto = new CentroCusto();
            centroCusto.setId(funcionarioDTO.getIdCentroCusto());
            funcionario.setCentroCusto(centroCusto);
        }
        
        if (funcionarioDTO.getIdCargo() != null) {
            CargoFuncionario cargo = new CargoFuncionario();
            cargo.setId(funcionarioDTO.getIdCargo());
            funcionario.setCargos(cargo);
        }
        
        return funcionario;
    }
    
}
	

