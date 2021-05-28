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

@RestController
public class FeriasControllerAPI {

	@Autowired
	private FeriasController controllerFerias;

	@Autowired
	private RequerimentoController controllerRequerimento;

	@Autowired
	private ModelMapper modelMapper;

	public FeriasControllerAPI() {
	}

	@GetMapping("/ferias/{id}")
	@ResponseBody
	FeriasDTO buscarFeriasPorId(@PathVariable Long id) throws Exception {
		return convertToDto(controllerFerias.buscarPorId(id));
	}

	@GetMapping("/ferias")
	ArrayList<FeriasDTO> buscarTodasAsFerias() {
		ArrayList<Ferias> ferias = (ArrayList<Ferias>) controllerFerias.buscarTodasFerias();
		return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/feriasColaborador/{idColaborador}")
	ArrayList<FeriasDTO> buscarTodasAsFeriasPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		ArrayList<Ferias> ferias = controllerFerias.buscarTodasAsFeriasPorIdColaborador(idColaborador);
		return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	
	@GetMapping("/feriasAUsufruirColaborador/{idColaborador}")
	ArrayList<FeriasDTO> buscarFeriasAUsufruirPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		ArrayList<Ferias> ferias = controllerFerias.buscarFeriasAUsufruirPorIdColaborador(idColaborador);
		return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@GetMapping("/feriasAUsufruirSubordinados/{idGestor}")
	ArrayList<FeriasDTO> buscarFeriasAUsufruirDosSubordinados(@PathVariable Long idGestor) throws Exception {
//		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorId(idRequerimento);
//		return convertToDto(controllerFerias.buscarPorRequerimento(requerimento));
		return null;
	}
	
	@GetMapping("/feriasUsufruindoSubordinados/gestor/{idGestor}")
	ArrayList<FeriasDTO> buscarFeriasUsufruindoDosSubordinados(@PathVariable Long idGestor) throws Exception {
//		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorId(idRequerimento);
//		return convertToDto(controllerFerias.buscarPorRequerimento(requerimento));
		return null;
	}

	/**
	 * Requisicao para cancelar uma ferias, alterando seu estado para cancelada.
	 * 
	 * @param id
	 * @throws Exception
	 */
	@PutMapping("/ferias")
	@ResponseStatus(HttpStatus.OK)
	public void cancelarFerias(@PathVariable Long id) throws Exception {
		Ferias ferias = controllerFerias.buscarPorId(id);
		ferias.setEstado(EstadoFerias.CANCELADA);
		controllerFerias.alterarFerias(ferias, id);
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