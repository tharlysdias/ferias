package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

public interface IRequerimento {

	public Long getId();

	public Long getIdColaborador();

	public Long getIdGestor();

	public LocalDate getDataAbertura();

	public LocalDate getPrazoAnalise();

	public LocalDate getDataFechamento();

	public EstadosRequerimento getEstado();

	public String getMensagem();

	public String getResposta();

	public Integer getDiasRequisitados();

	public Integer getDiasVendidos();

	public Integer getDiasFracionados();

	public LocalDate getDataInicioFeriasRequisitadas();

	public LocalDate getDataFimFeriasRequisitadas();

	public boolean isVendeuORestante();

	public TiposFerias getTipoFerias();

}