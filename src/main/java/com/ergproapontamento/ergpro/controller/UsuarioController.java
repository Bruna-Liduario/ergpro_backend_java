package com.ergproapontamento.ergpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ergproapontamento.ergpro.dto.dados.DadosUsuario;
import com.ergproapontamento.ergpro.enuns.UserRole;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.entity.Usuario;
import com.ergproapontamento.ergpro.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	 @GetMapping("/listar")
	    public ResponseEntity<List<DadosUsuario>> listarUsuarios() {
	        List<DadosUsuario> usuario = usuarioService.listarUsuarios();
	        return new ResponseEntity<>(usuario, HttpStatus.OK);
	    }


	    @GetMapping("/buscar/{id}")
	    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
	        try {
	        	Usuario usuario = usuarioService.buscarUsuarPorId(id);
	            return new ResponseEntity<>(usuario, HttpStatus.OK);
	        } catch (NotFoundException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
		@GetMapping("/buscar/roles")
		  public UserRole[] getRolesValues() {
		    return UserRole.values();
		  }

	    //salvar usuario esta no Authencication Controller (register) 


		@PutMapping("/atualizar")
		public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
		    return usuarioService.atualizarUsuario(usuario)
		            .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
		            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}



	    @DeleteMapping("/deletar/{id}")
	    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
	        if (usuarioService.excluirUsuario(id)) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
