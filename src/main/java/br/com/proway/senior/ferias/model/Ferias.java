package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.proway.senior.ferias.controller.IFerias;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

@Entity
public class Ferias implements IFerias {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long idColaborador;
	private Long idGestor;
	private Long idRequerimento;
	@Enumerated(EnumType.STRING)
	private EstadoFerias estado;

	private LocalDate dataInicio;
	private LocalDate dataFim;

	private int diasRequisitados;
	private int diasVendidos;

	@Enumerated(EnumType.STRING)
	private TiposFerias tipoFerias;

	public Ferias() {
	}

	/**
	 * @param id
	 * @param idColaborador
	 * @param idGestor
	 * @param idRequerimento
	 * @param usufruido
	 * @param dataInicio
	 * @param dataFim
	 * @param diasRequisitados
	 * @param diasVendidos
	 * @param tipoFerias
	 */
	public Ferias(Long idColaborador, Long idGestor, Long idRequerimento, EstadoFerias estado,
			LocalDate dataInicio, LocalDate dataFim, int diasRequisitados, int diasVendidos, TiposFerias tipoFerias) {
		this.idColaborador = idColaborador;
		this.idGestor = idGestor;
		this.idRequerimento = idRequerimento;
		this.estado = estado;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.diasRequisitados = diasRequisitados;
		this.diasVendidos = diasVendidos;
		this.tipoFerias = tipoFerias;
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

	public Long getIdRequerimento() {
		return idRequerimento;
	}

	public void setIdRequerimento(Long idRequerimento) {
		this.idRequerimento = idRequerimento;
	}

	public EstadoFerias getEstado() {
		return estado;
	}

	public void setEstado(EstadoFerias estado) {
		this.estado = estado;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
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
		return tipoFerias;
	}

	public void setTipoFerias(TiposFerias tipoFerias) {
		this.tipoFerias = tipoFerias;
	}
	
	public Long getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(Long idGestor) {
		this.idGestor = idGestor;
	}

}