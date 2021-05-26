package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * Controller do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse	<guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias			<tharlys.dias@senior.com.br>
 */

@Service
public class RequerimentoController {

	@Autowired
	private RequerimentoRepository repository;

	@Autowired
    private FeriasController controllerFerias;
		
		
	public ArrayList<Requerimento> buscarTodosRequerimentos() {
		return (ArrayList<Requerimento>) this.repository.findAll();
	}

	public Requerimento buscarRequerimentoPorId(Long id) {
		Optional<Requerimento> obj = this.repository.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}

	/**
	 * Cria o objeto {@link Requerimento}
	 * 
	 * @param requerimento
	 * @return
	 */
	public Requerimento criarRequerimento(Requerimento requerimento) {
		return this.repository.save(requerimento);
	}
	
	/**
	 * Altera um {@link Requerimento} no banco de dados
	 * 
	 * @param requerimento
	 * @return
	 */	
	public Requerimento atualizarRequerimento(Requerimento requerimento) {
		Optional<Requerimento> op = this.repository.findById(requerimento.getId());
		if (op.get().getId().equals(requerimento.getId())) {
			return this.repository.save(requerimento);
		} else {
			return null;
		}
	}

	/**
	 * Deleta um {@link Requerimento} no banco de dados
	 * 
	 * @param requerimento
	 * @return
	 */
	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	
	/**
	 * Aprova um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */	
	private Ferias aprovarRequerimento(Requerimento requerimento) {
		requerimento.setEstado(EstadosRequerimento.APROVADO);
		repository.save(requerimento);
		return controllerFerias.criarFerias(requerimento);
	}

	/**
	 * Recusa um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */
	private Requerimento recusarRequerimento(Requerimento requerimento) {
		requerimento.setEstado(EstadosRequerimento.RECUSADO);
		return repository.save(requerimento);
	}

	/**
	 * Possibilita a avaliacao de um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */
	public Requerimento avaliarRequerimento(Long id, EstadosRequerimento estado) {
		Optional<Requerimento> obj = this.repository.findById(id);
		if (obj.isPresent() && obj.get().getEstado().equals(EstadosRequerimento.PENDENTE)) {
			if (estado.equals(EstadosRequerimento.APROVADO)) {
				aprovarRequerimento(obj.get());
			} else {
				recusarRequerimento(obj.get());
			}
			return obj.get();
		} return null;
	}

	/**
	 * Desativa um {@link Requerimento} em estado de pendencia.
	 * 
	 * @param requerimento
	 * @return
	 */	
	public void desativarRequerimento(Requerimento requerimento) {
		Optional<Requerimento> obj = this.repository.findById(requerimento.getId());
		if (obj.get().getEstado().equals(EstadosRequerimento.PENDENTE)) {
			this.repository.delete(requerimento);
		}

	}

	/**
	 * Busca um {@link Requerimento} no banco de dados pelo id do gestor.
	 * 
	 * @param requerimento
	 * @return
	 */	
	public ArrayList<Requerimento> buscarRequerimentoPorIdGestor(Long idGestor) {
		return (ArrayList<Requerimento>) this.repository.findAllByIdGestor(idGestor);
	}

	/**
	 * Busca um {@link Requerimento} no banco de dados pelo id do colaborador.
	 * 
	 * @param requerimento
	 * @return
	 */	
	public Requerimento buscarRequerimentoPorIdColaborador(Long idColaborador) {
		Optional<Requerimento> obj = this.repository.findById(idColaborador);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}
}