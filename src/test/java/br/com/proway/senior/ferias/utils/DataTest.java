package br.com.proway.senior.ferias.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataTest {

	@Test
	public void testRetornarIntervaloEmDiasEntreAsDatas() {
		LocalDate dataInicioFerias = LocalDate.now().plusDays(60);
		LocalDate dataRequerimento = LocalDate.now();
		assertTrue(Data.validacaoPrazoSolicitacaoDeFerias(dataRequerimento, dataInicioFerias));
	}

	@Test
	public void testRetornarIntervaloEmDiasEntreAsDatasFalse() {
		LocalDate dataInicioFerias = LocalDate.now().plusDays(10);
		LocalDate dataRequerimento = LocalDate.now();
		assertFalse(Data.validacaoPrazoSolicitacaoDeFerias(dataRequerimento, dataInicioFerias));
	}
	@Test
	public void testValidacaoPrazoSolicitacaoDeFerias() {
		LocalDate dataInicioFerias = LocalDate.now().plusDays(10);
		LocalDate dataFimFerias = LocalDate.now().plusDays(40);
		assertEquals(30, Data.retornarIntervaloEmDiasEntreAsDatas(dataInicioFerias, dataFimFerias));
		
	}

}
