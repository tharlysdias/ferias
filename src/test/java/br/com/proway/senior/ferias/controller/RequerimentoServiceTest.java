package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequerimentoServiceTest {

	@Autowired
	private RequerimentoService serviceRequerimento;
	
	private static Saldo saldo;
	private static Requerimento requerimento;
		
	@Test
	public void testASetupRequerimento() {
		saldo = new Saldo();
		requerimento = new Requerimento(saldo, 10l, LocalDate.of(2021, 5, 5), 
				LocalDate.of(2021, 5, 5), "ola", "opa", 10, 0, LocalDate.of(2021, 5, 5));
		serviceRequerimento.criarRequerimento(requerimento, saldo);
		
		System.out.println(saldo.getId());
		System.out.println(requerimento.getId());
	}
	
	@Test
	public void testBBuscarRequerimentoPorId() {
		System.out.println(saldo.getId());
		Requerimento requerimentoBuscado = serviceRequerimento.buscarRequerimentoPorId(requerimento.getId());
		assertNotNull(requerimentoBuscado);
		assertEquals(saldo.getId(), requerimentoBuscado.getSaldo().getId());
	}
		
			
	@Ignore
	public void testBuscarTodosRequerimentos() {
		ArrayList<Requerimento> lista = serviceRequerimento.buscarTodosRequerimentos();
		assertNotNull(lista);
	}



	@Ignore
	public void testCriarRequerimento() {
//		Requerimento requerimento = requerimento();
//		serviceRequerimento.criarRequerimento(requerimento, 1l);
//		Assertions.assertThat(requerimento.getId()).isNotNull();
//		Assertions.assertThat(requerimento.getEstado()).isEqualTo(EstadosRequerimento.PENDENTE);
				
	}

	@Ignore
	public void testAtualizarRequerimento() {
//		Requerimento requerimento = requerimento();
		Long id = requerimento.getId();
		serviceRequerimento.buscarRequerimentoPorId(id);
		requerimento.setDataAbertura(LocalDate.of(2021, 10, 10));
		serviceRequerimento.atualizarRequerimento(requerimento);
		assertEquals(LocalDate.of(2021, 10, 10), serviceRequerimento.buscarRequerimentoPorId(id).getDataAbertura());
	}

	@Ignore
	public void testDeletar() {
//		Requerimento requerimento = requerimento();
		ArrayList<Requerimento> lista = serviceRequerimento.buscarTodosRequerimentos();
		this.serviceRequerimento.deletar(requerimento.getId());
		assertEquals(lista.size() - 1, serviceRequerimento.buscarTodosRequerimentos().size());
	}

	@Ignore
	public void testAvaliarRequerimento() {
		/**
		 * Nesse pega o objeto direto do banco de dados.
		 */
//		Requerimento requerimento = requerimento();
		Long id = requerimento.getId();
		serviceRequerimento.avaliarRequerimento(id, EstadosRequerimento.RECUSADO);
		assertEquals(EstadosRequerimento.RECUSADO, serviceRequerimento.buscarRequerimentoPorId(id).getEstado());

	}

	@Ignore
	public void testDesativarRequerimento() {
		/**
		 * Nesse pega o objeto direto do banco de dados.
		 */
//		Requerimento requerimento = requerimento();
		Long id = requerimento.getId();
		serviceRequerimento.desativarRequerimento(id);
		Assertions.assertThat(serviceRequerimento.buscarRequerimentoPorId(id)).isNull();
	}

	@Ignore
	public void testBuscarRequerimentoPorIdGestor() {
		ArrayList<Requerimento> requerimentos = serviceRequerimento.buscarRequerimentoPorIdGestor(10L);
		assertNotNull(requerimentos);
	}

	@Ignore
	public void testBuscarRequerimentoPorIdColaborador() {
//		Requerimento requerimento = requerimento();
		Long id = requerimento.getId();
		assertNotNull(serviceRequerimento.buscarRequerimentoPorIdColaborador(id));
	}
	
	@Test
	public void testXCleanDB() {
//		serviceRequerimento.deletar(requerimento.ge);;
	}
	
}
