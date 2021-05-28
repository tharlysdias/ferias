package br.com.proway.senior.ferias.model.externo;

import java.util.List;

public class JornadaDTO {
	private Long id;
	private String data;
	private TurnoDTO turno;
	public List<Ponto> listaPonto;
	private Long idPessoa;

	// Auxiliares proprias do DTO
	public short dia;
	public short mes;
	public short ano;

	public long minutosTrabalhados;

	public String estado;

	public JornadaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public TurnoDTO getTurno() {
		return turno;
	}

	public List<Ponto> getListaPonto() {
		return listaPonto;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public short getDia() {
		return dia;
	}

	public short getMes() {
		return mes;
	}

	public short getAno() {
		return ano;
	}

	public long getMinutosTrabalhados() {
		return minutosTrabalhados;
	}

	public String getEstado() {
		return estado;
	}
}