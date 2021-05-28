package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.FeriasRepository;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;

/**
 * Controller da {@link Ferias}
 * 
 * @author David Hildebrandt <davihildebran@gmail.com>
 * @author Lucas Grijo <rksgrijo@gmail.com>
 * @version Sprint6
 * 
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @author Lucas Grijo <rksgrijo@gmail.com>
 * @version Sprint7
 */
@Controller
public class FeriasController {

	private FeriasRepository repositoryFerias;

	private RequerimentoRepository repositoryRequerimento;

	private SaldoRepository repositorySaldo;

	@Autowired
	public FeriasController(FeriasRepository repositoryFerias, RequerimentoRepository repositoryRequerimento,
			SaldoRepository repositorySaldo) {
		this.repositoryFerias = repositoryFerias;
		this.repositoryRequerimento = repositoryRequerimento;
		this.repositorySaldo = repositorySaldo;
	}

	/**
	 * Busca {@link Ferias} por id.
	 * 
	 * Busca no banco as ferias com o id igual ao passado como parametro.
	 * 
	 * @param id das ferias
	 * @return ferias
	 * @throws Exception
	 */
	public Ferias buscarPorId(Long id) throws Exception {
		return repositoryFerias.findById(id).orElseThrow(() -> new Exception("Nao existe uma ferias com esse id."));
	}

	/**
	 * Busca todas as {@link Ferias} do banco.
	 * 
	 * @return lista de Ferias.
	 */
	public List<Ferias> buscarTodasFerias() {
		return repositoryFerias.findAll();
	}

	/**
	 * Buscar todos os requerimentos cujo o {@link Saldo} corresponde ao {@link Saldo} do
	 * colaborador procurado.
	 * 
	 * @param idColaborador
	 * @return
	 */
	public ArrayList<Ferias> buscarTodasAsFeriasPorIdColaborador(Long idColaborador) {
		Saldo saldo = repositorySaldo.findByIdColaborador(idColaborador);
		ArrayList<Requerimento> requerimentos = (ArrayList<Requerimento>) repositoryRequerimento
				.findBySaldo(saldo);
		ArrayList<Ferias> ferias = new ArrayList<Ferias>();
		for (Requerimento requerimento : requerimentos) {
			ferias.add(repositoryFerias.findById(requerimento.getId()).get());
		}
		return ferias;
	}

	/**
	 * Buscar {@link Ferias} do colaborador que estão com estado "A_USUFRUIR".
	 * 
	 * @param idColaborador
	 * @return ferias
	 */
	public ArrayList<Ferias> buscarFeriasAUsufruirPorIdColaborador(Long idColaborador) {
		Saldo saldo = repositorySaldo.findByIdColaborador(idColaborador);
		ArrayList<Requerimento> requerimentos = (ArrayList<Requerimento>) repositoryRequerimento
				.findBySaldo(saldo);
		ArrayList<Ferias> ferias = new ArrayList<Ferias>();
		for (Requerimento requerimento : requerimentos) {
			Ferias temp = repositoryFerias.findById(requerimento.getId()).get();
			if (temp.getEstado().equals(EstadoFerias.A_USUFRUIR))
				ferias.add(temp);
		}
		return ferias;
	}

	/**
	 * Buscar todas {@link Ferias} "A_USUFRUIR" de todos subordinados do gestor.
	 * 
	 * @return ferias
	 */
	public ArrayList<Ferias> buscarFeriasAUsufruirDosSubordinados() {
		return null;
	}

	/**
	 * Buscar todas {@link Ferias} "USUFRUINDO" de todos subordinados do gestor.
	 * 
	 * @return ferias
	 */
	public ArrayList<Ferias> buscarFeriasUsufruindoDosSubordinados() {
		return null;
	}

	/**
	 * Metodo que altera uma {@link Ferias}.
	 * 
	 * @param novaferias
	 * @param id
	 * @return ferias
	 * @throws Exception
	 */
	public Ferias alterarFerias(Ferias novaferias, Long id) throws Exception {
		return repositoryFerias.findById(id).map(ferias -> {
			ferias.setEstado(novaferias.getEstado());
			ferias.setDataInicio(novaferias.getDataInicio());
			ferias.setDataFim(novaferias.getDataFim());
			ferias.setDias(novaferias.getDias());
			ferias.setDiasVendidos(novaferias.getDiasVendidos());
			return repositoryFerias.save(ferias);
		}).orElseThrow(() -> new Exception("Ferias nao encontradas"));

	}

	/**
	 * Deletar {@link Ferias}.
	 * 
	 * Deletar um objeto {@link Ferias} com o id igual ao passado como parametro.
	 * 
	 * @param ferias
	 */
	public void deletarFeriasPorId(Long id) {
		repositoryFerias.deleteById(id);
	}

	/**
	 * Cria e salva um objeto {@link Ferias}.
	 * 
	 * Este metodo deve ser chamado assim que um requerimento for aprovado.
	 * 
	 * Uma {@link Ferias} recem criada tem seu estado definido como "A_USUFRUIR".
	 * 
	 * @param requerimento {@link Requerimento}.
	 * @return Ferias
	 */
	public Ferias criarFerias(IRequerimento requerimento) {
		Ferias ferias = new Ferias(requerimento);
		ferias.setEstado(EstadoFerias.A_USUFRUIR);
		return repositoryFerias.save(ferias);
	}
}