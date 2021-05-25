package br.com.proway.senior.ferias.model.dto;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

public class FeriasDTO {

	private int idColaborador;
	private int idGestor;
	private int idRequerimento;
	private String estado;
	private String dataInicio;
	private String dataFim;
	private int diasRequisitados;
	private int diasVendidos;
	private String tipoFerias;
	
	public int getIdColaborador() {
		return idColaborador;
	}
	public void setIdColaborador(int idColaborador) {
		this.idColaborador = idColaborador;
	}
	public int getIdGestor() {
		return idGestor;
	}
	public void setIdGestor(int idGestor) {
		this.idGestor = idGestor;
	}
	public int getIdRequerimento() {
		return idRequerimento;
	}
	public void setIdRequerimento(int idRequerimento) {
		this.idRequerimento = idRequerimento;
	}
	public EstadoFerias getEstadoConverted() {
		return EstadoFerias.valueOf(this.estado);
	}
	public void setEstado(EstadoFerias estado) {
		this.estado = estado.toString();
	}
	public LocalDate getDataInicioConverted() {
		return LocalDate.parse(this.dataInicio);
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio.toString();
	}
	public LocalDate getDataFimConverted() {
		return LocalDate.parse(this.dataFim);
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim.toString();
	}
	public int getDiasRequisitados() {
		return diasRequisitados;
	}
	public void setDiasRequisitados(int diasRequisitados) {
		this.diasRequisitados = diasRequisitados;
	}
	public int getDiasVendidos() {
		return diasVendidos;
	}
	public void setDiasVendidos(int diasVendidos) {
		this.diasVendidos = diasVendidos;
	}
	public TiposFerias getTipoFerias() {
		return TiposFerias.valueOf(tipoFerias);
	}
	public void setTipoFerias(TiposFerias tipoFerias) {
		this.tipoFerias = tipoFerias.toString();
	}
		
}