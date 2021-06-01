package br.com.proway.senior.ferias.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.dto.RequerimentoDTO;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.service.RequerimentoService;
import br.com.proway.senior.ferias.service.SaldoService;

/**
 * ControllerAPI do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse <guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias <tharlys.dias@senior.com.br>
 * @author David Hildebrand <davihildebran@gmail.com>
 * @author Lucas Grijó <rksgrijo@gmail.com>
 */
@RestController
public class RequerimentoControllerAPI {

	@Autowired
	private RequerimentoService requerimentoService;

	@Autowired
	private SaldoService saldoService;

	@Autowired
	private ModelMapper modelMapper;

	@ResponseBody
	@RequestMapping(path = "/requerimento", method = RequestMethod.GET)
	public List<RequerimentoDTO> buscarTodos() {
		return requerimentoService.buscarTodosRequerimentos().stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/{id}", method = RequestMethod.GET)
	public RequerimentoDTO buscarRequerimentoPorId(@PathVariable Long id) {
		return convertToDTO(requerimentoService.buscarRequerimentoPorId(id));
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/colaborador/{idColaborador}", method = RequestMethod.GET)
	public List<RequerimentoDTO> buscarRequerimentoPorIdColaborador(@PathVariable Long idColaborador) {
		return requerimentoService.buscarRequerimentoPorIdColaborador(idColaborador).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/colaborador/{idColaborador}/estado/", method = RequestMethod.GET)
	public List<RequerimentoDTO> buscarRequerimentoPorIdEEstadoColaborador(
			@PathVariable Long idColaborador, @RequestBody EstadosRequerimento estado) {
		return requerimentoService.buscarRequerimentoPorIdColaboradorEEstado(idColaborador, estado).stream()
				.map(this::convertToDTO).collect(Collectors.toList());
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/{idSaldo}", method = RequestMethod.POST)
	public RequerimentoDTO criar(@PathVariable Long idSaldo, @RequestBody RequerimentoDTO requerimentoDto)
			throws Exception {
		Requerimento novoRequerimento = convertToEntity(requerimentoDto);
		System.out.println(novoRequerimento.toString());
		Saldo saldo = saldoService.buscarPorId(idSaldo);
		if (saldo != null) {
			novoRequerimento.setSaldo(saldo);
			return convertToDTO(requerimentoService.criarRequerimento(novoRequerimento));
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/{id}", method = RequestMethod.DELETE)
	public void desativar(@PathVariable("id") Long id) {
		this.requerimentoService.desativarRequerimento(id);
	}

	@ResponseBody
	@RequestMapping(path = "/requerimento/avaliar/{id}", method = RequestMethod.PUT)
	public RequerimentoDTO avaliar(@PathVariable Long id, @RequestBody EstadosRequerimento estado) throws Exception {
		return convertToDTO(requerimentoService.avaliarRequerimento(id, estado));
	}

	/**
	 * Converte a entidade {@link Requerimento} para {@link RequerimentoDTO}.
	 * 
	 * @param requerimento
	 * @return requerimentoDTO
	 */
	private RequerimentoDTO convertToDTO(Requerimento requerimento) {
		return modelMapper.map(requerimento, RequerimentoDTO.class);
	}

	/**
	 * Converte uma {@link RequerimentoDTO} para entidade {@link Requerimento}.
	 * 
	 * @param requerimentoDTO
	 * @return requerimento
	 */
	private Requerimento convertToEntity(RequerimentoDTO requerimentoDto) {
		return modelMapper.map(requerimentoDto, Requerimento.class);
	}
}