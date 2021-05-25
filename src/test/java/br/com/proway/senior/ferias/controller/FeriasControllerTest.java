package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeriasControllerTest {
	
	Requerimento requerimento = new Requerimento();

	@Autowired
	private FeriasController controller = Mockito.mock(FeriasController.class);
	
	private Ferias ferias;
	
	@Before
	public void criarRequerimento() {
		ferias = new Ferias();
		ferias.setIdRequerimento(1l);
		ferias.setIdColaborador(1l);
		ferias.setIdGestor(2l);
		ferias.setDiasRequisitados(30);
		ferias.setDiasVendidos(0);
		ferias.setDataInicio(LocalDate.now().plusDays(30));
		ferias.setDataFim(LocalDate.now().plusDays(60));
		ferias.setTipoFerias(TiposFerias.TOTAL);
		ferias.setEstado(EstadoFerias.USUFRUIDA);
	}
	
	@Test
	public void testCriarFerias() {
		requerimento.setId(1l);
		requerimento.setIdColaborador(1l);
		requerimento.setIdGestor(2l);
		requerimento.setDiasRequisitados(30);
		requerimento.setDiasVendidos(0);
		requerimento.setDiasFracionados(0);
		requerimento.setDataInicioFeriasRequisitadas(LocalDate.now().plusDays(30));
		requerimento.setDataFimFeriasRequisitadas(LocalDate.now().plusDays(60));
		requerimento.setTipoFerias(TiposFerias.TOTAL);
		assertEquals(ferias.getIdColaborador(), controller.criarFerias(requerimento).getIdColaborador());
	}

	@Test
	public void testBuscarPorIdFerias() throws Exception {
		ferias.setIdColaborador(5l);
		Ferias feriasSalvas = controller.getRepository().save(ferias);
		Long id = feriasSalvas.getId();
		assertEquals(5l, controller.buscarPorIdFerias(id).getIdColaborador());
	}

	@Test
	public void testBuscarPorIdGestorENaoUsufruidas() {
		int tamanhoAntes = controller.getRepository().findByIdGestorAndEstado(2l).size();
		controller.criarFerias(requerimento);
		controller.criarFerias(requerimento);
		assert
		
	}

	@Test
	public void testBuscarPorIdColaborador() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarTodos() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterarEstadoFerias() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterarDataFerias() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletarFeriasPorFerias() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletarFeriasPorId() {
		fail("Not yet implemented");
	}

}
