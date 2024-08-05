package com.ergproapontamento.ergpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ergproapontamento.ergpro.dto.AuthenticationDTO;
import com.ergproapontamento.ergpro.dto.LoginResponseDTO;
import com.ergproapontamento.ergpro.dto.RegisterDTO;
import com.ergproapontamento.ergpro.infra.TokenService;
import com.ergproapontamento.ergpro.repository.UsuarioRepository;
import com.ergproapontamento.ergpro.repository.entity.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Usuario)auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
		if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
		

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		Usuario novoUsuario = new Usuario(data.login(), encryptedPassword, data.role());
		
		this.usuarioRepository.save(novoUsuario);
		
		return ResponseEntity.ok().build();
	}
}
