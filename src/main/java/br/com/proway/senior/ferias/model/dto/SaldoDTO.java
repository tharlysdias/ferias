package br.com.proway.senior.ferias.model.dto;

import java.time.LocalDate;

/**
 * @see {@link Long} idColaborador
 * @see {@link int} diasDisponiveisDeFerias
 * 
 * @author Leonardo F Silva <felipeleao217@gmail.com>
 */
public class SaldoDTO {

	private Long id;
	private Long idColaborador;
	private int diasDisponiveisDeFerias;

	public SaldoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public int getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}

	public void setDiasDisponiveisDeFerias(int diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}
}