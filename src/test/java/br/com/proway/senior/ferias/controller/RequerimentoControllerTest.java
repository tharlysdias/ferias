package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RequerimentoControllerTest {

	@Autowired
	private RequerimentoService requerimentoService;
	
	private Requerimento requerimento() {
		Saldo saldo = new Saldo();
//		saldo.setId(null);
		Requerimento requerimento = new Requerimento(saldo, 10l, LocalDate.of(2021, 5, 5), 
				LocalDate.of(2021, 5, 5), "ola", "opa", 10, 0, LocalDate.of(2021, 5, 5));
		
		return requerimento;
	}

	@Test
	public void testBuscarTodosRequerimentos() {
		ArrayList<Requerimento> lista = requerimentoService.buscarTodosRequerimentos();
		assertNotNull(lista);
	}

	@Test
	public void testBuscarRequerimentoPorId() {
		Requerimento requerimento = requerimento();
		requerimentoService.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		assertNotNull(requerimentoService.buscarRequerimentoPorId(id));
	}

	@Test
	public void testCriarRequerimento() {
		Requerimento requerimento = requerimento();
		this.requerimentoService.criarRequerimento(requerimento);
		Assertions.assertThat(requerimento.getId()).isNotNull();
		Assertions.assertThat(requerimento.getEstado()).isEqualTo(EstadosRequerimento.PENDENTE);
		

	}

	@Test
	public void testAtualizarRequerimento() {
		Requerimento requerimento = requerimento();
		requerimentoService.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		System.out.println("===========" +  id);
		requerimentoService.buscarRequerimentoPorId(id);
		requerimento.setDataAbertura(LocalDate.of(2021, 10, 10));
		requerimentoService.atualizarRequerimento(requerimento);
		assertEquals(LocalDate.of(2021, 10, 10), requerimentoService.buscarRequerimentoPorId(id).getDataAbertura());
	}

	@Test
	public void testDeletar() {
		Requerimento requerimento = requerimento();
		this.requerimentoService.criarRequerimento(requerimento);
		ArrayList<Requerimento> lista = requerimentoService.buscarTodosRequerimentos();
		this.requerimentoService.deletar(requerimento.getId());
		assertEquals(lista.size() - 1, requerimentoService.buscarTodosRequerimentos().size());
	}

	@Test
	public void testAvaliarRequerimento() {
		/**
		 * Nesse pega o objeto direto do banco de dados.
		 */
		Requerimento requerimento = requerimento();
		requerimentoService.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		requerimentoService.avaliarRequerimento(id, EstadosRequerimento.RECUSADO);
		assertEquals(EstadosRequerimento.RECUSADO, requerimentoService.buscarRequerimentoPorId(id).getEstado());

	}

	@Test
	public void testDesativarRequerimento() {
		/**
		 * Nesse pega o objeto direto do banco de dados.
		 */
		Requerimento requerimento = requerimento();
		requerimentoService.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		requerimentoService.desativarRequerimento(id);
		Assertions.assertThat(requerimentoService.buscarRequerimentoPorId(id)).isNull();
	}

	@Test
	public void testBuscarRequerimentoPorIdGestor() {
		ArrayList<Requerimento> requerimentos = requerimentoService.buscarRequerimentoPorIdGestor(10L);
		assertNotNull(requerimentos);
	}

	@Test
	public void testBuscarRequerimentoPorIdColaborador() {
		Requerimento requerimento = requerimento();
		requerimentoService.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		assertNotNull(requerimentoService.buscarRequerimentoPorIdColaborador(id));
	}


}
