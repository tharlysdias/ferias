package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.dto.FeriasDTO;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.service.FeriasService;
import br.com.proway.senior.ferias.service.SaldoService;

@RestController
public class FeriasControllerAPI {

	@Autowired
	private FeriasService feriasService;

	@Autowired
	private SaldoService saldoService;

	@Autowired
	private ModelMapper modelMapper;

	public FeriasControllerAPI() {
	}

	@GetMapping("/ferias/{id}")
	@ResponseBody
	FeriasDTO buscarFeriasPorId(@PathVariable Long id) throws Exception {
		return convertToDto(feriasService.buscarPorId(id));
	}

	@GetMapping("/ferias")
	ArrayList<FeriasDTO> buscarTodasAsFerias() {
		ArrayList<Ferias> ferias = (ArrayList<Ferias>) feriasService.buscarTodasFerias();
		if (ferias != null)
			return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
		else
			return null;
	}

	@GetMapping("/ferias/colaborador/{idColaborador}")
	ArrayList<FeriasDTO> buscarTodasAsFeriasPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		ArrayList<Ferias> ferias = feriasService.buscarTodasAsFeriasPorIdColaborador(idColaborador);
		if (ferias != null)
			return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
		else
			return null;
	}

	@GetMapping("/ferias/colaborador/a_usufruir/{idColaborador}")
	ArrayList<FeriasDTO> buscarFeriasAUsufruirPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		ArrayList<Ferias> ferias = feriasService.buscarFeriasAUsufruirPorIdColaborador(idColaborador);
		if (ferias != null)
			return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
		else
			return null;
	}

	@GetMapping("/ferias/gestor/a_usufruir/{idGestor}")
	ArrayList<FeriasDTO> buscarFeriasAUsufruirDosSubordinados(@PathVariable Long idGestor) throws Exception {
		ArrayList<Ferias> ferias = feriasService.buscarFeriasAUsufruirDosSubordinados(idGestor);
		if (ferias != null)
			return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
		else
			return null;
	}

	@GetMapping("/ferias/gestor/usufruindo/{idGestor}")
	ArrayList<FeriasDTO> buscarFeriasUsufruindoDosSubordinados(@PathVariable Long idGestor) throws Exception {
		ArrayList<Ferias> ferias = feriasService.buscarFeriasUsufruindoDosSubordinados(idGestor);
		if (ferias != null)
			return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
		else
			return null;
	}

	/**
	 * Requisicao para cancelar uma ferias, alterando seu estado para cancelada.
	 * 
	 * @param id
	 * @throws Exception
	 */
	@PutMapping("/ferias/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void cancelarFerias(@PathVariable Long id) throws Exception {
		Ferias ferias = feriasService.buscarPorId(id);
		ferias.setEstado(EstadoFerias.CANCELADA);
		saldoService.adicionarSaldo(ferias.getRequerimento().getSaldo().getIdColaborador(),
				ferias.getRequerimento().getDiasRequisitados() + ferias.getRequerimento().getDiasVendidos());
		feriasService.alterarFerias(ferias, id);
	}

	/**
	 * Converte a entidade {@link Ferias} para {@link FeriasDTO}.
	 * 
	 * @param ferias
	 * @return feriasDTO
	 */
	private FeriasDTO convertToDto(IFerias ferias) {
		FeriasDTO feriasDTO = modelMapper.map(ferias, FeriasDTO.class);
		return feriasDTO;
	}

	/**
	 * Converte uma {@link FeriasDTO} para entidade {@link Ferias}.
	 * 
	 * @param feriasDTO
	 * @return ferias
	 */

	private Ferias convertToEntity(FeriasDTO feriasDTO) {
		Ferias ferias = modelMapper.map(feriasDTO, Ferias.class);
		return ferias;
	}
}