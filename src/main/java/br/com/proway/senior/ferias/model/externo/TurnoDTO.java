package br.com.proway.senior.ferias.model.externo;

import java.util.List;

public class TurnoDTO {
	private Long id;
	private String horaInicio;
	private String horaFim;
	private String nomeTurno;
	private Long minutosTrabalho;
	List<Long> pessoasNoTurno;
	
	
	public Long getId() {
		return id;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public String getHoraFim() {
		return horaFim;
	}
	public String getNomeTurno() {
		return nomeTurno;
	}
	public Long getMinutosTrabalho() {
		return minutosTrabalho;
	}
	public List<Long> getPessoasNoTurno() {
		return pessoasNoTurno;
	}
}