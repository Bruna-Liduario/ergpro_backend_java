package com.ergproapontamento.ergpro.repository.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.ergproapontamento.ergpro.dto.ApontamentoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "apontamentos")
@Getter
@Setter
public class Apontamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "local")
	private String local;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "minutos")
	private Integer minutos;
	
	@Column(name = "minutosextra")
	private Integer minutosExtra;
	
	@Column(name = "obs")
	private String observacao;

	
	//id_func
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_funcionarios", referencedColumnName = "id", nullable = true)
     private Funcionario funcionarios;	

	 
	//id_atividade
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_atividades", referencedColumnName = "id", nullable = true)
     private Atividade atividade; 
	 
	//id_ordemServico
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_ordemservico", referencedColumnName = "id", nullable = true)
     private OrdemServico ordemServico; 

	 
	 // Converte minutos em formato de hora (HH:mm)
	    public String getFormattedMinutos() {
	        return convertMinutosToTime(minutos);
	    }

	    public String getFormattedMinutosExtra() {
	        return convertMinutosToTime(minutosExtra);
	    }

	    // Método auxiliar para converter minutos em formato de hora
	    private String convertMinutosToTime(Integer minutos) {
	        if (minutos == null) {
	            return "00:00";
	        }

	        int horas = minutos / 60;
	        int minutosRestantes = minutos % 60;

	        return String.format("%02d:%02d", horas, minutosRestantes);
	    }

	    
	 // Método para definir minutos a partir de uma string no formato "HH:mm"
	    public void setMinutos(String minutosStr) {
	    LocalTime time = LocalTime.parse(minutosStr, DateTimeFormatter.ofPattern("HH:mm"));
	    this.minutos = (int) ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, time);
	    }

	    // Método para definir minutos extras a partir de uma string no formato "HH:mm"
	    public void setMinutosExtra(String minutosExtraStr) {
	    LocalTime time = LocalTime.parse(minutosExtraStr, DateTimeFormatter.ofPattern("HH:mm"));
	    this.minutosExtra = (int) ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, time);
	    }

	    // Métodos para obter os minutos no formato "HH:mm"
	    public String getMinutos() {
	    LocalTime time = LocalTime.MIDNIGHT.plusMinutes(minutos);
	    return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	    }

	    public String getMinutosExtra() {
	    LocalTime time = LocalTime.MIDNIGHT.plusMinutes(minutosExtra);
	    return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	    }
	    
	    



	    public ApontamentoDTO convertEntityToDto() {
			 ApontamentoDTO apontamentoDto = new ApontamentoDTO(id, local, data, getMinutos(), getMinutosExtra(), observacao, 
					 funcionarios != null ? funcionarios.getId() : null, 
											 atividade != null ? atividade.getId() : null, 
													 ordemServico != null ? ordemServico.getId() : null);
			 
			 return apontamentoDto;
		 }

	
}
