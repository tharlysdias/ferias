package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import br.com.proway.senior.ferias.controller.IFerias;
import br.com.proway.senior.ferias.controller.IRequerimento;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.utils.Data;

@Entity
public class Ferias implements IFerias {

	@Id
	private Long id;

	/**
	 * {@link MapsId}
	 * https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "id")
	private Requerimento requerimento;

	@Enumerated(EnumType.STRING)
	private EstadoFerias estado;

	private LocalDate dataInicio;
	private LocalDate dataFim;

	private int dias;
	private int diasVendidos;

	public Ferias() {
	}

	/**
	 * Construtor que recebe {@link IRequerimento}.
	 * 
	 * @param requerimento
	 */
	public Ferias(IRequerimento requerimento) {
		this.requerimento = (Requerimento) requerimento;
		this.dataInicio = requerimento.getDataInicioFerias();
		this.dias = requerimento.getDiasRequisitados();
		this.diasVendidos = requerimento.getDiasVendidos();
		this.dataFim = Data.calcularDataFim(dataInicio, dias);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Requerimento getRequerimento() {
		return requerimento;
	}

	public void setRequerimento(Requerimento requerimento) {
		this.requerimento = requerimento;
	}

	public EstadoFerias getEstado() {
		return estado;
	}

	public void setEstado(EstadoFerias estado) {
		this.estado = estado;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getDiasVendidos() {
		return diasVendidos;
	}

	public void setDiasVendidos(int diasVendidos) {
		this.diasVendidos = diasVendidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + dias;
		result = prime * result + diasVendidos;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((requerimento == null) ? 0 : requerimento.hashCode());
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
		Ferias other = (Ferias) obj;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (dias != other.dias)
			return false;
		if (diasVendidos != other.diasVendidos)
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (requerimento == null) {
			if (other.requerimento != null)
				return false;
		} else if (!requerimento.equals(other.requerimento))
			return false;
		return true;
	}

}