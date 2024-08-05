package com.ergproapontamento.ergpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.PermissaoRepository;
import com.ergproapontamento.ergpro.repository.entity.Permissao;

@Service
@Transactional
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<Permissao> listarPermissoes(){
		return permissaoRepository.findAll();
	}
	
	public Permissao buscarPermissaoPorId(Long id) throws NotFoundException {
		Permissao permissao = permissaoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Permissão não encontrada com id: " + id));
		return permissao;
	}
	
	 public Permissao criarPermissao(Permissao permissao) {
	        return permissaoRepository.save(permissao);
	    }


	 public Optional<Permissao> atualizarPermissao(Permissao permissao) {
	        if (permissaoRepository.existsById(permissao.getId())) {
	            return Optional.of(permissaoRepository.save(permissao));
	        } else {
	            return Optional.empty();
	        }
	 }

	    public boolean excluirPermissao(Long id) {
	        if (permissaoRepository.existsById(id)) {
	            permissaoRepository.deleteById(id);
	            return true;
	        } else {
	            return false;
	        }
	    }
	
}
