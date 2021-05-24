package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.proway.senior.ferias.model.enums.TiposFerias;

@Entity
public class Ferias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int idColaborador;
	private int idRequerimento;
	private boolean usufruido;

	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	private int diasRequisitados;
	private int diasVendidos;
	
	@Enumerated(EnumType.STRING)
	private TiposFerias tipoFerias;
}
