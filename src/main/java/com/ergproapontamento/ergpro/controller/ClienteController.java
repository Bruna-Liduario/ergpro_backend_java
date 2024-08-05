package com.ergproapontamento.ergpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ergproapontamento.ergpro.dto.ClienteDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosDoClienteDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public ResponseEntity<List<DadosDoClienteDto>> listarClientes() {
		List<DadosDoClienteDto> clientes = clienteService.listarClientes();

		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) throws NotFoundException {
		ClienteDTO clientesDto = clienteService.buscarClientePorId(id);
		return ResponseEntity.ok(clientesDto);
	}

	@PostMapping("/salvar")
	public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO clientesDto) throws ValidacoesException {
		ClienteDTO clienteSalvo = clienteService.salvarCliente(clientesDto);
		return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO clientesDto) throws NotFoundException, ValidacoesException {
		ClienteDTO clienteAtualizado = clienteService.atualizarCliente(clientesDto);
		return ResponseEntity.ok(clienteAtualizado);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Long id) throws NotFoundException{
		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();	
	}
}
