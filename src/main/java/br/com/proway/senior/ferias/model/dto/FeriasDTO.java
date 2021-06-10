package br.com.proway.senior.ferias.model.dto;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;

public class FeriasDTO {

	private Long id;
	private Long idRequerimento;
	private String estado;
	private String dataInicio;
	private String dataFim;
	private int dias;
	private int diasVendidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdRequerimento(Requerimento requerimento) {
		this.idRequerimento = requerimento.getId();
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