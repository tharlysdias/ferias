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
	private Long id;
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
	
	public Requerimento(int idColaborador, int idGestor, LocalDate dataAbertura, LocalDate prazoAnalise,
			LocalDate dataFechamento, EstadosRequerimento estado, String mensagem, String resposta,
			Integer diasRequisitados, Integer diasVendidos, Integer diasFracionados,
			LocalDate dataInicioFeriasRequisitadas, LocalDate dataFimFeriasRequisitadas, boolean vendeuORestante,
			TiposFerias tipoFerias) {
		this.idColaborador = idColaborador;
		this.idGestor = idGestor;
		this.dataAbertura = dataAbertura;
		this.prazoAnalise = prazoAnalise;
		this.dataFechamento = dataFechamento;
		this.estado = estado;
		this.mensagem = mensagem;
		this.resposta = resposta;
		this.diasRequisitados = diasRequisitados;
		this.diasVendidos = diasVendidos;
		this.diasFracionados = diasFracionados;
		this.dataInicioFeriasRequisitadas = dataInicioFeriasRequisitadas;
		this.dataFimFeriasRequisitadas = dataFimFeriasRequisitadas;
		this.vendeuORestante = vendeuORestante;
		this.tipoFerias = tipoFerias;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getPrazoAnalise() {
		return prazoAnalise;
	}

	public void setPrazoAnalise(LocalDate prazoAnalise) {
		this.prazoAnalise = prazoAnalise;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public EstadosRequerimento getEstado() {
		return estado;
	}

	public void setEstado(EstadosRequerimento estado) {
		this.estado = estado;
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

	public Integer getDiasFracionados() {
		return diasFracionados;
	}

	public void setDiasFracionados(Integer diasFracionados) {
		this.diasFracionados = diasFracionados;
	}

	public LocalDate getDataInicioFeriasRequisitadas() {
		return dataInicioFeriasRequisitadas;
	}

	public void setDataInicioFeriasRequisitadas(LocalDate dataInicioFeriasRequisitadas) {
		this.dataInicioFeriasRequisitadas = dataInicioFeriasRequisitadas;
	}

	public LocalDate getDataFimFeriasRequisitadas() {
		return dataFimFeriasRequisitadas;
	}

	public void setDataFimFeriasRequisitadas(LocalDate dataFimFeriasRequisitadas) {
		this.dataFimFeriasRequisitadas = dataFimFeriasRequisitadas;
	}

	public boolean isVendeuORestante() {
		return vendeuORestante;
	}

	public void setVendeuORestante(boolean vendeuORestante) {
		this.vendeuORestante = vendeuORestante;
	}

	public TiposFerias getTipoFerias() {
		return tipoFerias;
	}

	public void setTipoFerias(TiposFerias tipoFerias) {
		this.tipoFerias = tipoFerias;
	}
			
	
}
