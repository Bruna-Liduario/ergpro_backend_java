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

import com.ergproapontamento.ergpro.dto.ApontamentoDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosApontamentoDto;
import com.ergproapontamento.ergpro.dto.dados.SalvarApontamentoDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.entity.Apontamento;
import com.ergproapontamento.ergpro.service.ApontamentoService;

@RestController
@RequestMapping("/apontamentos")
public class ApontamentoController {
	
	@Autowired
	private ApontamentoService apontamentoService;

	@GetMapping("/listar")
	public ResponseEntity<List<DadosApontamentoDto>> listarApontamentos() {		
		List<DadosApontamentoDto> apontamento = apontamentoService.listarApontamentos();

		return ResponseEntity.ok(apontamento);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<ApontamentoDTO> buscarApontamentoPorId(@PathVariable Long id) throws NotFoundException {
		ApontamentoDTO apontamentoDto = apontamentoService.buscarApontamentosPorId(id);
		return ResponseEntity.ok(apontamentoDto);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<ApontamentoDTO> salvarApontamento(@RequestBody SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException {
		ApontamentoDTO apontamentoSalvo = apontamentoService.salvarApontamento(salvarApontamentoDto);
		return new ResponseEntity<>(apontamentoSalvo, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/atualizar")
	public ResponseEntity<ApontamentoDTO> atualizarApontamento(@RequestBody SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException, NotFoundException {
        ApontamentoDTO apontamentoAtualizado = apontamentoService.atualizarApontamentos(salvarApontamentoDto);
        return ResponseEntity.ok(apontamentoAtualizado);
    }
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Apontamento> excluirApontamento(@PathVariable Long id) throws NotFoundException{
		apontamentoService.excluirApontamento(id);
		return ResponseEntity.noContent().build();	
	}	

}
