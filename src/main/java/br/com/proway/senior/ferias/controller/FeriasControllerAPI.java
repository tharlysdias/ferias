package br.com.proway.senior.ferias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.proway.senior.ferias.model.Ferias;

public class FeriasControllerAPI {

	@Autowired
	private FeriasController controller;

	public FeriasControllerAPI() {}

	/**
	 * Consulta uma {@link Ferias} por id.
	 * 
	 * @param id {@link id}
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Nao existe uma ferias com esse id.
	 */
	@GetMapping("/ferias/{id}")
	Ferias consultarFeriasPorId(@PathVariable Long id) throws Exception {
		// TODO adicionar FeriasDTO
		return controller.buscarPorIdFerias(id);
	}

	/**
	 * Consulta as {@link Ferias} por id do colaborador.
	 * 
	 * @param id {@link id}
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Nao existe uma ferias com esse id.
	 */
	@GetMapping("colaborador/{idColaborador}/ferias")
	List<Ferias> consultarFeriasPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		// TODO adicionar FeriasDTO
		return controller.buscarPorIdColaborador(idColaborador);
	}
	
	/**
	 * Consulta as {@link Ferias} ainda nao usufruidas por id do gestor.
	 * 
	 * @param id {@link id}
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Nao existe uma ferias com esse id.
	 */
	@GetMapping("colaborador/{idColaborador}/ferias")
	List<Ferias> consultarFeriasPorIdGestorNaoUsufruidas(@PathVariable Long idColaborador) throws Exception {
		// TODO adicionar FeriasDTO
		return controller.buscarPorIdGestorENaoUsufruidas(idColaborador);
	}
	
	/**
	 * Busca todas as {@link Ferias} do banco.
	 * 
	 * @return List<Ferias>
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@GetMapping("/ferias")
	List<Ferias> buscarTodos() {
		// TODO adicionar FeriasDTO
		return controller.buscarTodasFerias();
	}

	/**
	 * Altera uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Ferias nao encontradas.
	 */
	@PutMapping("/ferias/{id}")
	Ferias alterarFerias(@RequestBody Ferias novaFerias, @PathVariable Long id) throws Exception {
		return controller.alterarDataFerias(id, novaFerias);
	}

	/**
	 * Deleta uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@DeleteMapping("/ferias/{id}")
	void deletarFerias(@PathVariable Long id) {
		controller.deletarFeriasPorId(id);
	}
}