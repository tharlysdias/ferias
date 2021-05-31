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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaldoServiceTest {

	@Autowired
	public SaldoService service = Mockito.mock(SaldoService.class);
	
	@Autowired
	public SaldoRepository repository;

//	@Test
//	public void testCriarSaldoPassa() throws Exception {
//		Saldo saldo = new Saldo();
//		boolean verificacao = false;
//		
//		saldo.setIdColaborador((long) 123);
//		service.criarSaldo(saldo);
//		
//		for(Saldo saldoBuscado : service.buscarTodos()) {
//			if(saldoBuscado.getIdColaborador().equals((long) 123)) {
//				verificacao = true;
//			}
//		}
//		assertTrue(verificacao);
//	}
//	
//	@Test
//	public void testBuscarTodos() {
//		assertTrue(repository.findAll().size() == service.buscarTodos().size());
//	}
//	
//	@Test
//	public void testBuscarTodosFalse() {
//		assertFalse(repository.findAll().size() != service.buscarTodos().size());
//	}
//	
//	@Test
//	public void testDescontarSaldo() throws Exception {
//		service.adicionarSaldo((long) 4, 10);
//		service.descontarSaldo((long) 4, 10);
//		assertTrue(service.buscarPorIdColaborador((long) 4).getDiasDisponiveisDeFerias() == 0);
//	}
//	
//	@Test
//	public void testBuscarPorId() throws Exception  {
//		Saldo saldo = new Saldo();
//		saldo.setIdColaborador((long) 666);
//		service.criarSaldo(saldo);
//		Long idBuscado = (long) service.buscarTodos().get(service.buscarTodos().size() - 1).getId();
//		assertTrue(service.buscarPorId(idBuscado).getIdColaborador() == (long) 666);
//	}
	
	@Test
	public void testAutoUpdateSaldo() throws Exception {
		String path = "http://localhost:8081/api/jornadas/123";
		service.atualizarSaldoPorIdColaborador((long) 123, path);
		System.out.println(service.testRestTemplate(path));
	}
}