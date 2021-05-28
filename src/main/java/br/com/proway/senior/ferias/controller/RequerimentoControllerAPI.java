package br.com.proway.senior.ferias.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.dto.RequerimentoDTO;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * ControllerAPI do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse <guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias <tharlys.dias@senior.com.br>
 */

@RestController
public class RequerimentoControllerAPI {

	@Autowired
	private RequerimentoController controller;

	@Autowired
	private ModelMapper modelMapper;

	@ResponseBody
	@RequestMapping(path = "/requerimento", method = RequestMethod.GET)
	public List<RequerimentoDTO> buscarTodos() {
		return controller.buscarTodosRequerimentos().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/{id}", method = RequestMethod.GET)
	public RequerimentoDTO buscarRequerimentoPorId(@PathVariable("id") Long id) {
		return convertToDTO(controller.buscarRequerimentoPorId(id));
	}

	@ResponseBody
	@RequestMapping(path = "/colaborador/{idColaborador}/requerimento", method = RequestMethod.GET)
	public List<RequerimentoDTO> buscarRequerimentoPorIdColaborador(@PathVariable("id") Long id) {
		return controller.buscarRequerimentoPorIdColaborador(id).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@PostMapping(path = "/requerimento/")
	public RequerimentoDTO criar(@RequestBody RequerimentoDTO requerimentoDto) {
		//System.out.println(requerimentoDto.getIdColaborador() + " " + requerimentoDto.getDiasVendidos());
		//System.out.println(convertToEntity(requerimentoDto).getIdColaborador() + " " + convertToEntity(requerimentoDto).getDiasVendidos());
		return convertToDTO(controller.criarRequerimento(convertToEntity(requerimentoDto)));
	}
	
	@ResponseBody
	@RequestMapping(path = "/requerimento/{id}", method = RequestMethod.DELETE)
	public void desativar(@PathVariable("id") Long id) {
		this.controller.desativarRequerimento(id);
	}
	
	@ResponseBody
	@RequestMapping(path = "/requerimento/avaliar/{id}", method = RequestMethod.PUT)
	public RequerimentoDTO avaliar(@PathVariable("id") Long id, @RequestBody EstadosRequerimento estado){
		return convertToDTO(controller.avaliarRequerimento(id, estado));
	}
		

	private RequerimentoDTO convertToDTO(Requerimento requerimento) {
		return modelMapper.map(requerimento, RequerimentoDTO.class);
	}
	
	private Requerimento convertToEntity(RequerimentoDTO requerimentoDto) {
		return modelMapper.map(requerimentoDto, Requerimento.class);
	}
}