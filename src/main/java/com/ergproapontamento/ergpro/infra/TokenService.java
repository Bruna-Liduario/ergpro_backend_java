package com.ergproapontamento.ergpro.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ergproapontamento.ergpro.repository.entity.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					
					.withIssuer("auth-ergpro") //nome que identifica aplicacao					
					.withSubject(usuario.getLogin()) //Subject é usuario que ta recebendo token. salvar usuario no token					
					.withExpiresAt(genExpirationDate()) //tempo de expiracao do token					
					.sign(algorithm); //assinatura e geracao final
			
			return token;
					
		} catch(JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token", exception);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)					
					.withIssuer("auth-ergpro") //emissor				
					.build() //montando o dado					
					.verify(token) //verificando o token descriptografando					
					.getSubject(); //pega o Subject novamente que tinha salvo
		
		}catch(JWTVerificationException exception) {
			return "";
		}
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
	}
}
