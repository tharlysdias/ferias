package br.com.proway.senior.ferias.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.proway.senior.ferias.model.FeriasRepository;
import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.Saldo;
import br.com.proway.senior.ferias.model.SaldoRepository;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;
import br.com.proway.senior.ferias.service.RequerimentoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequerimentoServiceTest {

	@Autowired
	private RequerimentoService requerimentoService;

	@Autowired
	private FeriasRepository feriasRepository;
	@Autowired
	private RequerimentoRepository requerimentoRepository;
	@Autowired
	private SaldoRepository saldoRepository;

	private static Long idGestor1 = 88l;
	private static Long idGestor2 = 99l;
	private static Long idColaboradorDoSaldo1 = 2015l;
	private static Saldo saldo1;
	private static Requerimento requerimento1;
	private static Long idColaboradorDoSaldo2 = 3215l;
	private static Saldo saldo2;
	private static Requerimento requerimento2;
	private static Long idColaboradorDoSaldo3 = 4851l;
	private static Saldo saldo3;
	private static Requerimento requerimento3;
	private static Requerimento requerimento4;

	@Test
	public void testAPopulateDB() {
		saldo1 = new Saldo(idColaboradorDoSaldo1, 45.0, LocalDate.now().minusMonths(6));
		requerimento1 = new Requerimento(saldo1, idGestor1, LocalDate.now(), LocalDate.now().plusDays(15),
				"Teste 1 Req", "", 12, 0, LocalDate.now().plusMonths(3));
		saldo2 = new Saldo(idColaboradorDoSaldo2, 32.0, LocalDate.now().minusMonths(24));
		requerimento2 = new Requerimento(saldo2, idGestor1, LocalDate.now(), LocalDate.now().plusDays(20),
				"Teste 2 Req", "", 18, 12, LocalDate.now().plusMonths(1));
		saldoRepository.save(saldo2);
		requerimentoService.criarRequerimento(requerimento2);
		saldo3 = new Saldo(idColaboradorDoSaldo3, 20.0, LocalDate.now().minusMonths(12));
		requerimento3 = new Requerimento(saldo3, idGestor1, LocalDate.now(), LocalDate.now().plusDays(18),
				"Teste 3 Req", "", 30, 0, LocalDate.now().plusMonths(2));
		saldoRepository.save(saldo3);
		requerimentoService.criarRequerimento(requerimento3);
		saldo3 = saldoRepository.findById(saldo3.getId()).get();
		requerimento4 = new Requerimento(saldo3, idGestor2, LocalDate.now(), LocalDate.now().plusDays(18),
				"Teste 3 Part 2 Req", "", 2, 1, LocalDate.now().plusMonths(2));
		requerimentoService.criarRequerimento(requerimento4);
	}

	@Test
	public void testBCriarRequerimentoEBuscarPorId() {
		saldoRepository.save(saldo1);
		requerimentoService.criarRequerimento(requerimento1);
		requerimento1 = requerimentoRepository.getById(requerimento1.getId());
		assertEquals(requerimento1.getEstado(), EstadosRequerimento.PENDENTE);
		assertEquals(requerimento1.getSaldo().getIdColaborador(), idColaboradorDoSaldo1);
	}

	@Test
	public void testCBuscarTodosRequerimentos() {
		assertEquals(4, requerimentoService.buscarTodosRequerimentos().size());
	}

	@Test
	public void testDBuscarRequerimentoPorIdGestor() {
		assertEquals(3, requerimentoService.buscarRequerimentoPorIdGestor(idGestor1).size());
	}

	@Test
	public void testEBuscarRequerimentoPorIdColaborador() {
		assertEquals(2, requerimentoService.buscarRequerimentoPorIdColaborador(idColaboradorDoSaldo3).size());
	}

	@Test
	public void testFBuscarRequerimentoPorIdColaboradorEEstado() {
		requerimento3.setEstado(EstadosRequerimento.RECUSADO);
		requerimentoService.atualizarRequerimento(requerimento3);
		assertEquals(1, requerimentoService
				.buscarRequerimentoPorIdColaboradorEEstado(idColaboradorDoSaldo3, EstadosRequerimento.RECUSADO).size());
		assertEquals(1, requerimentoService
				.buscarRequerimentoPorIdColaboradorEEstado(idColaboradorDoSaldo3, EstadosRequerimento.PENDENTE).size());

	}

	@Test
	public void testGAvaliarRequerimento() throws Exception {
		requerimento3.setEstado(EstadosRequerimento.PENDENTE);
		requerimento3 = requerimentoService.atualizarRequerimento(requerimento3);
		requerimentoService.avaliarRequerimento(requerimento3.getId(), EstadosRequerimento.APROVADO);
		requerimentoService.avaliarRequerimento(requerimento4.getId(), EstadosRequerimento.RECUSADO);
		assertEquals(EstadosRequerimento.APROVADO, requerimentoService.buscarRequerimentoPorId(requerimento3.getId()).getEstado());
		assertEquals(EstadosRequerimento.RECUSADO, requerimentoService.buscarRequerimentoPorId(requerimento4.getId()).getEstado());
	}

	@Test
	public void testHAtualizarRequerimento() {
		String resposta = "RESPOSTA do gestor.";
		requerimento2.setResposta(resposta);
		requerimento2.setEstado(EstadosRequerimento.PENDENTE);
		requerimentoService.atualizarRequerimento(requerimento2);
		assertEquals(resposta, requerimentoService.buscarRequerimentoPorId(requerimento2.getId()).getResposta());
	}

	@Test
	public void testIDesativarRequerimento() {
		requerimentoService.desativarRequerimento(requerimento2.getId());
		assertEquals(3, requerimentoService.buscarTodosRequerimentos().size());
	}

	@Test
	public void testJDeletarRequerimento() {
		requerimentoService.deletar(requerimento1.getId());
		assertEquals(2, requerimentoService.buscarTodosRequerimentos().size());
	}

	@Ignore
	public void testXCleanDB() {
		feriasRepository.delete(feriasRepository.findByRequerimento(requerimento3).get());
		requerimentoRepository.delete(requerimento3);
		requerimentoRepository.delete(requerimento4);
		saldoRepository.delete(saldo1);
		saldoRepository.delete(saldo2);
		saldoRepository.delete(saldo3);
	}

}
