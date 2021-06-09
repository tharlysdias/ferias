package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.dto.SaldoDTO;
import br.com.proway.senior.ferias.service.SaldoService;

@RestController
public class SaldoControllerAPI {

	@Autowired
	private SaldoService service;

	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Este metodo eh apenas para teste manual, sera tirado
	 * 
	 * @param saldoDto
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/saldo")
	public SaldoDTO criarSaldo(@RequestBody SaldoDTO saldoDto) {
		return convertToDTO(service.criarSaldo(convertToEntity(saldoDto)));
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	/**
	 * Retorna toda a lista de {@link Saldo}s do banco de dados.
	 * 
	 * @return ArrayList<Saldo>
	 */
	@CrossOrigin
	@GetMapping("/saldo")
	ArrayList<SaldoDTO> buscarTodos() {
		// return converterListaSaldoParaSaldoDTO((ArrayList<Saldo>)
		// controller.buscarTodos());
		return (ArrayList<SaldoDTO>) service.buscarTodos().stream().map(this::convertToDTO)
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
	@CrossOrigin
	@GetMapping(value = "/colaborador/{idColaborador}/saldo")
	public SaldoDTO buscarPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		return convertToDTO(service.buscarPorIdColaborador(idColaborador));
	}

	/**
	 * Recebe um id e retorna um {@link Saldo} do banco de dados.
	 * 
	 * @param id
	 * @return {@link Saldo}
	 * @throws Exception
	 */
	@CrossOrigin
	@GetMapping(value = "/saldo/{id}")
	public SaldoDTO buscarPorId(@PathVariable Long id) throws Exception {
		return convertToDTO(service.buscarPorId(id));
	}

	private SaldoDTO convertToDTO(Saldo saldo) {
		return modelMapper.map(saldo, SaldoDTO.class);
	}

	private Saldo convertToEntity(SaldoDTO saldoDto) {
		return modelMapper.map(saldoDto, Saldo.class);
	}
	
	@CrossOrigin
	@PutMapping("/atualizarSaldo/{id}")
	SaldoDTO atualizarSaldo(@PathVariable Long idColaborador) throws Exception {
		return convertToDTO(service.atualizarSaldoPorIdColaborador(idColaborador));
	}
}