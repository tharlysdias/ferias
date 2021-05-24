package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

public class RequerimentoControllerTest {

	static Requerimento requerimento;
	static RequerimentoController requerimentoController;
	static RequerimentoControllerAPI requerimentoControllerAPI;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Requerimento requerimento = new Requerimento();
				
		
	}
	
	
	@Test
	public void testCriarRequerimento() {
		Requerimento requerimento = new Requerimento
				(5, 10, LocalDate.now(), LocalDate.now().plusDays(30), 
						LocalDate.now().plusDays(60), EstadosRequerimento.PENDENTE, "ola",
						"sim", 30, 0, 0, LocalDate.now().plusDays(65), LocalDate.now().plusDays(70), 
						false, TiposFerias.TOTAL);
		Requerimento req1 = requerimentoControllerAPI. criarRequerimento(requerimento);
		Requerimento req2 = requerimentoController.buscarRequerimentoPorId(req1.getId());
		assertEquals(req1, req2);
		
	}
	

	@Ignore
	public void testAvaliarRequerimento() {		
		requerimento = new Requerimento();
		requerimento.setEstado(EstadosRequerimento.PENDENTE);
		Requerimento req1 = requerimentoController.criarRequerimento(requerimento);
		Requerimento req2 = requerimentoController.avaliarRequerimento(req1.getId(), EstadosRequerimento.APROVADO);
		assertEquals(req1, req2);
	}
	
}


