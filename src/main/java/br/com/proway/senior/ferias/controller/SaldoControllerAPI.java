package br.com.proway.senior.ferias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;

@RestController
public class SaldoControllerAPI {

//	@Autowired
//	private final SaldoController controller;
//
//	public SaldoControllerAPI(SaldoController controller) {
//		this.controller = controller;
//	}

	@Autowired
	private final SaldoRepository repository;
	
	public SaldoControllerAPI(SaldoRepository repository) {
		this.repository = repository;
	}
//	
//	/**
//	 * Retorna toda a lista de {@link Saldo}s do banco de dados.
//	 * 
//	 * @return ArrayList<Saldo>
//	 */
//	@GetMapping(value = "/saldo")
//	ArrayList<SaldoDTO> buscarTodos() {
//		return converterListaSaldoParaSaldoDTO((ArrayList<Saldo>) controller.buscarTodos());
//	}
//
//	/**
//	 * Busca um {@link Saldo} no banco de dados
//	 * 
//	 * Recebe um id de colaborador e retorna o {@link Saldo} disponível.
//	 * 
//	 * @param idColaborador
//	 * @return {@link Saldo}
//	 */
//	@GetMapping(value = "/colaborador/{idColaborador}/saldo")
//	public SaldoDTO buscarPorIdColaborador(@PathVariable Long idColaborador) {
//		return new SaldoDTO(controller.buscarPorIdColaborador(idColaborador));
//	}
//
//	/**
//	 * Retorna todos os saldos por id de Gestor
//	 * 
//	 * Busca toda a lista de {@link Saldo} e seleciona aqueles com a id do Gestor
//	 * recebida no parâmetro. Retorna a lista selecionada.
//	 * 
//	 * @param idColaborador
//	 * @return ArrayList<Saldo>
//	 */
//	@GetMapping(value = "/gestor/{idGestor}/saldo")
//	public ArrayList<SaldoDTO> buscarTodosPorIdGestor(@PathVariable Long idGestor) {
//		return converterListaSaldoParaSaldoDTO(controller.buscarTodosPorIdGestor(idGestor));
//	}
//
//	/**
//	 * Recebe um id e retorna um {@link Saldo} do banco de dados.
//	 * 
//	 * @param id
//	 * @return {@link Saldo}
//	 * @throws Exception
//	 */
//	@GetMapping(value = "/saldo/{id}")
//	public SaldoDTO buscarPorId(@PathVariable Long id) throws Exception {
//		return new SaldoDTO(controller.buscarPorId(id));
//	}
//
//	private ArrayList<SaldoDTO> converterListaSaldoParaSaldoDTO(ArrayList<Saldo> saldos) {
//		ArrayList<SaldoDTO> saldosDto = new ArrayList<SaldoDTO>();
//		for (Saldo saldo : saldos) {
//			SaldoDTO saldoDto = new SaldoDTO(saldo);
//			saldosDto.add(saldoDto);
//		}
//		return saldosDto;
//	}
//
	@PostMapping(value = "/saldo/")
	public Saldo criarSaldo(@RequestBody Saldo saldo) throws Exception {
//		return controller.criarSaldo(saldo);
		return repository.save(saldo); 
	}
}