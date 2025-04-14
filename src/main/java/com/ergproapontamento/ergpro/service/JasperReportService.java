package com.ergproapontamento.ergpro.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ergproapontamento.ergpro.dto.dados.RelatorioApontamentosDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportService {
	
	 public byte[] exportApontamentoReport(List<RelatorioApontamentosDto> apontamentos) throws JRException {
	        // Caminho do template Jasper (.jasper)
	        InputStream reportStream = getClass().getResourceAsStream("/reports/apontamento-pdf.jasper");
	        System.out.println("Caminho do relatório: " + "/reports/apontamento-pdf.jasper");
	        
	        // Verifique se o arquivo foi encontrado
	        if (reportStream == null) {
	            throw new RuntimeException("Relatório não encontrado!");
	        }

	        // Preparando os dados para o relatório
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(apontamentos);
	        
	        // Parâmetros que podem ser enviados ao relatório
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Sistema de Apontamentos");

	        // Preenchendo o relatório com dados
	        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, dataSource);

	        // Exportando para PDF
	        return JasperExportManager.exportReportToPdf(jasperPrint);
	    }

}
