package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.service.SaldoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaldoServiceTest {
	
	private Long getNewIdColaborador() {
		return (repository.findAll().get(repository.findAll().size() - 1).getIdColaborador() + 1);
	}

	@Autowired
	public SaldoService service = Mockito.mock(SaldoService.class);
	
	@Autowired
	public SaldoRepository repository;

	@Test
	public void testCriarSaldoPassa() throws Exception {
		Long newIdColaborador = getNewIdColaborador();
		Saldo saldo = new Saldo();
		boolean verificacao = false;
		
		saldo.setIdColaborador(newIdColaborador);
		Saldo novoSaldo = service.criarSaldo(saldo);
		
		for(Saldo saldoBuscado : service.buscarTodos()) {
			if(saldoBuscado.getId().equals(novoSaldo.getId())) {
				verificacao = true;
			}
		}
		assertTrue(verificacao);
	}
	
	@Test
	public void testBuscarTodos() {
		assertTrue(repository.findAll().size() == service.buscarTodos().size());
	}
	
	@Test(expected = Exception.class)
	public void testBuscarPorIdColaboradorThrowsException() throws Exception  {
		service.buscarPorIdColaborador((long) 949494949);
	}
	
	@Test
	public void testAdicionarDescontarSaldo() throws Exception {
		Long newIdColaborador = getNewIdColaborador();
		Saldo saldo = new Saldo();
		
		saldo.setIdColaborador(newIdColaborador);
		service.criarSaldo(saldo);
		
		service.adicionarSaldo(newIdColaborador, 2.0);
		service.descontarSaldo(newIdColaborador, 2.0);
		assertTrue(service.buscarPorIdColaborador(newIdColaborador).getDiasDisponiveisDeFerias() == 0);
	}
	
	@Test(expected = Exception.class)
	public void testDescontarSaldoThrowsException() throws Exception {
		service.descontarSaldo((long) 9991, 2.0);
	}
	
	@Test(expected = Exception.class)
	public void testAdicionarSaldoThrowsException() throws Exception {
		service.adicionarSaldo((long) 9991, 2.0);
	}
	
	@Test
	public void testBuscarPorIdColaborador() throws Exception  {
		Saldo buscado = repository.findAll().get(repository.findAll().size() - 1);
		assertTrue(service.buscarPorIdColaborador(buscado.getIdColaborador()).equals(buscado));
	}
	
	@Test
	public void testBuscarPorId() throws Exception  {
		Long newIdColaborador = getNewIdColaborador();
		Saldo saldo = new Saldo();
		saldo.setIdColaborador(newIdColaborador);
		service.criarSaldo(saldo);
		Long idBuscado = (long) service.buscarTodos().get(service.buscarTodos().size() - 1).getId();
		assertTrue(service.buscarPorId(idBuscado).getIdColaborador().equals(newIdColaborador));
	}
	
	@Test(expected = Exception.class)
	public void testBuscarPorIdThrowsException() throws Exception  {
		service.buscarPorId((long) 949494949);
	}
	
	@Test
	public void testAutoUpdateSaldo() throws Exception {
		Long newIdColaborador = getNewIdColaborador();
		Saldo saldo = new Saldo();
		saldo.setIdColaborador(newIdColaborador);
		service.criarSaldo(saldo);
		service.atualizarSaldoPorIdColaborador(newIdColaborador);
		assertTrue(service.buscarPorIdColaborador(newIdColaborador).getDiasDisponiveisDeFerias().equals((Double) 0.0));
	}
	
	@Test(expected = Exception.class)
	public void testAutoUpdateSaldoThrowsException() throws Exception {
		service.atualizarSaldoPorIdColaborador((long) 10101010);
	}
	
	@Test
	public void testDeleteSaldo() throws Exception {
		Long newIdColaborador = getNewIdColaborador();
		Saldo saldo = new Saldo();
		saldo.setIdColaborador(newIdColaborador);
		Saldo saldoCriado = service.criarSaldo(saldo);
		Long idSaldoDeletado = saldoCriado.getId();
		service.deletarSaldo(idSaldoDeletado);
		assertFalse(service.buscarTodos().contains(saldoCriado));
	}
	
	@Test(expected = Exception.class)
	public void testDeleteSaldoThrowsException() throws Exception {
		service.deletarSaldo((long) 949494949);
	}
}