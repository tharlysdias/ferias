package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.dto.SaldoDTO;

@RestController
public class SaldoControllerAPI {

	@Autowired
	private final SaldoController controller;

	@Autowired
	private ModelMapper modelMapper;

	public SaldoControllerAPI(SaldoController controller) {
		this.controller = controller;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	/**
	 * Retorna toda a lista de {@link Saldo}s do banco de dados.
	 * 
	 * @return ArrayList<Saldo>
	 */
	@GetMapping("/saldo")
	ArrayList<SaldoDTO> buscarTodos() {
		// return converterListaSaldoParaSaldoDTO((ArrayList<Saldo>)
		// controller.buscarTodos());
		return (ArrayList<SaldoDTO>) controller.buscarTodos().stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	// end::get-aggregate-root[]
	/**
	 * Busca um {@link Saldo} no banco de dados
	 * 
	 * Recebe um id de colaborador e retorna o {@link Saldo} disponível.
	 * 
	 * @param idColaborador
	 * @return {@link Saldo}
	 */
	@GetMapping(value = "/colaborador/{idColaborador}/saldo")
	public SaldoDTO buscarPorIdColaborador(@PathVariable Long idColaborador) {
		return convertToDTO(controller.buscarPorIdColaborador(idColaborador));
	}

	/**
	 * Recebe um id e retorna um {@link Saldo} do banco de dados.
	 * 
	 * @param id
	 * @return {@link Saldo}
	 * @throws Exception
	 */
	@GetMapping(value = "/saldo/{id}")
	public SaldoDTO buscarPorId(@PathVariable Long id) throws Exception {
		return convertToDTO(controller.buscarPorId(id));
	}

	/**
	 * Este metodo eh apenas para teste manual, sera tirado
	 * 
	 * @param saldoDto
	 * @return
	 */
	@PostMapping(value = "/saldo")
	public SaldoDTO criarSaldo(@RequestBody SaldoDTO saldoDto) {
		return convertToDTO(controller.criarSaldo(convertToEntity(saldoDto)));
	}

	private SaldoDTO convertToDTO(Saldo saldo) {
		return modelMapper.map(saldo, SaldoDTO.class);
	}

	private Saldo convertToEntity(SaldoDTO saldoDto) {
		return modelMapper.map(saldoDto, Saldo.class);
	}
}