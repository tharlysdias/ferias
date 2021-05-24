package br.com.proway.senior.ferias.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.FeriasRepository;

public class FeriasControllerAPI {

	private final FeriasRepository repository;

	public FeriasControllerAPI(FeriasRepository repository) {
		this.repository = repository;
	}

	/**
	 * Consulta uma {@link Ferias} por id.
	 * 
	 * @param id {@link id}
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Nao existe uma ferias com esse id.
	 */
	@GetMapping("/ferias/{id}")
	public Ferias consultarFeriasPorId(@PathVariable Long id) throws Exception {

		return repository.findById(id).orElseThrow(() -> new Exception("Nao existe uma ferias com esse id."));
	}

	/**
	 * Busca todas as {@link Ferias} do banco.
	 * 
	 * @return List<Ferias>
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@GetMapping("/ferias")
	List<Ferias> all() {
		return repository.findAll();
	}

	/**
	 * Altera uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@PutMapping("/ferias/{id}")
	Ferias alterarFerias(@RequestBody Ferias novaFerias, @PathVariable Long id) {

		return repository.findById(id).map(ferias -> {
			ferias.setIdColaborador(novaFerias.getIdColaborador());
			ferias.setIdRequerimento(novaFerias.getIdRequerimento());
			ferias.setUsufruido(novaFerias.isUsufruido());
			ferias.setDataInicio(novaFerias.getDataInicio());
			ferias.setDataFim(novaFerias.getDataFim());
			ferias.setDiasRequisitados(novaFerias.getDiasRequisitados());
			ferias.setDiasVendidos(novaFerias.getDiasVendidos());
			ferias.setTipoFerias(novaFerias.getTipoFerias());
			return repository.save(ferias);
		}).orElseGet(() -> {
			novaFerias.setId(id);
			return repository.save(novaFerias);
		});
	}

	/**
	 * Deleta uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@DeleteMapping("/ferias/{id}")
	void deletarFerias(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
