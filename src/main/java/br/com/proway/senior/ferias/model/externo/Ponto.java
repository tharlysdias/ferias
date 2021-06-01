package br.com.proway.senior.ferias.model.externo;

public class Ponto {
	private Long id;
	private String momento;
	private Long idPessoa;
	
	public Ponto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getMomento() {
		return momento;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}
}