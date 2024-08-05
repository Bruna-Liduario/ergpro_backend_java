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

import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.entity.Permissao;
import com.ergproapontamento.ergpro.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
	
	 @Autowired
	    private PermissaoService permissaoService;
	 
	    @GetMapping("/listar")
	    public ResponseEntity<List<Permissao>> listarPermissoes() {
	        List<Permissao> permissoes = permissaoService.listarPermissoes();
	        return new ResponseEntity<>(permissoes, HttpStatus.OK);
	    }


	    @GetMapping("/buscar/{id}")
	    public ResponseEntity<Permissao> buscarPermissaoPorId(@PathVariable Long id) {
	        try {
	            Permissao permissao = permissaoService.buscarPermissaoPorId(id);
	            return new ResponseEntity<>(permissao, HttpStatus.OK);
	        } catch (NotFoundException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }


	    @PostMapping("/salvar")
	    public ResponseEntity<Permissao> criarPermissao(@RequestBody Permissao permissao) {
	        Permissao novaPermissao = permissaoService.criarPermissao(permissao);
	        return new ResponseEntity<>(novaPermissao, HttpStatus.CREATED);
	    }


	    @PutMapping("/atualizar")
	    public ResponseEntity<Permissao> atualizarPermissao(@RequestBody Permissao permissao) {
	        return permissaoService.atualizarPermissao(permissao)
	                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }



	    @DeleteMapping("/deletar/{id}")
	    public ResponseEntity<Void> excluirPermissao(@PathVariable Long id) {
	        if (permissaoService.excluirPermissao(id)) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
