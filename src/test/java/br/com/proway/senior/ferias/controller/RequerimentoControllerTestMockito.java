package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

@RunWith(SpringRunner.class)
@SpringBootTest
class RequerimentoControllerTestMockito {
		
	@Mock
	Requerimento requerimento = new Requerimento(5, 10, LocalDate.now(), 
			LocalDate.now().plusDays(30), LocalDate.now().plusDays(60), 
			EstadosRequerimento.PENDENTE, "Me da ferias", "nao", 30, 0, 0, 
			LocalDate.now().plusDays(65), LocalDate.now().plusDays(70), false, 
			TiposFerias.TOTAL);
	
	
@SuppressWarnings("deprecation")
@Before
public void initMocks() {
	MockitoAnnotations.initMocks(this);
	this.service = new RequerimentoController();
}

	@Autowired
	private RequerimentoController service;
	
	@MockBean
	private RequerimentoControllerAPI repository;
		
	@Test
	void testBuscarTodosRequerimentos() {
		RequerimentoController requerimentoControllerMock = Mockito.mock(RequerimentoController.class);
		ArrayList<Requerimento> todos = requerimentoControllerMock.buscarTodosRequerimentos();
		Assert.assertTrue(todos.isEmpty());
	}
	
	@Test
	void testBuscarRequerimentoPorId() {
		
	}
		
	@Test
	void testCriarRequerimento() {
		Requerimento requerimento = Mockito.mock(Requerimento.class);
		Mockito.when(repository.criar(requerimento)).thenReturn(requerimento);
		Assert.assertEquals(requerimento, this.service.criarRequerimento(requerimento));
	}
	
	@Test
	void testCriarRequerientoComFalha() {
		
	}
	
	
	
	
	
	@Test
	void testAtualizarRequerimento() {
		
		
	}
		
	@Test
	void testDeletarRequerimento() {
		
	}
	
	@Test
	void testAvaliarRequerimento() {
		
	}
	
}
