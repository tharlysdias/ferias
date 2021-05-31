package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * Controller do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse <guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias <tharlys.dias@senior.com.br>
 */
@Service
public class RequerimentoService {

	@Autowired
    private FeriasService controllerFerias;
	
	private RequerimentoRepository repositoryRequerimento;
	private SaldoRepository repositorySaldo;

	@Autowired
	public RequerimentoService(RequerimentoRepository repositoryRequerimento, SaldoRepository repositorySaldo) {
		this.repositoryRequerimento = repositoryRequerimento;
		this.repositorySaldo = repositorySaldo;
	}

	public ArrayList<Requerimento> buscarTodosRequerimentos() {
		return (ArrayList<Requerimento>) this.repositoryRequerimento.findAll();
	}

	public Requerimento buscarRequerimentoPorId(Long id) {
		Optional<Requerimento> obj = this.repositoryRequerimento.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}

	/**
	 * Busca um {@link Requerimento} no banco de dados pelo id do gestor.
	 * 
	 * @param requerimento
	 * @return
	 */
	public ArrayList<Requerimento> buscarRequerimentoPorIdGestor(Long idGestor) {
		return (ArrayList<Requerimento>) this.repositoryRequerimento.findAllByIdGestor(idGestor);
	}

	/**
	 * Busca um {@link Requerimento} no banco de dados pelo id do colaborador.
	 * 
	 * @param requerimento
	 * @return
	 */
	public ArrayList<Requerimento> buscarRequerimentoPorIdColaborador(Long idColaborador) {
		Saldo saldo = repositorySaldo.findByIdColaborador(idColaborador);
		ArrayList<Requerimento> obj = (ArrayList<Requerimento>) this.repositoryRequerimento.findBySaldo(saldo);
		return obj;
	}

	public ArrayList<Requerimento> buscarRequerimentoPorIdColaboradorEEstado(Long idColaborador,
			EstadosRequerimento estado) {
		Saldo saldo = repositorySaldo.findByIdColaborador(idColaborador);
		ArrayList<Requerimento> obj = (ArrayList<Requerimento>) this.repositoryRequerimento
				.findAllByEstadoAndSaldo(estado, saldo);
		return obj;
	}

	/**
	 * Cria o objeto {@link Requerimento}
	 * 
	 * @param requerimento
	 * @return
	 */
	public Requerimento criarRequerimento(Requerimento requerimento) {
		return this.repositoryRequerimento.save(requerimento);
	}

	/**
	 * Possibilita a avaliacao de um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */
	public Requerimento avaliarRequerimento(Long idRequerimento, EstadosRequerimento estado) {
		Optional<Requerimento> obj = this.repositoryRequerimento.findById(idRequerimento);
		System.out.println("3.1");
		if (obj.isPresent() && obj.get().getEstado().equals(EstadosRequerimento.PENDENTE)) {
			System.out.println("3.2");
			if (estado.equals(EstadosRequerimento.APROVADO)) {
				System.out.println("3.3");
				aprovarRequerimento(obj.get());
			} else {
				System.out.println("3.4");
				recusarRequerimento(obj.get());
			}
			System.out.println("3.5");
			obj.get().setDataFechamento(LocalDate.now());
			System.out.println("3.6");
			repositoryRequerimento.save(obj.get());
			System.out.println("3.7");
			return obj.get();
		}
		return null;
	}

	/**
	 * Aprova um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */
	private Ferias aprovarRequerimento(Requerimento requerimento) {
		System.out.println("3.3.1");
		requerimento.setEstado(EstadosRequerimento.APROVADO);
		System.out.println("3.3.2");
		atualizarRequerimento(requerimento);
		System.out.println("3.3.3");
		Requerimento r = repositoryRequerimento.getById(requerimento.getId());
		Ferias f = controllerFerias.criarFerias(r);
		System.out.println("3.3.4");		
		return f;
	}

	/**
	 * Recusa um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */
	private Requerimento recusarRequerimento(Requerimento requerimento) {
		requerimento.setEstado(EstadosRequerimento.RECUSADO);
		return (requerimento);
	}

	/**
	 * Altera um {@link Requerimento} no banco de dados
	 * 
	 * @param requerimento
	 * @return
	 */
	public Requerimento atualizarRequerimento(Requerimento requerimento) {
		Optional<Requerimento> op = this.repositoryRequerimento.findById(requerimento.getId());
		if (op.get().getId().equals(requerimento.getId())) {
			return this.repositoryRequerimento.save(requerimento);
		} else {
			return null;
		}
	}

	/**
	 * Desativa um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */
	public void desativarRequerimento(Long id) {
		Optional<Requerimento> obj = this.repositoryRequerimento.findById(id);
		if (obj.get().getEstado().equals(EstadosRequerimento.PENDENTE)) {
			this.repositoryRequerimento.delete(obj.get());
		}
	}

	/**
	 * Deleta um {@link Requerimento} no banco de dados
	 * 
	 * @param requerimento
	 * @return
	 */
	public void deletar(Long id) {
		this.repositoryRequerimento.deleteById(id);
	}

}