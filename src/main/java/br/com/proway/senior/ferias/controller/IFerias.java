package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

public interface IFerias {

	public Long getId();

	public Long getIdColaborador();

	public Long getIdRequerimento();

	public EstadoFerias getEstado();

	public LocalDate getDataInicio();

	public LocalDate getDataFim();

	public int getDiasRequisitados();

	public int getDiasVendidos();

	public TiposFerias getTipoFerias();

}
