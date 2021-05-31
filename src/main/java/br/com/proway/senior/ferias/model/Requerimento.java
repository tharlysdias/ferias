package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.proway.senior.ferias.controller.IRequerimento;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * Classe {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse <guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias <tharlys.dias@senior.com.br>
 */

@Entity
public class Requerimento implements IRequerimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	/**
	 * ID do {@link Saldo}.
	 */
	@ManyToOne(targetEntity = Saldo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_saldo")
	private Saldo saldo;

	private Long idGestor;

	private LocalDate dataAbertura;
	private LocalDate dataFechamento;
	private LocalDate prazoAnalise;

	@Enumerated(EnumType.STRING)
	private EstadosRequerimento estado;

	private String mensagem;
	private String resposta;

	private Integer diasRequisitados;
	private Integer diasVendidos;

	private LocalDate dataInicioFerias;

	@OneToOne(mappedBy = "requerimento")
	private Ferias ferias;

	public Requerimento() {
	}

	public Requerimento(Saldo saldo, Long idGestor, LocalDate dataAbertura, LocalDate prazoAnalise, String mensagem,
			String resposta, Integer diasRequisitados, Integer diasVendidos, LocalDate dataInicioFerias) {
		this.saldo = saldo;
		this.idGestor = idGestor;
		this.dataAbertura = dataAbertura;
		this.prazoAnalise = prazoAnalise;
		this.estado = EstadosRequerimento.PENDENTE;
		this.mensagem = mensagem;
		this.resposta = resposta;
		this.diasRequisitados = diasRequisitados;
		this.diasVendidos = diasVendidos;
		this.dataInicioFerias = dataInicioFerias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Saldo getSaldo() {
		return saldo;
	}

	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}

	public Long getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(Long idGestor) {
		this.idGestor = idGestor;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public LocalDate getPrazoAnalise() {
		return prazoAnalise;
	}

	public void setPrazoAnalise(LocalDate prazoAnalise) {
		this.prazoAnalise = prazoAnalise;
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

	public LocalDate getDataInicioFerias() {
		return dataInicioFerias;
	}

	public void setDataInicioFerias(LocalDate dataInicioFerias) {
		this.dataInicioFerias = dataInicioFerias;
	}

	public Ferias getFerias() {
		return ferias;
	}

	public void setFerias(Ferias ferias) {
		this.ferias = ferias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAbertura == null) ? 0 : dataAbertura.hashCode());
		result = prime * result + ((dataFechamento == null) ? 0 : dataFechamento.hashCode());
		result = prime * result + ((dataInicioFerias == null) ? 0 : dataInicioFerias.hashCode());
		result = prime * result + ((diasRequisitados == null) ? 0 : diasRequisitados.hashCode());
		result = prime * result + ((diasVendidos == null) ? 0 : diasVendidos.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((ferias == null) ? 0 : ferias.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idGestor == null) ? 0 : idGestor.hashCode());
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((prazoAnalise == null) ? 0 : prazoAnalise.hashCode());
		result = prime * result + ((resposta == null) ? 0 : resposta.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requerimento other = (Requerimento) obj;
		if (dataAbertura == null) {
			if (other.dataAbertura != null)
				return false;
		} else if (!dataAbertura.equals(other.dataAbertura))
			return false;
		if (dataFechamento == null) {
			if (other.dataFechamento != null)
				return false;
		} else if (!dataFechamento.equals(other.dataFechamento))
			return false;
		if (dataInicioFerias == null) {
			if (other.dataInicioFerias != null)
				return false;
		} else if (!dataInicioFerias.equals(other.dataInicioFerias))
			return false;
		if (diasRequisitados == null) {
			if (other.diasRequisitados != null)
				return false;
		} else if (!diasRequisitados.equals(other.diasRequisitados))
			return false;
		if (diasVendidos == null) {
			if (other.diasVendidos != null)
				return false;
		} else if (!diasVendidos.equals(other.diasVendidos))
			return false;
		if (estado != other.estado)
			return false;
		if (ferias == null) {
			if (other.ferias != null)
				return false;
		} else if (!ferias.equals(other.ferias))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idGestor == null) {
			if (other.idGestor != null)
				return false;
		} else if (!idGestor.equals(other.idGestor))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (prazoAnalise == null) {
			if (other.prazoAnalise != null)
				return false;
		} else if (!prazoAnalise.equals(other.prazoAnalise))
			return false;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requerimento [id=" + id + ", saldo=" + saldo + ", idGestor=" + idGestor + ", dataAbertura="
				+ dataAbertura + ", dataFechamento=" + dataFechamento + ", prazoAnalise=" + prazoAnalise + ", estado="
				+ estado + ", mensagem=" + mensagem + ", resposta=" + resposta + ", diasRequisitados="
				+ diasRequisitados + ", diasVendidos=" + diasVendidos + ", dataInicioFerias=" + dataInicioFerias
				+ ", ferias=" + ferias + "]";
	}

}