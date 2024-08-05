package com.ergproapontamento.ergpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.repository.entity.CentroCustoTipoServico;
import com.ergproapontamento.ergpro.repository.entity.TipoServico;
import com.ergproapontamento.ergpro.service.CentroCustoTipoServicoService;

@RestController
@RequestMapping("/centrocusto-tiposervico")
public class CentroCustoTipoServicoController {
	
	@Autowired
	private CentroCustoTipoServicoService centroCustoTipoServicoService;

    @GetMapping("/listar")
    public List<CentroCustoTipoServico> listarCentroCustoTipoServico() {
        return centroCustoTipoServicoService.listarCentroCustoTipoServico();
    }
    
    @GetMapping("/tiposervico/{idCentroCusto}")
    public List<TipoServico> getTiposServicosByCentroCusto(@PathVariable Long idCentroCusto) {
        return centroCustoTipoServicoService.findTiposServicosByCentroCusto(idCentroCusto);
    }
	
    @PostMapping("/salvar")
    public ResponseEntity<CentroCustoTipoServico> criarCentroCustoTipoServico(@RequestBody CentroCustoTipoServico centroCustoTipoServico) {
    	CentroCustoTipoServico savedEntity = centroCustoTipoServicoService.salvarCentroCustoTipoServico(centroCustoTipoServico);
    return ResponseEntity.ok(savedEntity);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    	centroCustoTipoServicoService.deletarCentroCustoTipoServico(id);
        return ResponseEntity.noContent().build();
    }
	
}
