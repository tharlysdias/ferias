package br.com.proway.senior.ferias.model.dto;

public class SaldoDTO {
	private Long idColaborador;
	private Long idGestor;
	private int diasDisponiveisDeFerias;
	
	public SaldoDTO() {}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public Long getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(Long idGestor) {
		this.idGestor = idGestor;
	}

	public int getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}

	public void setDiasDisponiveisDeFerias(int diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}
}