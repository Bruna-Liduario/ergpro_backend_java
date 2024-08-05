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
import com.ergproapontamento.ergpro.dto.FuncionarioDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosDoFuncionarioDto;
import com.ergproapontamento.ergpro.enuns.EstadoCivilEnum;
import com.ergproapontamento.ergpro.enuns.GeneroEnum;
import com.ergproapontamento.ergpro.enuns.GrauEnum;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.entity.Funcionario;
import com.ergproapontamento.ergpro.service.FuncionarioService;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<DadosDoFuncionarioDto>> listarFuncionarios() {
		List<DadosDoFuncionarioDto> funcionario = funcionarioService.listarFuncionarios();

		return ResponseEntity.ok(funcionario);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<FuncionarioDTO> buscarFuncionarioPorId(@PathVariable Long id) throws NotFoundException {
		FuncionarioDTO funcionarioDto = funcionarioService.buscarFuncionarioPorId(id);
		return ResponseEntity.ok(funcionarioDto);
	}
	
	@GetMapping("/nomes")
	public List<String> getNomeFuncionario(){
		return funcionarioService.getNomeFuncionario();
	}
	
	
	//buscar genero
	@GetMapping("/buscar/genero")
	  public GeneroEnum[] getGeneroValues() {
	    return GeneroEnum.values();
	 }
	
	//buscar estado civil
	@GetMapping("/buscar/estadocivil")
	  public EstadoCivilEnum[] getEstadoCivilValues() {
	    return EstadoCivilEnum.values();
	 }
	
	//buscar grau
	@GetMapping("/buscar/grau")
	  public GrauEnum[] getGrauValues() {
	    return GrauEnum.values();
	 }
	
		
	@PostMapping("/salvar")
	public ResponseEntity<FuncionarioDTO> salvarFuncionario(@RequestBody FuncionarioDTO funcionarioDto) throws ValidacoesException {
		FuncionarioDTO funcionarioSalvo = funcionarioService.salvarFuncionario(funcionarioDto);
		return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@RequestBody FuncionarioDTO funcionarioDto) throws ValidacoesException, NotFoundException {
		FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizarFuncionario(funcionarioDto);
		return ResponseEntity.ok(funcionarioAtualizado);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Funcionario> excluirFuncionario(@PathVariable Long id) throws NotFoundException{
		funcionarioService.excluirFuncionario(id);
		return ResponseEntity.noContent().build();	
	}	
	
}
