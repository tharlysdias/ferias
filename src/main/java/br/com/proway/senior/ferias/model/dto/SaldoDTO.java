package br.com.proway.senior.ferias.model.dto;

import br.com.proway.senior.ferias.model.Saldo;

public class SaldoDTO {
	private Long idColaborador;
	private Long idGestor;
	private int diasDisponiveisDeFerias;
	
	public SaldoDTO(Saldo saldo) {
		this.idColaborador = saldo.getIdColaborador();
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

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public void setIdGestor(Long idGestor) {
		this.idGestor = idGestor;
	}

	public void setDiasDisponiveisDeFerias(int diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}
}