package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

@Entity
public class Requerimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int idColaborador;
	private int idGestor;

	private LocalDate dataAbertura;
	private LocalDate prazoAnalise;
	private LocalDate dataFechamento;

	@Enumerated(EnumType.STRING)
	private EstadosRequerimento estado;

	private String mensagem;
	private String resposta;

	private Integer diasRequisitados;
	private Integer diasVendidos;
	private Integer diasFracionados;

	private LocalDate dataInicioFeriasRequisitadas;
	private LocalDate dataFimFeriasRequisitadas;

	private boolean vendeuORestante;

	@Enumerated(EnumType.STRING)
	private TiposFerias tipoFerias;
	
	public Requerimento() {}

}
