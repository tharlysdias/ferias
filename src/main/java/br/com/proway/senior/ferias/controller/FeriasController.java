package br.com.proway.senior.ferias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.FeriasRepository;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;

/**
 * Controller da {@link Ferias}
 * 
 * @author David Hildebrandt <davihildebran@gmail.com>
 * @author Lucas Grijo <rksgrijo@gmail.com>
 */
@Controller
public class FeriasController {

	@Autowired
	private FeriasRepository repository;

	public FeriasController() {	}
	
	public FeriasRepository getRepository() {
		return repository;
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
		EstadoFerias estado = EstadoFerias.NAO_USUFRUIDA;
		Ferias ferias = new Ferias(requerimento.getIdColaborador(), requerimento.getIdGestor(), 
				requerimento.getId(), estado, requerimento.getDataInicioFeriasRequisitadas(), 
				requerimento.getDataFimFeriasRequisitadas(), requerimento.getDiasRequisitados(), 
				requerimento.getDiasVendidos(), requerimento.getTipoFerias());
		return repository.save(ferias);
	}
	
	/**
	 * Busca Ferias por id
	 * 
	 * Busca no banco as ferias com o id igual ao passado como parametro.
	 * 
	 * @param id das ferias
	 * @return ferias
	 * @throws Exception 
	 */
	public Ferias buscarPorIdFerias(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("Nao existe uma ferias com esse id."));
	}
	
	/**
	 * Buscar {@link Ferias}
	 * 
	 * Busca uma lista com todas as ferias dos colaboradores sob gerencia do gestor com o id passado
	 * como parametro e que nao tenham sido usufruidas. (Estado = NAO_USUFRUIDA).
	 *  
	 * @param id
	 * @return Lista de ferias
	 */
	public List<Ferias> buscarPorIdGestorENaoUsufruidas(Long id){
		 return repository.findByIdGestorAndEstado(id, EstadoFerias.NAO_USUFRUIDA);
	}
	
	/**
	 * Busca {@link Ferias}
	 * 
	 * Busca todas as férias com o id do colaborador igual ao passado como parametro.
	 * 
	 * @param id
	 * @return lista de Ferias.
	 */
	public List<Ferias> buscarPorIdColaborador(Long id){
		return repository.findAllByIdColaborador(id);
	}
	
	/**
	 * Busca {@link Ferias} do colaborador.
	 * 
	 * Busca as ferias anda nao usufruidas do colaborador.
	 * 
	 * @param id do colaborador
	 * @return ferias do colaborador
	 */
	public Ferias buscarPorIdColaboradorENaoUsufruidas(Long id){
		return repository.findByIdColaboradorAndEstado(id, EstadoFerias.NAO_USUFRUIDA);
	}
	
	/**
	 * Busca todas as {@link Ferias} do banco.
	 * 
	 * @return lista de Ferias.
	 */
	public List<Ferias> buscarTodasFerias(){
		return repository.findAll();
	}
	
	/**
	 * Altera o Estado da {@link Ferias}.
	 * 
	 * Altera o estado da Ferias com o id igual ao passado como parametro para o estado passado como
	 * parametro
	 * 
	 * @param id Da Ferias
	 * @param estado A ser alterado
	 * @return Ferias alterada
	 * @throws Exception .Ferias nao encontradas
	 */
	public Ferias alterarEstadoFerias(Long id, EstadoFerias estado) throws Exception {
		return repository.findById(id).map(ferias -> {
			ferias.setEstado(estado);
			return repository.save(ferias);
		}).orElseThrow(() -> new Exception("Ferias nao encontradas"));
	}
	
	/**
	 * Altera uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * Metodo para alterar as datas da Ferias.
	 * 
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */	
	public Ferias alterarDataFerias(Long id, Ferias novaFerias) throws Exception {
		return repository.findById(id).map(ferias -> {
			ferias.setDataInicio(novaFerias.getDataInicio());
			ferias.setDataFim(novaFerias.getDataFim());
			ferias.setDiasRequisitados(novaFerias.getDiasRequisitados());
			ferias.setDiasVendidos(novaFerias.getDiasVendidos());
			return repository.save(ferias);
		}).orElseThrow(() -> new Exception("Ferias nao encontradas"));
	}
	
	/**
	 * Deleta {@link Ferias}.
	 * 
	 * Deleta um objeto Ferias igual ao passado como parametro. 
	 * 
	 * @param ferias
	 */
	public void deletarFeriasPorFerias(Ferias ferias) {
		repository.delete(ferias);
	}
	
	/**
	 * Deleta {@link Ferias}.
	 * 
	 * Deleta um objeto Ferias com o id igual ao passado como parametro. 
	 * 
	 * @param ferias
	 */
	public void deletarFeriasPorId(Long id) {
		repository.deleteById(id);
	}
	
}