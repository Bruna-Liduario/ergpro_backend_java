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

import com.ergproapontamento.ergpro.dto.EmpresaDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosDaEmpresaDto;
import com.ergproapontamento.ergpro.enuns.StatusEmpresaEnum;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.EmpresaService;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	
	@Autowired
	private EmpresaService empresaService;

	
	@GetMapping("/listar")
	public ResponseEntity<List<DadosDaEmpresaDto>> listarEmpresas() {
		List<DadosDaEmpresaDto> empresas = empresaService.listarEmpresas();
		return ResponseEntity.ok(empresas);		
	}

	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<EmpresaDTO> buscarEmpresaPorId(@PathVariable Long id) throws NotFoundException {
		EmpresaDTO empresasDTO = empresaService.buscarEmpresaPorId(id);
		return ResponseEntity.ok(empresasDTO);
	}
	
	 @GetMapping("/nomes")
	 public List<String> getNomesEmpresas() {
	     return empresaService.getNomeEmpresa();
	 }
	
	
	@GetMapping("/buscar/status")
	  public StatusEmpresaEnum[] getEmpresaStatusValues() {
	    return StatusEmpresaEnum.values();
	  }
	

	@PostMapping("/salvar")
	public ResponseEntity<EmpresaDTO> salvarEmpresa(@RequestBody EmpresaDTO empresasDTO) throws ValidacoesException {
		System.out.println("Recebendo requisição para salvar empresa: " + empresasDTO.toString());
		EmpresaDTO empresaSalva = empresaService.salvarEmpresa(empresasDTO);
	    return new ResponseEntity<>(empresaSalva, HttpStatus.CREATED);
	}

	
	@PutMapping("/atualizar")
	public ResponseEntity<EmpresaDTO> atualizarEmpresa(@RequestBody EmpresaDTO empresasDTO) throws NotFoundException, ValidacoesException {
		EmpresaDTO empresaAtualizada = empresaService.atualizarEmpresa(empresasDTO);
		
		return ResponseEntity.ok(empresaAtualizada);
	}
	
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id) throws NotFoundException, ValidacoesException{
		if(empresaService.validarPossibilidadeExclusaoEmpresa(id)) {
			empresaService.excluirEmpresa(id);
	        return ResponseEntity.noContent().build();
		} else {
			  return ResponseEntity.badRequest().build();
		}
	}
	
}
