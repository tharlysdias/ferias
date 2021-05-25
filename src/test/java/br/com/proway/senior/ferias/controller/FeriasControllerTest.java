package br.com.proway.senior.ferias.controller;

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
	public void testBuscarPorIdColaborador() {
		int tamanhoAntes = controller.buscarPorIdColaborador(30l).size();
		requerimento.setIdColaborador(30l);
		controller.criarFerias(requerimento);
		assertEquals(tamanhoAntes + 1, controller.buscarPorIdColaborador(30l).size());
	}

	@Test
	public void testBuscarTodasFerias() {
		int tamanhoAntes = controller.buscarTodasFerias().size();
		requerimento.setIdGestor(55l);;
		controller.criarFerias(requerimento);
		List<Ferias> listaFerias = controller.buscarTodasFerias();
		assertEquals(tamanhoAntes + 1, listaFerias.size());
		assertEquals(55l, listaFerias.get(listaFerias.size()-1).getIdGestor());
	}

	@Test
	public void testAlterarEstadoFerias() throws Exception {
		controller.getRepository().save(ferias);
		Long id = ferias.getId();
		assertEquals(EstadoFerias.USUFRUIDA, 
				controller.alterarEstadoFerias(id, EstadoFerias.USUFRUIDA).getEstado());
	}

	@Test(expected = Exception.class)
	public void testAlterarEstadoFeriasException() throws Exception {
		controller.getRepository().save(ferias);
		assertEquals(EstadoFerias.USUFRUIDA, 
				controller.alterarEstadoFerias(-5l, EstadoFerias.USUFRUIDA).getEstado());	
	}
	
	@Test
	public void testAlterarDataFerias() throws Exception {
		controller.getRepository().save(ferias);
		Ferias novaFerias = new Ferias();
		novaFerias.setIdRequerimento(1l);
		novaFerias.setIdColaborador(1l);
		novaFerias.setIdGestor(2l);
		novaFerias.setDiasRequisitados(30);
		novaFerias.setDiasVendidos(0);
		novaFerias.setDataInicio(LocalDate.now().plusDays(50));
		novaFerias.setDataFim(LocalDate.now().plusDays(80));
		novaFerias.setTipoFerias(TiposFerias.TOTAL);
		novaFerias.setEstado(EstadoFerias.NAO_USUFRUIDA);
		assertEquals(LocalDate.now().plusDays(50), 
				controller.alterarDataFerias(ferias.getId(), novaFerias).getDataInicio());
		assertEquals(LocalDate.now().plusDays(50), 
				controller.getRepository().findById(ferias.getId()).get().getDataInicio());
	}

	@Test(expected = Exception.class)
	public void testAlterarDataFeriasException() throws Exception {
		controller.getRepository().save(ferias);
		Ferias novaFerias = new Ferias();
		novaFerias.setIdRequerimento(1l);
		novaFerias.setIdColaborador(1l);
		novaFerias.setIdGestor(2l);
		novaFerias.setDiasRequisitados(30);
		novaFerias.setDiasVendidos(0);
		novaFerias.setDataInicio(LocalDate.now().plusDays(50));
		novaFerias.setDataFim(LocalDate.now().plusDays(80));
		novaFerias.setTipoFerias(TiposFerias.TOTAL);
		novaFerias.setEstado(EstadoFerias.NAO_USUFRUIDA);
		assertEquals(LocalDate.now().plusDays(50), 
				controller.alterarDataFerias(-5l, novaFerias).getDataInicio());
	}
	
	@Test(expected = Exception.class)
	public void testDeletarFeriasPorFerias() throws Exception {
		controller.getRepository().save(ferias);
		int tamanhoAntes = controller.buscarTodasFerias().size(); 
		Long id = ferias.getId();
		controller.deletarFeriasPorFerias(ferias);;
		assertEquals(tamanhoAntes-1, controller.buscarTodasFerias().size());
		controller.buscarPorIdFerias(id);
	}

	@Test(expected = Exception.class)
	public void testDeletarFeriasPorId() throws Exception {
		controller.getRepository().save(ferias);
		int tamanhoAntes = controller.buscarTodasFerias().size(); 
		Long id = ferias.getId();
		controller.deletarFeriasPorId(id);
		assertEquals(tamanhoAntes-1, controller.buscarTodasFerias().size());
		controller.buscarPorIdFerias(id);
	}
	
	@Test
	public void testBuscarPorIdGestorENaoUsufruidas() {
		requerimento.setDataFimFeriasRequisitadas(LocalDate.now().plusDays(60));
		controller.criarFerias(requerimento);
		requerimento.setDataInicioFeriasRequisitadas(LocalDate.now().plusDays(40));
		controller.criarFerias(requerimento);
		List<Ferias> listaFerias = controller.buscarPorIdGestorENaoUsufruidas(requerimento.getIdGestor());
		int tamanhoLista = listaFerias.size();
		assertEquals(LocalDate.now().plusDays(60), listaFerias.get(tamanhoLista - 2).getDataFim());
		assertEquals(LocalDate.now().plusDays(40), listaFerias.get(tamanhoLista - 1).getDataInicio());
	}
	
	@Test
	public void testBuscarPorIdColaboradorENaoUsufruidas() {
		int tamanhoAntes = controller.buscarPorIdColaboradorENaoUsufruidas(ferias.getIdColaborador()).size();
		ferias.setEstado(EstadoFerias.NAO_USUFRUIDA);
		controller.getRepository().save(ferias);
		Ferias novaFerias = new Ferias();
		novaFerias.setIdRequerimento(1l);
		novaFerias.setIdColaborador(1l);
		novaFerias.setIdGestor(2l);
		novaFerias.setDiasRequisitados(30);
		novaFerias.setDiasVendidos(0);
		novaFerias.setDataInicio(LocalDate.now().plusDays(50));
		novaFerias.setDataFim(LocalDate.now().plusDays(80));
		novaFerias.setTipoFerias(TiposFerias.TOTAL);
		novaFerias.setEstado(EstadoFerias.USUFRUIDA);
		controller.getRepository().save(novaFerias);
		int tamanhoDepois = controller.buscarPorIdColaboradorENaoUsufruidas(ferias.getIdColaborador()).size();
		assertEquals(tamanhoAntes + 1, tamanhoDepois);
		
	}

	@Test
	public void testBucarPorIdRequerimento() {
		ferias.setIdRequerimento(70l);
		ferias.setDiasRequisitados(17);
		if(controller.buscarPorIdRequerimento(70l) == null) {
			controller.getRepository().save(ferias);
		}
		assertEquals(17, controller.buscarPorIdRequerimento(70l).getDiasRequisitados());
	}
	
}