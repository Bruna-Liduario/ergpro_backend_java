package com.ergproapontamento.ergpro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ergproapontamento.ergpro.dto.dados.DadosUsuario;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.UsuarioRepository;
import com.ergproapontamento.ergpro.repository.entity.Usuario;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder; 
	
	public List<DadosUsuario> listarUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<DadosUsuario> listaUsuario = new ArrayList<DadosUsuario>();
		
		for(Usuario usuario: usuarios) {
			DadosUsuario dados = new DadosUsuario();
			dados.setId(usuario.getId());
			dados.setLogin(usuario.getLogin());
			dados.setRole(usuario.getRole());
			
			listaUsuario.add(dados);
		}
		return listaUsuario;
	} 
	
	public Usuario buscarUsuarPorId(Long id) throws NotFoundException {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Usuario não encontrado!" + id));
		return usuario;	
	}
	

	 public Optional<Usuario> atualizarUsuario(Usuario usuarioAtualizado) {
	        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(usuarioAtualizado.getId());

	        if (usuarioExistenteOpt.isPresent()) {
	            Usuario usuarioExistente = usuarioExistenteOpt.get();

	            // Atualiza os campos do usuário existente com os novos valores
	            usuarioExistente.setLogin(usuarioAtualizado.getLogin());
	            usuarioExistente.setRole(usuarioAtualizado.getRole());

	            // Se a senha fornecida não estiver vazia, criptografa e atualiza a senha
	            if (usuarioAtualizado.getPassword() != null && !usuarioAtualizado.getPassword().isEmpty()) {
	                usuarioExistente.setPassword(passwordEncoder.encode(usuarioAtualizado.getPassword()));
	            }

	            usuarioRepository.save(usuarioExistente);
	            return Optional.of(usuarioExistente);
	        } else {
	            return Optional.empty();
	        }
	    }

	
	
	
	
	public boolean excluirUsuario(Long id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
}
