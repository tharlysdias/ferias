package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.TiposFerias;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeriasControllerTest {

	@Autowired
	private FeriasController controller = Mockito.mock(FeriasController.class);

	private Requerimento requerimento;
	private Ferias ferias;
	
	@Before
	public void criarRequerimento() {
		ferias = new Ferias();
		requerimento = new Requerimento();
		ferias.setIdRequerimento(1l);
		ferias.setIdColaborador(1l);
		ferias.setIdGestor(2l);
		ferias.setDiasRequisitados(30);
		ferias.setDiasVendidos(0);
		ferias.setDataInicio(LocalDate.now().plusDays(30));
		ferias.setDataFim(LocalDate.now().plusDays(60));
		ferias.setTipoFerias(TiposFerias.TOTAL);
		ferias.setEstado(EstadoFerias.USUFRUIDA);
		requerimento.setId(1l);
		requerimento.setIdColaborador(1l);
		requerimento.setIdGestor(2l);
		requerimento.setDiasRequisitados(30);
		requerimento.setDiasVendidos(0);
		requerimento.setDiasFracionados(0);
		requerimento.setDataInicioFeriasRequisitadas(LocalDate.now().plusDays(30));
		requerimento.setDataFimFeriasRequisitadas(LocalDate.now().plusDays(60));
		requerimento.setTipoFerias(TiposFerias.TOTAL);
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
		int tamanhoAntes = controller.getRepository().findByIdGestorAndEstado(2l, EstadoFerias.NAO_USUFRUIDA).size();
		controller.criarFerias(requerimento);
		requerimento.setDiasRequisitados(15);
		controller.criarFerias(requerimento);
		List<Ferias> listaFerias = controller.getRepository().findByIdGestorAndEstado(2l, EstadoFerias.NAO_USUFRUIDA); 
		assertEquals(tamanhoAntes + 2, listaFerias.size());
		assertEquals(15, listaFerias.get(tamanhoAntes + 1).getDiasRequisitados());
		
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