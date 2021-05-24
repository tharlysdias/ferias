package br.com.proway.senior.ferias.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaldoControllerTest {

	@Autowired
	public SaldoController controller;
	
	@MockBean
	public SaldoRepository repository;

	@Test
	public void testCriarSaldoPassa() throws Exception {
//		SaldoController sc = new SaldoController(repository);
		Saldo saldo = new Saldo();
		boolean verificacao = false;
		
		saldo.setDataAdmissao(LocalDate.now());
		saldo.setDiasDisponiveisDeFerias(20);
		saldo.setIdColaborador((long) 3);
		saldo.setIdGestor((long) 1);
		
//		controller.criarSaldo(saldo);
		when(repository.findAll()).thenReturn(Stream.of(saldo).collect(Collectors.toList()));
		
		System.out.println(controller.buscarTodos().size());
		System.out.println(controller.buscarTodos().get(0).getDiasDisponiveisDeFerias());
		System.out.println(controller.buscarTodos().get(0).getIdColaborador());
		System.out.println(controller.buscarTodos().get(0).getId());
		
		for(Saldo saldoBuscado : controller.buscarTodos()) {
			if(saldoBuscado.getIdColaborador() == saldo.getIdColaborador()) {
				verificacao = true;
			}
		}
		assertTrue(verificacao);
	}
}