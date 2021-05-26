package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

@SpringBootTest
@RunWith(SpringRunner.class)

public class RequerimentoControllerTest {

	@Autowired
	private RequerimentoController controllerRequerimento;

	@Test
	public void testBuscarTodosRequerimentos() {
		ArrayList<Requerimento> lista = controllerRequerimento.buscarTodosRequerimentos();
		assertNotNull(lista);
	}

	@Test
	public void testBuscarRequerimentoPorId() {
		Requerimento requerimento = requerimento();
		controllerRequerimento.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		assertNotNull(controllerRequerimento.buscarRequerimentoPorId(id));
	}

	@Test
	public void testCriarRequerimento() {
		Requerimento requerimento = requerimento();
		this.controllerRequerimento.criarRequerimento(requerimento);
		Assertions.assertThat(requerimento.getId()).isNotNull();
		Assertions.assertThat(requerimento.getEstado()).isEqualTo(EstadosRequerimento.PENDENTE);
		Assertions.assertThat(requerimento.getTipoFerias()).isEqualTo(TiposFerias.TOTAL);

	}

	@Test
	public void testAtualizarRequerimento() {
		Requerimento requerimento = requerimento();
		controllerRequerimento.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		controllerRequerimento.buscarRequerimentoPorId(id);
		requerimento.setDataAbertura(LocalDate.of(2021, 10, 10));
		controllerRequerimento.atualizarRequerimento(requerimento);
		assertEquals(LocalDate.of(2021, 10, 10), controllerRequerimento.buscarRequerimentoPorId(id).getDataAbertura());
	}

	@Test
	public void testDeletar() {
		Requerimento requerimento = requerimento();
		this.controllerRequerimento.criarRequerimento(requerimento);
		ArrayList<Requerimento> lista = controllerRequerimento.buscarTodosRequerimentos();
		this.controllerRequerimento.deletar(requerimento.getId());
		assertEquals(lista.size() - 1, controllerRequerimento.buscarTodosRequerimentos().size());
	}

	@Test
	public void testAvaliarRequerimento() {
		/**
		 * Nesse pega o objeto direto do banco de dados.
		 */
		Requerimento requerimento = requerimento();
		controllerRequerimento.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		controllerRequerimento.avaliarRequerimento(id, EstadosRequerimento.RECUSADO);
		assertEquals(EstadosRequerimento.RECUSADO, controllerRequerimento.buscarRequerimentoPorId(id).getEstado());

	}

	@Test
	public void testDesativarRequerimento() {
		/**
		 * Nesse pega o objeto direto do banco de dados.
		 */
		Requerimento requerimento = requerimento();
		controllerRequerimento.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		controllerRequerimento.desativarRequerimento(requerimento);
		Assertions.assertThat(controllerRequerimento.buscarRequerimentoPorId(id)).isNull();
	}

	@Test
	public void testBuscarRequerimentoPorIdGestor() {
		ArrayList<Requerimento> requerimentos = controllerRequerimento.buscarRequerimentoPorIdGestor(10L);
		assertNotNull(requerimentos);
	}

	@Test
	public void testBuscarRequerimentoPorIdColaborador() {
		Requerimento requerimento = requerimento();
		controllerRequerimento.criarRequerimento(requerimento);
		Long id = requerimento.getId();
		assertNotNull(controllerRequerimento.buscarRequerimentoPorIdColaborador(id));
	}

	private Requerimento requerimento() {
		Requerimento requerimento = new Requerimento(5l, 10l, LocalDate.now(), LocalDate.now().plusDays(30),
				LocalDate.now().plusDays(60), EstadosRequerimento.PENDENTE, "Me da ferias", "nao", 30, 10, 5,
				LocalDate.now().plusDays(65), LocalDate.now().plusDays(70), false, TiposFerias.TOTAL);
	
		return requerimento;
	}

}
