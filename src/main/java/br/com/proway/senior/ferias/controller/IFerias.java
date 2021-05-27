package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;

public interface IFerias {

	public Long getId();

	public Requerimento getRequerimento();

	public EstadoFerias getEstado();

	public LocalDate getDataInicio();

	public LocalDate getDataFim();

	public int getDias();

	public int getDiasVendidos();

}
