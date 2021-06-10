package br.com.proway.senior.ferias.controller;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
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
import br.com.proway.senior.ferias.service.RequerimentoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PopulateDB {

	@Autowired
	private FeriasService feriasService;

	@Autowired
	private RequerimentoService requerimentoService;

	@Autowired
	private FeriasRepository feriasRepository;
	@Autowired
	private RequerimentoRepository requerimentoRepository;
	@Autowired
	private SaldoRepository saldoRepository;

	// Saldo 1 (Principal)
	private static Saldo saldo1;
	private static Long saldo1_idColaborador = 666l;
	private static Double saldo1_diasDisponiveisDeFerias = 30.0;

	// Saldo 2
	private static Saldo saldo2;
	private static Long saldo2_idColaborador = 48596l;
	private static Double saldo2_diasDisponiveisDeFerias = 10.0;

	// Saldo 3
	private static Saldo saldo3;
	private static Long saldo3_idColaborador = 4523l;
	private static Double saldo3_diasDisponiveisDeFerias = 30.0;

	// Saldo 4
	private static Saldo saldo4;
	private static Long saldo4_idColaborador = 1l;
	private static Double saldo4_diasDisponiveisDeFerias = 5.0;

	// Requerimento 1 (Aberto pelo Saldo 1 há 3 dias atrás) [Aprovado porém férias
	// não usufruidas]
	private static Requerimento requerimento1;
	private static Long requerimento1_idGestor = 444l;
	private static LocalDate requerimento1_dataAbertura = LocalDate.now().minusDays(3);
	private static LocalDate requerimento1_prazoAnalises = LocalDate.now().plusDays(15);
	private static String requerimento1_mensagem = "Olá, gostaria de tirar uma férias de 30 dias daqui 4 meses.";
	private static String requerimento1_resposta = "";
	private static Integer requerimento1_diasRequisitados = 30;
	private static Integer requerimento1_diasVendidos = 0;
	private static LocalDate requerimento1_dataInicioFerias = LocalDate.now().plusMonths(4);

	// Requerimento 2 (Aberto pelo Saldo 1 há 1 ano atrás) [ férias fracionada parte
	// 1 - Aprovado e usufruidas]
	private static Requerimento requerimento2;
	private static Long requerimento2_idGestor = 444l;
	private static LocalDate requerimento2_dataAbertura = LocalDate.now().minusYears(1);
	private static LocalDate requerimento2_prazoAnalises = LocalDate.now().minusYears(1).plusDays(15);
	private static String requerimento2_mensagem = "Olá, gostaria de tirar uma férias de 15 dias daqui 1 mes.";
	private static String requerimento2_resposta = "";
	private static Integer requerimento2_diasRequisitados = 15;
	private static Integer requerimento2_diasVendidos = 0;
	private static LocalDate requerimento2_dataInicioFerias = LocalDate.now().minusYears(1).plusMonths(1);

	// Requerimento 3 (Aberto pelo Saldo 1 há 1 ano atrás) [ férias fracionada parte
	// 2 - Aprovado e usufruidas]
	private static Requerimento requerimento3;
	private static Long requerimento3_idGestor = 444l;
	private static LocalDate requerimento3_dataAbertura = LocalDate.now().minusYears(1).plusMonths(2);
	private static LocalDate requerimento3_prazoAnalises = LocalDate.now().minusYears(1).plusMonths(2).plusDays(15);;
	private static String requerimento3_mensagem = "Olá, gostaria de tirar uma férias de 15 dias daqui 2 meses.";
	private static String requerimento3_resposta = "";
	private static Integer requerimento3_diasRequisitados = 15;
	private static Integer requerimento3_diasVendidos = 0;
	private static LocalDate requerimento3_dataInicioFerias = LocalDate.now().minusYears(1).plusMonths(4);

	// Requerimento 4 (Aberto pelo Saldo 2 hoje) [ Aberto mas vai ser reprovado por
	// que não tinha saldo]
	private static Requerimento requerimento4;
	private static Long requerimento4_idGestor = 444l;
	private static LocalDate requerimento4_dataAbertura = LocalDate.now();
	private static LocalDate requerimento4_prazoAnalises = LocalDate.now().plusDays(15);
	private static String requerimento4_mensagem = "Olá, gostaria de tirar uma férias de 20 dias daqui 1 mes. E vender 10 dias";
	private static String requerimento4_resposta = "";
	private static Integer requerimento4_diasRequisitados = 20;
	private static Integer requerimento4_diasVendidos = 10;
	private static LocalDate requerimento4_dataInicioFerias = LocalDate.now().plusMonths(1);

	// Requerimento 5 (Aberto pelo Saldo 3 14 dias atras) [Á ser aprovado]
	private static Requerimento requerimento5;
	private static Long requerimento5_idGestor = 444l;
	private static LocalDate requerimento5_dataAbertura = LocalDate.now().minusDays(14);
	private static LocalDate requerimento5_prazoAnalises = LocalDate.now().plusDays(15);
	private static String requerimento5_mensagem = "Olá, gostaria de tirar uma férias de 20 dias daqui 1 mess. E vender 10.";
	private static String requerimento5_resposta = "";
	private static Integer requerimento5_diasRequisitados = 20;
	private static Integer requerimento5_diasVendidos = 10;
	private static LocalDate requerimento5_dataInicioFerias = LocalDate.now().plusMonths(1);

	// Requerimento 6 (Aberto pelo Saldo 4 este ano) [fracionado parte 1 poderia ser
	// aprovado, mas deve ser recusado por que vai ter dias insuficientes no
	// terceiro]
	private static Requerimento requerimento6;
	private static Long requerimento6_idGestor = 444l;
	private static LocalDate requerimento6_dataAbertura = LocalDate.now().minusDays(7);
	private static LocalDate requerimento6_prazoAnalises = LocalDate.now().minusDays(7).plusDays(15);
	private static String requerimento6_mensagem = "Olá, gostaria de tirar uma férias fracionada em 3. Uma de 15, Uma de 13 e Uma de 2. Esta é a primeira que será daqui 2 meses.";
	private static String requerimento6_resposta = "";
	private static Integer requerimento6_diasRequisitados = 15;
	private static Integer requerimento6_diasVendidos = 0;
	private static LocalDate requerimento6_dataInicioFerias = LocalDate.now().plusMonths(2);

	// Requerimento 7 (Aberto pelo Saldo 4 este ano) [fracionado parte 1 poderia ser
	// aprovado, deve ser recusado por que vai ter dias insuficientes no terceiro]
	private static Requerimento requerimento7;
	private static Long requerimento7_idGestor = 444l;
	private static LocalDate requerimento7_dataAbertura = LocalDate.now().minusDays(7);
	private static LocalDate requerimento7_prazoAnalises = LocalDate.now().minusDays(7).plusDays(15);
	private static String requerimento7_mensagem = "Parte 2 do pedido anterior. Essa tera 13 dias e será daqui 3 meses.";
	private static String requerimento7_resposta = "";
	private static Integer requerimento7_diasRequisitados = 13;
	private static Integer requerimento7_diasVendidos = 0;
	private static LocalDate requerimento7_dataInicioFerias = LocalDate.now().plusMonths(3);

	// Requerimento 8 (Aberto pelo Saldo 4 este ano) [fracionado parte 1 deve ser
	// recuraso por que o minimo é 5.]
	private static Requerimento requerimento8;
	private static Long requerimento8_idGestor = 444l;
	private static LocalDate requerimento8_dataAbertura = LocalDate.now().minusDays(7);
	private static LocalDate requerimento8_prazoAnalises = LocalDate.now().minusDays(7).plusDays(15);
	private static String requerimento8_mensagem = "Parte 3 do pedido anterior. Essa tera 2 dias e será daqui 4 meses.";
	private static String requerimento8_resposta = "";
	private static Integer requerimento8_diasRequisitados = 2;
	private static Integer requerimento8_diasVendidos = 0;
	private static LocalDate requerimento8_dataInicioFerias = LocalDate.now().plusMonths(4);

	// Requerimento 9 (Aberto pelo Saldo 1 este ano há 4 dias) [já foi recusado por
	// gestor não deixar ele tirar no dia especificado.]
	private static Requerimento requerimento9;
	private static Long requerimento9_idGestor = 444l;
	private static LocalDate requerimento9_dataAbertura = LocalDate.now().minusDays(4);
	private static LocalDate requerimento9_prazoAnalises = LocalDate.now().minusDays(4).plusDays(15);
	private static String requerimento9_mensagem = "Olá, gostaria de tirar uma férias de 30 dias este mes, daqui a 3 dias.";
	private static String requerimento9_resposta = "";
	private static Integer requerimento9_diasRequisitados = 30;
	private static Integer requerimento9_diasVendidos = 0;
	private static LocalDate requerimento9_dataInicioFerias = LocalDate.now().plusDays(3);

	// Ferias
	private static Ferias ferias1;
	private static Ferias ferias2;
	private static Ferias ferias3;
	private static Ferias ferias4;

	@Test
	public void populate() throws Exception {
		// Cria os 4 saldos.
		saldo1 = new Saldo(saldo1_idColaborador, saldo1_diasDisponiveisDeFerias, LocalDate.now());
		saldo1 = saldoRepository.saveAndFlush(saldo1);
		saldo2 = new Saldo(saldo2_idColaborador, saldo2_diasDisponiveisDeFerias, LocalDate.now());
		saldo2 = saldoRepository.saveAndFlush(saldo2);
		saldo3 = new Saldo(saldo3_idColaborador, saldo3_diasDisponiveisDeFerias, LocalDate.now());
		saldo3 = saldoRepository.saveAndFlush(saldo3);
		saldo4 = new Saldo(saldo4_idColaborador, saldo4_diasDisponiveisDeFerias, LocalDate.now());
		saldo4 = saldoRepository.saveAndFlush(saldo4);

		// Requerimento 1 (Aberto pelo Saldo 1 há 3 dias atrás) [Aprovado porém férias
		// não usufruidas]
		// Cria o requerimento 1.
		requerimento1 = new Requerimento(saldo1, requerimento1_idGestor, requerimento1_dataAbertura,
				requerimento1_prazoAnalises, requerimento1_mensagem, requerimento1_resposta,
				requerimento1_diasRequisitados, requerimento1_diasVendidos, requerimento1_dataInicioFerias);
		requerimento1 = requerimentoRepository.saveAndFlush(requerimento1);

		// Aprovar requerimento 1 e atualizar referencia para férias1.
		requerimento1.setResposta("Tudo certo! Aprovado.");
		requerimento1.setEstado(EstadosRequerimento.APROVADO);
		requerimento1.setDataFechamento(LocalDate.now());
		requerimentoService.avaliarRequerimento(requerimento1.getId(), requerimento1.getEstado());
		requerimento1 = requerimentoService.atualizarRequerimento(requerimento1);
		ferias1 = feriasRepository.findByRequerimento(requerimento1).get();

		// Requerimento 2 (Aberto pelo Saldo 1 há 1 ano atrás) [ férias fracionada parte
		// 1 - Aprovado e usufruidas]
		// Cria o requerimento 2.
		requerimento2 = new Requerimento(saldo1, requerimento2_idGestor, requerimento2_dataAbertura,
				requerimento2_prazoAnalises, requerimento2_mensagem, requerimento2_resposta,
				requerimento2_diasRequisitados, requerimento2_diasVendidos, requerimento2_dataInicioFerias);
		requerimento2 = requerimentoRepository.saveAndFlush(requerimento2);
		// Aprovar requerimento 2 e atualizar referencia para férias2. Fazer ferias 2
		// ser usufruida.
		requerimento2.setResposta("Tudo certo! Boas Férias.");
		requerimento2.setEstado(EstadosRequerimento.APROVADO);
		requerimento2.setDataFechamento(LocalDate.now());
		requerimentoService.avaliarRequerimento(requerimento2.getId(), requerimento2.getEstado());
		requerimento2 = requerimentoService.atualizarRequerimento(requerimento2);
		ferias2 = feriasRepository.findByRequerimento(requerimento2).get();
		ferias2.setEstado(EstadoFerias.USUFRUIDA);
		feriasService.alterarFerias(ferias2, ferias2.getId());

		// Requerimento 3 (Aberto pelo Saldo 1 há 1 ano atrás) [ férias fracionada parte
		// 2 - Aprovado e usufruidas]
		// Cria o requerimento 3.
		requerimento3 = new Requerimento(saldo1, requerimento3_idGestor, requerimento3_dataAbertura,
				requerimento3_prazoAnalises, requerimento3_mensagem, requerimento3_resposta,
				requerimento3_diasRequisitados, requerimento3_diasVendidos, requerimento3_dataInicioFerias);
		requerimento3 = requerimentoRepository.saveAndFlush(requerimento3);
		// Aprovar requerimento 3 e atualizar referencia para férias3. Fazer ferias 3
		// ser usufruida.
		requerimento3.setResposta("Aprovado! Boas Férias.");
		requerimento3.setEstado(EstadosRequerimento.APROVADO);
		requerimento3.setDataFechamento(LocalDate.now());
		requerimentoService.avaliarRequerimento(requerimento3.getId(), requerimento3.getEstado());
		requerimento3 = requerimentoService.atualizarRequerimento(requerimento3);
		ferias3 = feriasRepository.findByRequerimento(requerimento3).get();
		ferias3.setEstado(EstadoFerias.USUFRUIDA);
		feriasService.alterarFerias(ferias3, ferias3.getId());

		// Requerimento 4 (Aberto pelo Saldo 2 hoje) [ Aberto mas vai ser reprovado por
		// que não tinha saldo]
		// Cria o requerimento 4.
		requerimento4 = new Requerimento(saldo2, requerimento4_idGestor, requerimento4_dataAbertura,
				requerimento4_prazoAnalises, requerimento4_mensagem, requerimento4_resposta,
				requerimento4_diasRequisitados, requerimento4_diasVendidos, requerimento4_dataInicioFerias);
		requerimento4 = requerimentoRepository.saveAndFlush(requerimento4);

		// Requerimento 5 (Aberto pelo Saldo 3 14 dias atras) [Á ser aprovado]
		// Cria o requerimento 5.
		requerimento5 = new Requerimento(saldo3, requerimento5_idGestor, requerimento5_dataAbertura,
				requerimento5_prazoAnalises, requerimento5_mensagem, requerimento5_resposta,
				requerimento5_diasRequisitados, requerimento5_diasVendidos, requerimento5_dataInicioFerias);
		requerimento5 = requerimentoRepository.saveAndFlush(requerimento5);

		// Requerimento 6 (Aberto pelo Saldo 4 este ano) [fracionado parte 1 poderia ser
		// aprovado, mas deve ser recusado por que vai ter dias insuficientes no
		// terceiro]
		// Cria o requerimento 6.
		requerimento6 = new Requerimento(saldo4, requerimento6_idGestor, requerimento6_dataAbertura,
				requerimento6_prazoAnalises, requerimento6_mensagem, requerimento6_resposta,
				requerimento6_diasRequisitados, requerimento6_diasVendidos, requerimento6_dataInicioFerias);
		requerimento6 = requerimentoRepository.saveAndFlush(requerimento6);

		// Requerimento 7 (Aberto pelo Saldo 4 este ano) [fracionado parte 1 poderia ser
		// aprovado, deve ser recusado por que vai ter dias insuficientes no terceiro]
		// Cria o requerimento 7.
		requerimento7 = new Requerimento(saldo4, requerimento7_idGestor, requerimento7_dataAbertura,
				requerimento7_prazoAnalises, requerimento7_mensagem, requerimento7_resposta,
				requerimento7_diasRequisitados, requerimento7_diasVendidos, requerimento7_dataInicioFerias);
		requerimento7 = requerimentoRepository.saveAndFlush(requerimento7);

		// Requerimento 8 (Aberto pelo Saldo 4 este ano) [fracionado parte 1 deve ser
		// recuraso por que o minimo é 5.]
		// Cria o requerimento 8.
		requerimento8 = new Requerimento(saldo4, requerimento8_idGestor, requerimento8_dataAbertura,
				requerimento8_prazoAnalises, requerimento8_mensagem, requerimento8_resposta,
				requerimento8_diasRequisitados, requerimento8_diasVendidos, requerimento8_dataInicioFerias);
		requerimento8 = requerimentoRepository.saveAndFlush(requerimento8);

		// Requerimento 9 (Aberto pelo Saldo 1 este ano há 4 dias) [já foi recusado por
		// gestor não deixar ele tirar no dia especificado.]
		// Cria o requerimento 9.
		requerimento9 = new Requerimento(saldo1, requerimento9_idGestor, requerimento9_dataAbertura,
				requerimento9_prazoAnalises, requerimento9_mensagem, requerimento9_resposta,
				requerimento9_diasRequisitados, requerimento9_diasVendidos, requerimento9_dataInicioFerias);
		requerimento9 = requerimentoRepository.saveAndFlush(requerimento9);
		// Recusar requerimento 9.
		requerimento9.setResposta(
				"Recusado pois precisamos de voce nesse dia, já existem outros membros da equipe em férias.");
		requerimento9.setEstado(EstadosRequerimento.RECUSADO);
		requerimento9.setDataFechamento(LocalDate.now());
		requerimento9 = requerimentoService.atualizarRequerimento(requerimento9);

	}

}
