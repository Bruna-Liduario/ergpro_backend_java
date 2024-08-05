package com.ergproapontamento.ergpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ergproapontamento.ergpro.repository.entity.Atividade;
import com.ergproapontamento.ergpro.repository.entity.TipoServicoAtividade;
import com.ergproapontamento.ergpro.service.TipoServicoAtividadeService;

@RestController
@RequestMapping("/tiposervico-atividade")
public class TipoServicoAtividadeController {
	
	    @Autowired
	    private TipoServicoAtividadeService tipoServicoAtividadeservice;

	    @GetMapping("/listar")
	    public List<TipoServicoAtividade> listarTipoServicoAtividade() {
	        return tipoServicoAtividadeservice.listarTiposServicosAtividades();
	    }
	    
	    @GetMapping("/atividade/{idTipoServico}")
	    public List<Atividade> getAtividadeByTipoServico(@PathVariable Long idTipoServico) {
	        return tipoServicoAtividadeservice.findAtividadesByTiposDeServicos(idTipoServico);
	    }

	    @PostMapping("/salvar")
	    public ResponseEntity<TipoServicoAtividade> criarTipoServicoAtividade(@RequestBody TipoServicoAtividade tipoServicoAtividade) {
	    TipoServicoAtividade savedEntity = tipoServicoAtividadeservice.salvarTipoServicoAtividade(tipoServicoAtividade);
	    return ResponseEntity.ok(savedEntity);
	    }

	    @PutMapping("/alterar")
	    public TipoServicoAtividade update(@RequestBody TipoServicoAtividade tipoServicoAtividadeDetails) throws NotFoundException {
	        return tipoServicoAtividadeservice.alterarTipoServicoAtividade(tipoServicoAtividadeDetails);
	    }

	    @DeleteMapping("/deletar/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
	    	tipoServicoAtividadeservice.deletarTipoServicoAtividade(id);
	        return ResponseEntity.noContent().build();
	    }
}
