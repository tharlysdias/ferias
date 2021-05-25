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
	
	static Requerimento requerimento;
	
	@Autowired
	private RequerimentoController controllerRequerimento;
				
	@Test
	public void testBuscarTodosRequerimentos() {
		ArrayList<Requerimento> lista = controllerRequerimento.buscarTodosRequerimentos();
		assertNotNull(lista);
	}

	@Test
	public void testBuscarRequerimentoPorId() {
		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorId(1l);
		assertNotNull(requerimento);
	}

	@Test
	public void testCriarRequerimento() {
		requerimento = new Requerimento (5l, 10l, LocalDate.now(), 
			LocalDate.now().plusDays(30), LocalDate.now().plusDays(60), 
			EstadosRequerimento.PENDENTE, "Me da ferias", "nao", 30, 0, 0, 
			LocalDate.now().plusDays(65), LocalDate.now().plusDays(70), false, 
			TiposFerias.TOTAL);
		this.controllerRequerimento.criarRequerimento(requerimento);
		Assertions.assertThat(requerimento.getId()).isNotNull();
		Assertions.assertThat(requerimento.getEstado()).isEqualTo(EstadosRequerimento.PENDENTE);
		Assertions.assertThat(requerimento.getTipoFerias()).isEqualTo(TiposFerias.TOTAL);
		
	}

	@Test
	public void testAtualizarRequerimento() {
		Requerimento requerimento2 = controllerRequerimento.buscarRequerimentoPorId(2l);
		requerimento2.setDataAbertura(LocalDate.of(2021, 10, 10));
		controllerRequerimento.atualizarRequerimento(requerimento2);
		assertEquals(LocalDate.of(2021, 10, 10), controllerRequerimento.
				buscarRequerimentoPorId(2l).getDataAbertura());
	}

	@Test
	public void testDeletar() {
		Requerimento requerimento = new Requerimento (5l, 10l, LocalDate.now(), 
				LocalDate.now().plusDays(30), LocalDate.now().plusDays(60), 
				EstadosRequerimento.PENDENTE, "Me da ferias", "nao", 30, 0, 0, 
				LocalDate.now().plusDays(65), LocalDate.now().plusDays(70), false, 
				TiposFerias.TOTAL);
		this.controllerRequerimento.criarRequerimento(requerimento);
		ArrayList<Requerimento> lista = controllerRequerimento.buscarTodosRequerimentos();
		this.controllerRequerimento.deletar(requerimento.getId());
		assertEquals(lista.size() -1, controllerRequerimento.buscarTodosRequerimentos().size());
	}

	@Test
	public void testAvaliarRequerimento() {
		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorId(7l); //nesse pega o objeto direto do banco de dados.
		controllerRequerimento.avaliarRequerimento(requerimento.getId(), EstadosRequerimento.RECUSADO);
		assertEquals(EstadosRequerimento.RECUSADO, controllerRequerimento.buscarRequerimentoPorId(7l).getEstado());
				
	}

	@Test
	public void testDesativarRequerimento() {
		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorId(20l); //nesse pega o objeto direto do banco de dados.
		controllerRequerimento.desativarRequerimento(requerimento);
		Assertions.assertThat(controllerRequerimento.buscarRequerimentoPorId(20l)).isNull();
	}

	@Test
	public void testBuscarRequerimentoPorIdGestor() {
		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorIdGestor(6l);
		assertNotNull(requerimento);
	}

	@Test
	public void testBuscarRequerimentoPorIdColaborador() {
		Requerimento requerimento = controllerRequerimento.buscarRequerimentoPorIdColaborador(5l);
		assertNotNull(requerimento);
	}

}
