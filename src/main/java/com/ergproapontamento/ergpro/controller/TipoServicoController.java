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

import com.ergproapontamento.ergpro.dto.TipoServicoDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.TipoServicoService;

import java.util.List;

@RestController
@RequestMapping("/tiposervico")
public class TipoServicoController {

	@Autowired
	private TipoServicoService tipoServicoService;

   @GetMapping("/listar")
   public ResponseEntity<List<TipoServicoDTO>> listarTipoServico(){
	   List<TipoServicoDTO> tipoServicoDto = tipoServicoService.listar();
	   return ResponseEntity.ok(tipoServicoDto);
   }
   
   @GetMapping("/buscar/{id}")
	public ResponseEntity<TipoServicoDTO> buscarTipoServico(@PathVariable Long id) throws NotFoundException {
	   TipoServicoDTO tipoServicoDto = tipoServicoService.buscarTipoServico(id);
		return ResponseEntity.ok(tipoServicoDto);
	}
   
   @GetMapping("/descricao")
   public List<String> getDescricaoServico(){
	   return tipoServicoService.getDescricaoServico();
   }
   
   @PostMapping("/salvar")
	public ResponseEntity<TipoServicoDTO> salvarTipoServico(@RequestBody TipoServicoDTO tipoServicoDto) throws ValidacoesException{
	   TipoServicoDTO servicoSalvo = tipoServicoService.salvarTipoServico(tipoServicoDto);
		return new ResponseEntity<>(servicoSalvo, HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<TipoServicoDTO> atualizarTipoServico(@RequestBody TipoServicoDTO tipoServicoDto) throws ValidacoesException, NotFoundException{
		TipoServicoDTO servicoAtualizado = tipoServicoService.atualizarTipoServico(tipoServicoDto);
		return ResponseEntity.ok(servicoAtualizado);		
	}
	
		
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<TipoServicoDTO> excluirTipoServico(@PathVariable Long id) throws NotFoundException, ValidacoesException {
		if(tipoServicoService.validarPossibilidadeExclusaoTipoServico(id)) {
			tipoServicoService.excluirTipoServico(id);
			return ResponseEntity.noContent().build();
		} else {
			 return ResponseEntity.badRequest().build();
		}
	}
}
