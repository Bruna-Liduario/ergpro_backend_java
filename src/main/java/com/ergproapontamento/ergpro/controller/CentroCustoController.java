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

import com.ergproapontamento.ergpro.dto.CentroCustoDTO;
import com.ergproapontamento.ergpro.enuns.StatusCentroCustoEnum;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.CentroCustoService;

@RestController
@RequestMapping("/centrocusto")
public class CentroCustoController {

	@Autowired
	private CentroCustoService centroCustoService;
	
	//listar
	@GetMapping("/listar")
	public ResponseEntity<List<CentroCustoDTO>> listarCentroCusto(){
		List<CentroCustoDTO> centroCustoDTO = centroCustoService.listarCentroCusto();
		return ResponseEntity.ok(centroCustoDTO);
	}
	
	//buscar
	@GetMapping("/buscar/{id}")
	public ResponseEntity<CentroCustoDTO> buscarCentroCusto(@PathVariable Long id) throws NotFoundException{
		CentroCustoDTO centroCustoDto = centroCustoService.buscarCentroCustoPorId(id);
		return ResponseEntity.ok(centroCustoDto);
	}
	
	@GetMapping("/numeros")
    public List<String> getNumerosCentroCusto() {
        return centroCustoService.getNumerosCentroCusto();
    }
	
	//buscar status
	@GetMapping("/buscar/status")
	  public StatusCentroCustoEnum[] getCentroCustoValues() {
	    return StatusCentroCustoEnum.values();
	  }
	
	//salvar
	@PostMapping("/salvar")
	public ResponseEntity<CentroCustoDTO> salvarCentroCusto(@RequestBody CentroCustoDTO centroCustoDTO) throws ValidacoesException  {
		CentroCustoDTO centroCustoSalvo = centroCustoService.salvarCentroCusto(centroCustoDTO);
		return new ResponseEntity<>(centroCustoSalvo, HttpStatus.CREATED);
	}
	
	//atualizar
	@PutMapping("/atualizar")
	public ResponseEntity<CentroCustoDTO> atualizarCentroCusto(@RequestBody CentroCustoDTO centroCustoDTO) throws NotFoundException, ValidacoesException {
		CentroCustoDTO centroCustoAtualizado = centroCustoService.atualizarCentroCustoDto(centroCustoDTO);
		return ResponseEntity.ok(centroCustoAtualizado);
	}
	
	
	//excluir
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> excluirCentroCusto (@PathVariable Long id) throws NotFoundException, ValidacoesException {
		if(centroCustoService.validarPossibilidadeExclusaoCentroCusto(id)) {
			centroCustoService.excluirCentroCusto(id);
			return ResponseEntity.noContent().build();
		}else {
			 return ResponseEntity.badRequest().build();
		}
	}
	
}
