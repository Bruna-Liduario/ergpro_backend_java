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

import com.ergproapontamento.ergpro.dto.CargoFuncionarioDTO;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.service.CargoFuncionarioService;

@RestController
@RequestMapping("/cargos")
public class CargoFuncionarioController {
	
	@Autowired
	private CargoFuncionarioService cargoFuncionarioService;

	
	@GetMapping("/listar")
	public ResponseEntity<List<CargoFuncionarioDTO>> listarCargos() {
		List<CargoFuncionarioDTO> cargoFuncionarioDTO = cargoFuncionarioService.listarCargos();
		return ResponseEntity.ok(cargoFuncionarioDTO);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<CargoFuncionarioDTO> buscarCargo(@PathVariable Long id) throws NotFoundException {
		CargoFuncionarioDTO cargoFuncionarioDTO = cargoFuncionarioService.buscarCargos(id);
		return ResponseEntity.ok(cargoFuncionarioDTO);
	}
	
	@GetMapping("/nomes")
    public List<String> getNomesCargos() {
        return cargoFuncionarioService.getNomesCargos();
    }
	
	@PostMapping("/salvar")
	public ResponseEntity<CargoFuncionarioDTO> salvarCargo(@RequestBody CargoFuncionarioDTO cargoFuncionarioDto) throws ValidacoesException{
		CargoFuncionarioDTO cargoSalvo = cargoFuncionarioService.salvarCargo(cargoFuncionarioDto);
		return new ResponseEntity<>(cargoSalvo, HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<CargoFuncionarioDTO> atualizarCargo(@RequestBody CargoFuncionarioDTO cargoFuncionarioDto) throws ValidacoesException, NotFoundException{
		CargoFuncionarioDTO cargoAtualizado = cargoFuncionarioService.atualizarCargo(cargoFuncionarioDto);
		return ResponseEntity.ok(cargoAtualizado);
		
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<CargoFuncionarioDTO> excluirCargo(@PathVariable Long id) throws NotFoundException, ValidacoesException {
		if(cargoFuncionarioService.validarPossibilidadeExclusaoCargo(id)) {
			cargoFuncionarioService.excluirCargo(id);
			return ResponseEntity.noContent().build();
		} else {
			 return ResponseEntity.badRequest().build();
		}
		
		
	}
}
