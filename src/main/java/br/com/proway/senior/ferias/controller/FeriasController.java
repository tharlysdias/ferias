package br.com.proway.senior.ferias.controller;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.FeriasRepository;
import br.com.proway.senior.ferias.model.Requerimento;

public class FeriasController {

	private final FeriasRepository repository;

	public FeriasController(FeriasRepository repository) {
		this.repository = repository;
	}

	/**
	 * Cria o objeto {@link Ferias}
	 * 
	 * A partir de um requerimento, faz as validacoes das informacoes e se passarem
	 * cria um objeto {@link Ferias} e desconta o saldo de férias do usuário.
	 * 
	 * @param requerimento {@link Requerimento}.
	 * @return Ferias
	 */
	public Ferias criarFerias(IRequerimento requerimento) {
		boolean usufruido = false;
		Ferias ferias = new Ferias(requerimento.getIdColaborador(), requerimento.getId(), usufruido,
				requerimento.getDataInicioFeriasRequisitadas(), requerimento.getDataFimFeriasRequisitadas(),
				requerimento.getDiasRequisitados(), requerimento.getDiasVendidos(), requerimento.getTipoFerias());
		return repository.save(ferias);
	}
	

	
	

}