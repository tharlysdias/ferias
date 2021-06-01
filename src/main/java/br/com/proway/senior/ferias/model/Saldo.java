package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @see {@link Long} id
 * @see {@link Long} idColaborador
 * @see int diasDisponiveisDeFerias
 * @see {@link LocalDate} dataAdmissao
 * 
 * @author Leonardo F Silva <felipeleao217@gmail.com>
 * 
 */
@Entity
public class Saldo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;

	@Column(unique = true)
	private Long idColaborador;
	
	private int jornadas;
	private Double diasDisponiveisDeFerias;
	private LocalDate dataAdmissao;

	public Saldo() {}
	
	public Saldo(Long idColaborador, Double diasDisponiveisDeFerias, LocalDate dataAdmissao) {
		super();
		this.idColaborador = idColaborador;
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
		this.dataAdmissao = dataAdmissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public int getJornadas() {
		return this.jornadas;
	}

	public void setJornadas(int jornadasAnterior) {
		this.jornadas = jornadasAnterior;
	}

	public Double getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}

	public void setDiasDisponiveisDeFerias(Double diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
		result = prime * result + ((diasDisponiveisDeFerias == null) ? 0 : diasDisponiveisDeFerias.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idColaborador == null) ? 0 : idColaborador.hashCode());
		result = prime * result + jornadas;
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
		Saldo other = (Saldo) obj;
		if (dataAdmissao == null) {
			if (other.dataAdmissao != null)
				return false;
		} else if (!dataAdmissao.equals(other.dataAdmissao))
			return false;
		if (diasDisponiveisDeFerias == null) {
			if (other.diasDisponiveisDeFerias != null)
				return false;
		} else if (!diasDisponiveisDeFerias.equals(other.diasDisponiveisDeFerias))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idColaborador == null) {
			if (other.idColaborador != null)
				return false;
		} else if (!idColaborador.equals(other.idColaborador))
			return false;
		if (jornadas != other.jornadas)
			return false;
		return true;
	}
}