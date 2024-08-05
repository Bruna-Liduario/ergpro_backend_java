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
import com.ergproapontamento.ergpro.dto.OrdemServicoDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosOrdemServicoDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.OrdemServicoService;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoController {
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	//listar
	@GetMapping("/listar")
	public ResponseEntity<List<DadosOrdemServicoDto>> listarOrdensServicos () {
		List<DadosOrdemServicoDto> ordemServico = ordemServicoService.listarOrdensServicos();
		
		return ResponseEntity.ok(ordemServico);
	}
	
	//buscar
	@GetMapping("/buscar/{id}")
	public ResponseEntity<OrdemServicoDTO> buscarOrdemServico(@PathVariable Long id) throws NotFoundException{
		OrdemServicoDTO ordemServicoDto = ordemServicoService.buscarOrdemServico(id);
		
		return ResponseEntity.ok(ordemServicoDto);
	}
	
	@GetMapping("/descricao")
	public List<String> getDescricaoOrdemServico(){
		return ordemServicoService.getDescricaoOrdemServico();
	}
 	
	//salvar
	@PostMapping("/salvar")
	public ResponseEntity<OrdemServicoDTO> salvarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDto) throws ValidacoesException{
		OrdemServicoDTO ordemServicoSalvo = ordemServicoService.salvarOrdemServico(ordemServicoDto);
		
		return new ResponseEntity<>(ordemServicoSalvo, HttpStatus.CREATED);
	}
	
	//atualizar
	@PutMapping("/atualizar")
	public ResponseEntity<OrdemServicoDTO> atualizarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) throws ValidacoesException, NotFoundException {
		OrdemServicoDTO ordemServicoAtualizado = ordemServicoService.atualizarOrdemServico(ordemServicoDTO);
		
		return ResponseEntity.ok(ordemServicoAtualizado);
	}
	
	//excluir
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<OrdemServicoDTO> excluirOrdemServico(@PathVariable Long id) throws NotFoundException, ValidacoesException {
		if(ordemServicoService.validarPossibilidadeExclusaoOrdemServico(id)) {
			ordemServicoService.excluirOrdemServico(id);
			return ResponseEntity.noContent().build();
		} else {
			 return ResponseEntity.badRequest().build();
		}
	}

}
