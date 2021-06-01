package br.com.proway.senior.ferias.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.model.externo.JornadaDTO;

@Service
public class SaldoService {

	@Autowired
	private SaldoRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Cria novo saldo
	 * 
	 * @param novoSaldo
	 * @return {@link Saldo}
	 */
	public Saldo criarSaldo(Saldo novoSaldo) {
		novoSaldo.setDataAdmissao(LocalDate.now());
		novoSaldo.setDiasDisponiveisDeFerias(0.0);
		return repository.saveAndFlush(novoSaldo);
	}

	/**
	 * Desconta dias de um {@link Saldo}.
	 * 
	 * Busca o saldo de ferias do colaborador e desconta o numero de dias passados
	 * no argumento. Atualiza o dia de ferias no objeto buscado e devolve o item
	 * para o banco de dados.
	 * 
	 * @param id
	 * @param diasDescontados
	 * @return
	 * @throws Exception
	 */
	public Saldo descontarSaldo(Long idColaborador, double diasDescontados) throws Exception {
		double diasAtualizados = 0.0;
		
		try {
			diasAtualizados = repository.findByIdColaborador(idColaborador).getDiasDisponiveisDeFerias()
			- diasDescontados;
		} catch(Exception e) {
			e.printStackTrace();
		}
		Saldo saldo = repository.findByIdColaborador(idColaborador);
		saldo.setDiasDisponiveisDeFerias(diasAtualizados);
		return repository.save(saldo);
	}

	/**
	 * Adiciona dias a um {@link Saldo}.
	 * 
	 * Recebe uma id de colaborador e o valor de dias a serem descontados
	 * 
	 * @param id
	 * @param diasDescontados
	 * @return
	 * @throws Exception
	 */
	public Saldo adicionarSaldo(Long idColaborador, double diasAdicionados) throws Exception {
		double diasAtualizados = 0.0;
		
		try {
			diasAtualizados = repository.findByIdColaborador(idColaborador).getDiasDisponiveisDeFerias()
			+ diasAdicionados;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Saldo saldo = repository.findByIdColaborador(idColaborador);
		saldo.setDiasDisponiveisDeFerias(diasAtualizados);
		return repository.save(saldo);
	}

	/**
	 * Recebe um id e retorna um {@link Saldo} do banco de dados.
	 * 
	 * @param id
	 * @return {@link Saldo}
	 * @throws Exception
	 */
	public Saldo buscarPorId(Long id) throws Exception {
		return repository.findById(id)
				.orElseThrow(() -> new Exception("Error: nao existe saldo com este id " + id + "."));
	}

	/**
	 * Retorna toda a lista de {@link Saldo}s do banco de dados.
	 * 
	 * @return ArrayList<Saldo>
	 */
	public ArrayList<Saldo> buscarTodos() {
		return (ArrayList<Saldo>) repository.findAll();
	}

	/**
	 * Busca um {@link Saldo} no banco de dados
	 * 
	 * Recebe um id de colaborador e retorna o {@link Saldo} disponível.
	 * 
	 * @param idColaborador
	 * @return {@link Saldo}
	 * @throws Exception 
	 */
	public Saldo buscarPorIdColaborador(Long idColaborador) throws Exception {
		return repository.findByIdColaborador(idColaborador);
	}

	/**
	 * Atualiza um saldo no banco de dados
	 * 
	 * Recebe um objeto novo{@link Saldo} e um id. Busca no banco de dados o objeto
	 * com a id passada e atribui os valores do novo{@link Saldo} na objeto buscado.
	 * Insere o objeto no banco de dados na mesma id do parametro.;
	 * 
	 * @param novoSaldo
	 * @param id
	 * @return {@link Saldo}
	 * @throws Exception
	 */
	public Saldo atualizarSaldoPorIdColaborador(Long idColaborador) throws Exception {
		String path = "http://localhost:8081/api/jornadas/";
		ResponseEntity<JornadaDTO[]> responseEntity = restTemplate.getForEntity(path + idColaborador, JornadaDTO[].class);
		List<JornadaDTO> jornadas = Arrays.asList(responseEntity.getBody());
		Saldo fetchedSaldo = repository.findByIdColaborador(idColaborador);
		int jornadasAntigo = fetchedSaldo.getJornadas();
		int jornadasAtual = (int) jornadas.size();
		double saldoParaAdicionar = (jornadasAtual - jornadasAntigo) / 8.5;
		
		fetchedSaldo.setDiasDisponiveisDeFerias(fetchedSaldo.getDiasDisponiveisDeFerias() + saldoParaAdicionar);
		fetchedSaldo.setJornadas(jornadasAtual);
		
		return repository.save(fetchedSaldo);
	}

	/**
	 * Deleta um {@link Saldo} do banco de dados.
	 * 
	 * Recebe uma id. Deleta do banco de dados o objeto com esta id;
	 * 
	 * @param id
	 * @return void
	 */
	public void deletarSaldo(Long id) {
		repository.deleteById(id);
	}
}