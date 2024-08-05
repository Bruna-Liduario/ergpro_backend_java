package com.ergproapontamento.ergpro.controller;

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

import com.ergproapontamento.ergpro.dto.AtividadeDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.AtividadeService;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;

   @GetMapping("/listar")
   public ResponseEntity<List<AtividadeDTO>> listarAtividade(){
	   List<AtividadeDTO> atividadeDto = atividadeService.listar();
	   return ResponseEntity.ok(atividadeDto);
   }
   
   @GetMapping("/buscar/{id}")
	public ResponseEntity<AtividadeDTO> buscarAtividade(@PathVariable Long id) throws NotFoundException {
	   AtividadeDTO atividadeDto = atividadeService.buscarAtividade(id);
		return ResponseEntity.ok(atividadeDto);
	}
   
   @GetMapping("/descricao")
   public List<String> getDescricaoAtividade(){
	   return atividadeService.getDescricaoAtividade();
   }
   
   @PostMapping("/salvar")
	public ResponseEntity<AtividadeDTO> salvarAtividade(@RequestBody AtividadeDTO atividadeDto) throws ValidacoesException{
	   AtividadeDTO atividadeSalva = atividadeService.salvarAtividade(atividadeDto);
		return new ResponseEntity<>(atividadeSalva, HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<AtividadeDTO> atualizarAtividade(@RequestBody AtividadeDTO atividadeDto) throws ValidacoesException, NotFoundException{
		AtividadeDTO atividadeAtualizada = atividadeService.atualizarAtividade(atividadeDto);
		return ResponseEntity.ok(atividadeAtualizada);
		
	}
		
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<AtividadeDTO> excluirCargo(@PathVariable Long id) throws NotFoundException, ValidacoesException {
		if(atividadeService.validarPossibilidadeExclusaoAtividade(id)) {
			atividadeService.excluirAtividade(id);
			return ResponseEntity.noContent().build();
		} else {
			 return ResponseEntity.badRequest().build();
		}		
	}
}
