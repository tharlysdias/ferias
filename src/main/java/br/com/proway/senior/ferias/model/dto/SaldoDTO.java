package br.com.proway.senior.ferias.model.dto;

import br.com.proway.senior.ferias.model.Saldo;

public class SaldoDTO {
	private Long idColaborador;
	private Long idGestor;
	private int diasDisponiveisDeFerias;
	
	public SaldoDTO() {}
	
	public SaldoDTO(Saldo saldo) {
		this.idColaborador = saldo.getIdColaborador();
		this.idGestor = saldo.getIdGestor();
		this.diasDisponiveisDeFerias = saldo.getDiasDisponiveisDeFerias();
	}

	public Long getIdColaborador() {
		return idColaborador;
	}
	
	public Long getIdGestor() {
		return idGestor;
	}

	public int getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}
}