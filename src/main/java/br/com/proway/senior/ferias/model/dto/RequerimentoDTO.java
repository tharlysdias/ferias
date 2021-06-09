package br.com.proway.senior.ferias.model.dto;

import java.time.LocalDate;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * DTO do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse <guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias <tharlys.dias@senior.com.br>
 */

public class RequerimentoDTO {

	private Long idGestor;
	private String dataAbertura;
	private String dataFechamento;
	private String prazoAnalise;
	private String estado;
	private String mensagem;
	private String resposta;
	private Integer diasRequisitados;
	private Integer diasVendidos;
	private String dataInicioFerias;

	public RequerimentoDTO() {
	}

	public Long getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(Long idGestor) {
		this.idGestor = idGestor;
	}

	public LocalDate getDataAbertura() {
		return LocalDate.parse(this.dataAbertura);
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura.toString();
	}

	public LocalDate getDataFechamento() {
		if(this.dataFechamento != "null") {
			return LocalDate.parse(this.dataFechamento);			
		} else {
			return null;
		}
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		if(dataFechamento != null) {
			this.dataFechamento = dataFechamento.toString();			
		} else {
			this.dataFechamento = "null";
		}
	}

	public LocalDate getPrazoAnalise() {
		return LocalDate.parse(this.prazoAnalise);
	}

	public void setPrazoAnalise(LocalDate prazoAnalise) {
		this.prazoAnalise = prazoAnalise.toString();
	}

	public EstadosRequerimento getEstado() {
		return EstadosRequerimento.valueOf(this.estado);
	}

	public void setEstado(EstadosRequerimento estado) {
		this.estado = estado.toString();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Integer getDiasRequisitados() {
		return diasRequisitados;
	}

	public void setDiasRequisitados(Integer diasRequisitados) {
		this.diasRequisitados = diasRequisitados;
	}

	public Integer getDiasVendidos() {
		return diasVendidos;
	}

	public void setDiasVendidos(Integer diasVendidos) {
		this.diasVendidos = diasVendidos;
	}

	public LocalDate getDataInicioFerias() {
		return LocalDate.parse(this.dataInicioFerias);
	}

	public void setDataInicioFerias(LocalDate dataInicioFerias) {
		this.dataInicioFerias = dataInicioFerias.toString();
	}

}