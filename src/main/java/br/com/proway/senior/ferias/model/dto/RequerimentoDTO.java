package br.com.proway.senior.ferias.model.dto;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * DTO do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse	<guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias			<tharlys.dias@senior.com.br>
 */

public class RequerimentoDTO {
	
	private Long idColaborador;
	private Long idGestor;
	private LocalDate dataAbertura;
	private LocalDate prazoAnalise;
	private LocalDate dataFechamento;
	private EstadosRequerimento estado;
	private String mensagem;
	private String resposta;
	private Integer diasRequisitados;
	private Integer diasVendidos;
	private Integer diasFracionados;
	private LocalDate dataInicioFeriasRequisitadas;
	private LocalDate dataFimFeriasRequisitadas;
	private boolean vendeuORestante;

	public RequerimentoDTO() {}
	
	
	public RequerimentoDTO(Requerimento requerimento) {
		this.idColaborador = requerimento.getIdColaborador();
		this.idGestor = requerimento.getIdGestor();
		this.dataAbertura = requerimento.getDataAbertura();
		this.prazoAnalise = requerimento.getPrazoAnalise();
		this.dataFechamento = requerimento.getDataFechamento();
		this.estado = requerimento.getEstado();
		this.mensagem = requerimento.getMensagem();
		this.resposta = requerimento.getResposta();
		this.diasRequisitados = requerimento.getDiasRequisitados();
		this.diasVendidos = requerimento.getDiasVendidos();
		this.diasFracionados = requerimento.getDiasFracionados();
		this.dataInicioFeriasRequisitadas = requerimento.getDataInicioFeriasRequisitadas();
		this.dataFimFeriasRequisitadas = requerimento.getDataFimFeriasRequisitadas();
		this.vendeuORestante = requerimento.isVendeuORestante();
	}
	
	public Long getIdColaborador() {
		return idColaborador;
	}

	public Long getIdGestor() {
		return idGestor;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public LocalDate getPrazoAnalise() {
		return prazoAnalise;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public EstadosRequerimento getEstado() {
		return estado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getResposta() {
		return resposta;
	}

	public Integer getDiasRequisitados() {
		return diasRequisitados;
	}

	public Integer getDiasVendidos() {
		return diasVendidos;
	}

	public Integer getDiasFracionados() {
		return diasFracionados;
	}

	public LocalDate getDataInicioFeriasRequisitadas() {
		return dataInicioFeriasRequisitadas;
	}

	public LocalDate getDataFimFeriasRequisitadas() {
		return dataFimFeriasRequisitadas;
	}

	public boolean isVendeuORestante() {
		return vendeuORestante;
	}
		
}
