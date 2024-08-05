package com.ergproapontamento.ergpro.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.dto.ClienteDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosDoClienteDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.ClienteRepository;
import com.ergproapontamento.ergpro.repository.EmpresaRepository;
import com.ergproapontamento.ergpro.repository.entity.Cliente;
import com.ergproapontamento.ergpro.repository.entity.Empresa;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EmpresaRepository empresaRepository;
	
	public List<DadosDoClienteDto> listarClientes(){
//		List<Cliente> clientes = clienteRepository.findAll();
//        return clientes.stream()
//                .map(e -> e.convertEntityToDto())
//                .collect(Collectors.toList());
		
		List<Cliente> clientes = clienteRepository.findAll();
		List<DadosDoClienteDto> listaDeDados = new ArrayList<DadosDoClienteDto>();
		for (Cliente cliente : clientes) {
			DadosDoClienteDto dados = new DadosDoClienteDto();
			dados.setId(cliente.getId());
			dados.setNome(cliente.getNome());
			dados.setCnpj(cliente.getCnpj());
			dados.setRazaoSocial(cliente.getRazaoSocial());
			dados.setTel1(cliente.getTel1());
			dados.setEmail(cliente.getEmail());
			dados.setUf(cliente.getUf());
			
			String nomeEmpresa = cliente.getEmpresa().getCnpj() != null ? cliente.getEmpresa().getCnpj() + " - " + cliente.getEmpresa().getNome() : cliente.getEmpresa().getNome();
			dados.setNomeEmpresa(nomeEmpresa);
			
			listaDeDados.add(dados);
		}
		return listaDeDados;
	}
	
		
	public ClienteDTO buscarClientePorId(Long id) throws NotFoundException {
		Cliente clientes = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente não encontrada com o ID: " + id));
		return clientes.convertEntityToDto();		
	}
	
		
	public ClienteDTO salvarCliente (ClienteDTO clientesDto) throws ValidacoesException {
		validarClientes(clientesDto);
		Cliente clientes = convertDtoToEntity(clientesDto);
		clientes = clienteRepository.save(clientes);
		
		return clientes.convertEntityToDto();
	}
	
	
	public ClienteDTO atualizarCliente(ClienteDTO clientesDto) throws NotFoundException, ValidacoesException {
		validarClienteExistente(clientesDto.getId());
		validarClientes(clientesDto);
		
		Cliente clientes = convertDtoToEntity(clientesDto);
		clientes = clienteRepository.save(clientes);
		
        return clientes.convertEntityToDto();
    }

	
	public void excluirCliente(Long id) throws NotFoundException {
		validarClienteExistente(id);
		clienteRepository.deleteById(id);
    }
	
	
	
	
	
	
	
	
	public void validarClientes(ClienteDTO clientesDto) throws ValidacoesException {
				
        if(clientesDto.getNome() == null || clientesDto.getNome().trim().isEmpty()) {
			throw new ValidacoesException("Campo Nome cliente é obrigatório");
		}
        
        if(clientesDto.getId() != null) {
			Cliente existingClientes = clienteRepository.findById(clientesDto.getId()).orElse(null);
			if(existingClientes != null && !existingClientes.getNome().equals(clientesDto.getNome())) {
				throw new ValidacoesException("Não é permitido editar o nome do Cliente");
			}
		}
        
        Long idEmpresa = clientesDto.getIdEmpresa();
        if (idEmpresa == null) {
            throw new ValidacoesException("Obrigatório informar empresa");
        } else {
            if (!empresaRepository.existsById(idEmpresa)) {
                throw new ValidacoesException("Não existe empresa com ID: " + idEmpresa);
            }
        }
       
	}
					
	private void validarClienteExistente(Long id) throws NotFoundException {
        if (!clienteRepository.existsById(id)) {
            throw new NotFoundException("Cliente não encontrado com o ID:"+ " " + id);
        }
    }	
	
	private Cliente convertDtoToEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setCnpj(clienteDTO.getCnpj());
		cliente.setRazaoSocial(clienteDTO.getRazaoSocial());
		cliente.setTel1(clienteDTO.getTel1());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setRua(clienteDTO.getRua());
		cliente.setNumero(clienteDTO.getNumero());
		cliente.setComplemento(clienteDTO.getComplemento());
		cliente.setBairro(clienteDTO.getBairro());
		cliente.setCep(clienteDTO.getCep());
		cliente.setCidade(clienteDTO.getCidade());
		cliente.setUf(clienteDTO.getUf());
		
		if (clienteDTO.getIdEmpresa() != null) {
	       Empresa empresa = new Empresa();
	       empresa.setId(clienteDTO.getIdEmpresa());
	       cliente.setEmpresa(empresa);
	    }
       
        return cliente;
    } 
	
	
}
