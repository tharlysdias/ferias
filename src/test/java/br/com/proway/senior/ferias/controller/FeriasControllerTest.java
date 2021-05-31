package br.com.proway.senior.ferias.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.proway.senior.ferias.model.FeriasRepository;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeriasControllerTest {

	@Mock
	private FeriasRepository feriasRepository;
	@Mock
	private RequerimentoRepository requerimentoRepository;
	@Mock
	private SaldoRepository saldoRepository;
	
	private FeriasController feriasController;
	
	private Saldo saldo;
	private Requerimento requerimento;
	
	@Before
	public void setup() {
		feriasController = new FeriasController(feriasRepository, requerimentoRepository, saldoRepository);

		saldo = new Saldo(666l, 30, LocalDate.now());
		saldoRepository.save(saldo);
		
		requerimento = new Requerimento();
		requerimento.setSaldo(saldo);
		requerimento.setIdGestor(23l);
		requerimento.setDataAbertura(LocalDate.now());
		requerimento.setPrazoAnalise(LocalDate.now().plusDays(15));
		requerimento.setEstado(EstadosRequerimento.PENDENTE);
		requerimento.setDiasRequisitados(30);
		requerimento.setDiasVendidos(40);
		requerimento.setDataInicioFerias(LocalDate.now().plusMonths(1));
		requerimentoRepository.save(requerimento);
		
		
	}
	
	@Test
	public void testCriarFerias() {
		feriasController.criarFerias(requerimento);
		assertEquals(feriasRepository.getById(requerimento.getId()).getEstado(), EstadoFerias.A_USUFRUIR);
	}
	
	@Test
	public void testBuscarPorId(){
//		given(feriasRepository.findById(1l)).willReturn(new Ferias());
	}

//	@Test
//	public void testBuscarPorIdFerias() throws Exception {
//		ferias.setIdColaborador(5l);
//		Ferias feriasSalvas = controller.getRepository().save(ferias);
//		Long id = feriasSalvas.getId();
//		assertEquals((Long) 5L, controller.buscarPorIdRequerimento(id).getIdColaborador());
//	}
//
//	@Test
//	public void testBuscarPorIdColaborador() {
//		int tamanhoAntes = controller.buscarPorIdColaborador(30l).size();
//		requerimento.setIdColaborador(30l);
//		controller.criarFerias(requerimento);
//		assertEquals(tamanhoAntes + 1, controller.buscarPorIdColaborador(30l).size());
//	}
//
//	@Test
//	public void testBuscarTodasFerias() {
//		int tamanhoAntes = controller.buscarTodasFerias().size();
//		requerimento.setIdGestor(55l);;
//		controller.criarFerias(requerimento);
//		List<Ferias> listaFerias = controller.buscarTodasFerias();
//		assertEquals(tamanhoAntes + 1, listaFerias.size());
//		assertEquals((Long) 55l, listaFerias.get(listaFerias.size()-1).getIdGestor());
//	}
//
//	@Test
//	public void testAlterarEstadoFerias() throws Exception {
//		controller.getRepository().save(ferias);
//		Long id = ferias.getId();
//		assertEquals(EstadoFerias.USUFRUIDA, 
//				controller.alterarEstadoFerias(id, EstadoFerias.USUFRUIDA).getEstado());
//	}
//
//	@Test(expected = Exception.class)
//	public void testAlterarEstadoFeriasException() throws Exception {
//		controller.getRepository().save(ferias);
//		assertEquals(EstadoFerias.USUFRUIDA, 
//				controller.alterarEstadoFerias(-5l, EstadoFerias.USUFRUIDA).getEstado());	
//	}
//	
//	@Test
//	public void testAlterarDataFerias() throws Exception {
//		controller.getRepository().save(ferias);
//		Ferias novaFerias = new Ferias();
//		novaFerias.setIdRequerimento(1l);
//		novaFerias.setIdColaborador(1l);
//		novaFerias.setIdGestor(2l);
//		novaFerias.setDiasRequisitados(30);
//		novaFerias.setDiasVendidos(0);
//		novaFerias.setDataInicio(LocalDate.now().plusDays(50));
//		novaFerias.setDataFim(LocalDate.now().plusDays(80));
//		novaFerias.setTipoFerias(TiposFerias.TOTAL);
//		novaFerias.setEstado(EstadoFerias.NAO_USUFRUIDA);
//		assertEquals(LocalDate.now().plusDays(50), 
//				controller.alterarDataFerias(ferias.getId(), novaFerias).getDataInicio());
//		assertEquals(LocalDate.now().plusDays(50), 
//				controller.getRepository().findById(ferias.getId()).get().getDataInicio());
//	}
//
//	@Test(expected = Exception.class)
//	public void testAlterarDataFeriasException() throws Exception {
//		controller.getRepository().save(ferias);
//		Ferias novaFerias = new Ferias();
//		novaFerias.setIdRequerimento(1l);
//		novaFerias.setIdColaborador(1l);
//		novaFerias.setIdGestor(2l);
//		novaFerias.setDiasRequisitados(30);
//		novaFerias.setDiasVendidos(0);
//		novaFerias.setDataInicio(LocalDate.now().plusDays(50));
//		novaFerias.setDataFim(LocalDate.now().plusDays(80));
//		novaFerias.setTipoFerias(TiposFerias.TOTAL);
//		novaFerias.setEstado(EstadoFerias.NAO_USUFRUIDA);
//		assertEquals(LocalDate.now().plusDays(50), 
//				controller.alterarDataFerias(-5l, novaFerias).getDataInicio());
//	}
//	
//	@Test(expected = Exception.class)
//	public void testDeletarFeriasPorFerias() throws Exception {
//		controller.getRepository().save(ferias);
//		int tamanhoAntes = controller.buscarTodasFerias().size(); 
//		Long id = ferias.getId();
//		controller.deletarFeriasPorFerias(ferias);;
//		assertEquals(tamanhoAntes-1, controller.buscarTodasFerias().size());
//		controller.buscarPorIdRequerimento(id);
//	}
//
//	@Test(expected = Exception.class)
//	public void testDeletarFeriasPorId() throws Exception {
//		controller.getRepository().save(ferias);
//		int tamanhoAntes = controller.buscarTodasFerias().size(); 
//		Long id = ferias.getId();
//		controller.deletarFeriasPorId(id);
//		assertEquals(tamanhoAntes-1, controller.buscarTodasFerias().size());
//		controller.buscarPorIdRequerimento(id);
//	}
//	
//	@Test
//	public void testBuscarPorIdGestorENaoUsufruidas() {
//		requerimento.setDataFimFeriasRequisitadas(LocalDate.now().plusDays(60));
//		controller.criarFerias(requerimento);
//		requerimento.setDataInicioFeriasRequisitadas(LocalDate.now().plusDays(40));
//		controller.criarFerias(requerimento);
//		List<Ferias> listaFerias = controller.buscarPorIdGestorENaoUsufruidas(requerimento.getIdGestor());
//		int tamanhoLista = listaFerias.size();
//		assertEquals(LocalDate.now().plusDays(60), listaFerias.get(tamanhoLista - 2).getDataFim());
//		assertEquals(LocalDate.now().plusDays(40), listaFerias.get(tamanhoLista - 1).getDataInicio());
//	}
//	
//	@Test
//	public void testBuscarPorIdColaboradorENaoUsufruidas() {
//		int tamanhoAntes = controller.buscarPorIdColaboradorENaoUsufruidas(ferias.getIdColaborador()).size();
//		ferias.setEstado(EstadoFerias.NAO_USUFRUIDA);
//		controller.getRepository().save(ferias);
//		Ferias novaFerias = new Ferias();
//		novaFerias.setIdRequerimento(1l);
//		novaFerias.setIdColaborador(1l);
//		novaFerias.setIdGestor(2l);
//		novaFerias.setDiasRequisitados(30);
//		novaFerias.setDiasVendidos(0);
//		novaFerias.setDataInicio(LocalDate.now().plusDays(50));
//		novaFerias.setDataFim(LocalDate.now().plusDays(80));
//		novaFerias.setTipoFerias(TiposFerias.TOTAL);
//		novaFerias.setEstado(EstadoFerias.USUFRUIDA);
//		controller.getRepository().save(novaFerias);
//		int tamanhoDepois = controller.buscarPorIdColaboradorENaoUsufruidas(ferias.getIdColaborador()).size();
//		assertEquals(tamanhoAntes + 1, tamanhoDepois);
//		
//	}


	
}