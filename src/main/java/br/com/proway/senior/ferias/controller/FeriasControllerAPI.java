package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.dto.FeriasDTO;

@RestController
public class FeriasControllerAPI {

	@Autowired
	private FeriasController controller;
	
	@Autowired
	private ModelMapper modelMapper;

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
	@ResponseBody
	FeriasDTO consultarFeriasPorId(@PathVariable Long id) throws Exception {
		return convertToDto(controller.buscarPorIdFerias(id));
	}

	/**
	 * Consulta as {@link Ferias} por id do colaborador.
	 * 
	 * @param id {@link id}
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Nao existe uma ferias com esse id.
	 */
	@GetMapping("/feriasColaborador/{idColaborador}")
	List<FeriasDTO> consultarFeriasPorIdColaborador(@PathVariable Long idColaborador) throws Exception {
		return controller.buscarPorIdColaborador(idColaborador).stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	/**
	 * Consulta as {@link Ferias} ainda nao usufruidas por id do gestor.
	 * 
	 * @param id {@link id}
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Nao existe uma ferias com esse id.
	 */
	@GetMapping("/feriasGestor/{idGestor}")
	List<FeriasDTO> consultarFeriasPorIdGestorNaoUsufruidas(@PathVariable Long idGestor) throws Exception {
		return controller.buscarPorIdGestorENaoUsufruidas(idGestor).stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	/**
	 * Busca todas as {@link Ferias} do banco.
	 * 
	 * @return List<Ferias>
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@GetMapping("/ferias")
	ArrayList<FeriasDTO> buscarTodos() {
		ArrayList<Ferias> ferias = (ArrayList<Ferias>) controller.buscarTodasFerias();
        return (ArrayList<FeriasDTO>) ferias.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	/**
	 * Altera uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * @return Ferias
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 * @throws Exception Ferias nao encontradas.
	 */
	@PutMapping("/ferias/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void alterarFerias(@RequestBody FeriasDTO feriasDTO, @PathVariable Long id) throws Exception {
		Ferias novaFerias = convertToEntity(feriasDTO);
		controller.alterarDataFerias(id, novaFerias);
	}
	

	/**
	 * Deleta uma {@link Ferias} do banco a partir de seu id.
	 * 
	 * @author Lucas Grijó <rksgrijo@gmail.com>
	 */
	@DeleteMapping("/ferias/{id}")
	@ResponseStatus(HttpStatus.OK)
	void deletarFerias(@PathVariable Long id) {
		controller.deletarFeriasPorId(id);
	}
	
	private FeriasDTO convertToDto(IFerias ferias) {
		FeriasDTO feriasDTO = modelMapper.map(ferias, FeriasDTO.class);
		return feriasDTO;
	}

	private Ferias convertToEntity(FeriasDTO feriasDTO) {
		Ferias ferias = modelMapper.map(feriasDTO, Ferias.class);
		return ferias;
	}

}
