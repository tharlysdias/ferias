package br.com.proway.senior.ferias.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidacaoTest {

	@Test
	void testValidarSaldo() {
		assertTrue(Validacao.validarSaldo(5, 15));
	}
	
	@Test
	void testValidarSaldoFalse() {
		assertFalse(Validacao.validarSaldo(25, 15));
	}

}
