package com.ergproapontamento.ergpro.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ergproapontamento.ergpro.dto.ApontamentoDTO;
import com.ergproapontamento.ergpro.dto.dados.DadosApontamentoDto;
import com.ergproapontamento.ergpro.dto.dados.RelatorioApontamentosDto;
import com.ergproapontamento.ergpro.dto.dados.SalvarApontamentoDto;
import com.ergproapontamento.ergpro.exception.NotFoundException;
import com.ergproapontamento.ergpro.exception.ValidacoesException;
import com.ergproapontamento.ergpro.repository.entity.Apontamento;
import com.ergproapontamento.ergpro.service.ApontamentoService;
import com.ergproapontamento.ergpro.service.JasperReportService;
import org.springframework.http.HttpHeaders;


@RestController
@RequestMapping("/apontamentos")
public class ApontamentoController {
	
	@Autowired
	private ApontamentoService apontamentoService;
	
	@Autowired
    private JasperReportService jasperReportService;
	

	@GetMapping("/listar")
	public ResponseEntity<List<DadosApontamentoDto>> listarApontamentos() {		
		List<DadosApontamentoDto> apontamento = apontamentoService.listarApontamentos();

		return ResponseEntity.ok(apontamento);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<ApontamentoDTO> buscarApontamentoPorId(@PathVariable Long id) throws NotFoundException {
		ApontamentoDTO apontamentoDto = apontamentoService.buscarApontamentosPorId(id);
		return ResponseEntity.ok(apontamentoDto);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<ApontamentoDTO> salvarApontamento(@RequestBody SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException {
		ApontamentoDTO apontamentoSalvo = apontamentoService.salvarApontamento(salvarApontamentoDto);
		return new ResponseEntity<>(apontamentoSalvo, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/atualizar")
	public ResponseEntity<ApontamentoDTO> atualizarApontamento(@RequestBody SalvarApontamentoDto salvarApontamentoDto) throws ValidacoesException, NotFoundException {
        ApontamentoDTO apontamentoAtualizado = apontamentoService.atualizarApontamentos(salvarApontamentoDto);
        return ResponseEntity.ok(apontamentoAtualizado);
    }
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Apontamento> excluirApontamento(@PathVariable Long id) throws NotFoundException{
		apontamentoService.excluirApontamento(id);
		return ResponseEntity.noContent().build();	
	}	
	
	@GetMapping("/funcionario/{idFuncionario}")
	public List<RelatorioApontamentosDto> listarApontamentosPorFuncionario(
	        @PathVariable Long idFuncionario,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
				
	    return apontamentoService.listarApontamentosPorFuncionario(idFuncionario, startDate, endDate);
	}
	
	
	@GetMapping("/funcionario/{idFuncionario}/relatorio")
    public ResponseEntity<byte[]> gerarRelatorioApontamentos(
            @PathVariable Long idFuncionario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        try {
            // Buscar dados para o relatório
            List<RelatorioApontamentosDto> dadosApontamentos = apontamentoService.listarApontamentosPorFuncionario(idFuncionario, startDate, endDate);

            // Gerar o PDF
            byte[] pdfBytes = jasperReportService.exportApontamentoReport(dadosApontamentos);

            // Configurar headers para a resposta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio_apontamentos.pdf");
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
        	 e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

}
