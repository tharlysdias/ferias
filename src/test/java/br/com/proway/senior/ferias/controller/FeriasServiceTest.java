package br.com.proway.senior.ferias.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.Ferias;
import br.com.proway.senior.ferias.model.FeriasRepository;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.model.enums.EstadoFerias;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.service.FeriasService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeriasServiceTest {

	@Autowired
	private FeriasService feriasService;

	@Autowired
	private FeriasRepository feriasRepository;
	@Autowired
	private RequerimentoRepository requerimentoRepository;
	@Autowired
	private SaldoRepository saldoRepository;

	private static Long idGestor = 444l;
	private static Long idColaboradorDoSaldo1 = 666l;
	private static Saldo saldo1;
	private static Requerimento requerimento1;
	private static Ferias ferias1;
	private static Long idColaboradorDoSaldo2 = 2784l;
	private static Saldo saldo2;
	private static Requerimento requerimento2;
	private static Ferias ferias2;

	@Test
	public void testAPopulateDB() throws Exception {
		feriasService = new FeriasService(feriasRepository, requerimentoRepository, saldoRepository);
		saldo1 = new Saldo(idColaboradorDoSaldo1, 30.0, LocalDate.now());
		saldo1 = saldoRepository.saveAndFlush(saldo1);
		requerimento1 = new Requerimento();
		requerimento1.setSaldo(saldo1);
		requerimento1.setIdGestor(idGestor);
		requerimento1.setDataAbertura(LocalDate.now());
		requerimento1.setPrazoAnalise(LocalDate.now().plusDays(15));
		requerimento1.setEstado(EstadosRequerimento.PENDENTE);
		requerimento1.setDiasRequisitados(30);
		requerimento1.setDiasVendidos(40);
		requerimento1.setDataInicioFerias(LocalDate.now().plusMonths(1));
		requerimento1 = requerimentoRepository.saveAndFlush(requerimento1);

		saldo2 = new Saldo(idColaboradorDoSaldo2, 60.0, LocalDate.now().minusMonths(12));
		saldo2 = saldoRepository.saveAndFlush(saldo2);
		requerimento2 = new Requerimento(saldo2, idGestor, LocalDate.now(), LocalDate.now().plusDays(10),
				"Mensagem de teste", "Resposta de teste", 15, 15, LocalDate.now().plusMonths(4));
		requerimento2 = requerimentoRepository.saveAndFlush(requerimento2);
		feriasService.criarFerias(requerimento2);
		ferias2 = feriasRepository.findByRequerimento(requerimento2).get();
		ferias2.setEstado(EstadoFerias.CANCELADA);
		feriasService.alterarFerias(ferias2, ferias2.getId());
	}

	@Test
	public void testBCriarFeriasEBuscarPorId() {
		feriasService.criarFerias(requerimento1);
		ferias1 = feriasRepository.findByRequerimento(requerimento1).get();
		assertEquals(ferias1.getEstado(), EstadoFerias.A_USUFRUIR);
	}

	@Test
	public void testCBuscarTodasFerias() {
		ArrayList<Ferias> lista = (ArrayList<Ferias>) feriasService.buscarTodasFerias();
		assertEquals(2, lista.size());
	}

	@Test
	public void testDBuscarTodasAsFeriasPorIdColaborador() {
		ArrayList<Ferias> lista = (ArrayList<Ferias>) feriasService.buscarTodasAsFeriasPorIdColaborador(idColaboradorDoSaldo2);
		assertEquals(1, lista.size());
	}

	@Test
	public void testEBuscarFeriasAUsufruirPorIdColaborador() {
		ArrayList<Ferias> listaColab1 = (ArrayList<Ferias>) feriasService.buscarFeriasAUsufruirPorIdColaborador(idColaboradorDoSaldo1);
		ArrayList<Ferias> listaColab2 = (ArrayList<Ferias>) feriasService.buscarFeriasAUsufruirPorIdColaborador(idColaboradorDoSaldo2);
		assertEquals(1, listaColab1.size());
		assertEquals(0, listaColab2.size());
	}

	@Test
	public void testFBuscarFeriasAUsufruirDosSubordinados() throws Exception {
		ferias1.setEstado(EstadoFerias.A_USUFRUIR);
		feriasService.alterarFerias(ferias1, ferias1.getId());
		ferias2.setEstado(EstadoFerias.A_USUFRUIR);
		feriasService.alterarFerias(ferias2, ferias2.getId());
		ArrayList<Ferias> lista = feriasService.buscarFeriasAUsufruirDosSubordinados(idGestor);
		assertEquals(2, lista.size());
	}

	@Test
	public void testGBuscarFeriasUsufruindoDosSubordinados() throws Exception {
		ferias1.setEstado(EstadoFerias.USUFRUINDO);
		feriasService.alterarFerias(ferias1, ferias1.getId());
		ferias2.setEstado(EstadoFerias.CANCELADA);
		feriasService.alterarFerias(ferias2, ferias2.getId());
		ArrayList<Ferias> lista = feriasService.buscarFeriasUsufruindoDosSubordinados(idGestor);
		assertEquals(1, lista.size());
	}

	@Test
	public void testHAlterarFerias() throws Exception {
		ferias1.setEstado(EstadoFerias.USUFRUIDA);
		feriasService.alterarFerias(ferias1, ferias1.getId());
		ferias1 = feriasRepository.findByRequerimento(requerimento1).get();
		assertEquals(ferias1.getEstado(), EstadoFerias.USUFRUIDA);
	}

	@Test
	public void testIDeletarFeriasPorId() throws Exception {
		feriasRepository.delete(ferias2);
		ArrayList<Ferias> lista = (ArrayList<Ferias>) feriasService.buscarTodasFerias();
		assertEquals(1, lista.size());
	}

	@Test
	public void testXCleanDB() {
		feriasRepository.delete(ferias1);
		requerimentoRepository.delete(requerimento1);
		requerimentoRepository.delete(requerimento2);
		saldoRepository.delete(saldo1);
		saldoRepository.delete(saldo2);
	}

}