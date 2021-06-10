package br.com.proway.senior.ferias.model.dto;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.enums.EstadoFerias;

public class FeriasDTO {

	private int id;
	private long idColaborador;
	private String estado;
	private String dataInicio;
	private String dataFim;
	private int dias;
	private int diasVendidos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public EstadoFerias getEstado() {
		return EstadoFerias.valueOf(this.estado);
	}

	public void setEstado(EstadoFerias estado) {
		this.estado = estado.toString();
	}

	public LocalDate getDataInicio() {
		return LocalDate.parse(this.dataInicio);
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio.toString();
	}

	public LocalDate getDataFim() {
		return LocalDate.parse(this.dataFim);
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim.toString();
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int diasRequisitados) {
		this.dias = diasRequisitados;
	}

	public int getDiasVendidos() {
		return diasVendidos;
	}

	public void setDiasVendidos(int diasVendidos) {
		this.diasVendidos = diasVendidos;
	}

}