package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

public interface IRequerimento {
	
	public Long getId();

	public Saldo getSaldo();

	public Long getIdGestor();

	public LocalDate getDataAbertura();

	public LocalDate getDataFechamento();

	public LocalDate getPrazoAnalise();

	public EstadosRequerimento getEstado();

	public String getMensagem();

	public String getResposta();

	public Integer getDiasRequisitados();

	public Integer getDiasVendidos();

	public LocalDate getDataInicioFerias();
	
}