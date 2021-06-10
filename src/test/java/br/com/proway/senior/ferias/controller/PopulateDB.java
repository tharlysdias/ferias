package br.com.proway.senior.ferias.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PopulateDB {

	@Autowired
	private FeriasRepository feriasRepository;
	@Autowired
	private RequerimentoRepository requerimentoRepository;
	@Autowired
	private SaldoRepository saldoRepository;

	// Saldo 1
	private static Saldo saldo1;
	private static Long saldo1_idColaborador = 666l;
	private static Double saldo1_diasDisponiveisDeFerias  = 45.0;
	
	// Saldo 2
	private static Saldo saldo2;
	private static Long saldo2_idColaborador = 48596l;
	private static Double saldo2_diasDisponiveisDeFerias  = 10.0;
	
	// Saldo 3
	private static Saldo saldo3;
	private static Long saldo3_idColaborador = 4523l;
	private static Double saldo3_diasDisponiveisDeFerias  = 30.0;
	
	// Saldo 4
	private static Saldo saldo4;
	private static Long saldo4_idColaborador = 1l;
	private static Double saldo4_diasDisponiveisDeFerias  = 5.0;
	
	
	// Requerimento 1
	private static Requerimento requerimento1;
	private static Long requerimento1_idGestor = 444l;
	
	// Requerimento 2
	private static Requerimento requerimento2;
	
	// Requerimento 3
	private static Requerimento requerimento3;
	
	// Requerimento 4
	private static Requerimento requerimento4;
	
	
	// Requerimento 5
	private static Requerimento requerimento5;
	
	// Requerimento 6
	private static Requerimento requerimento6;
	
	// Requerimento 7
	private static Requerimento requerimento7;
	
	// Requerimento 8
	private static Requerimento requerimento8;
	
	
	@Test
	public void populate() {
		saldo1 = new Saldo(saldo1_idColaborador, saldo1_diasDisponiveisDeFerias, LocalDate.now());
		saldo1 = saldoRepository.saveAndFlush(saldo1);
		saldo2 = new Saldo(saldo2_idColaborador, saldo2_diasDisponiveisDeFerias, LocalDate.now());
		saldo2 = saldoRepository.saveAndFlush(saldo2);
		saldo3 = new Saldo(saldo3_idColaborador, saldo3_diasDisponiveisDeFerias, LocalDate.now());
		saldo3 = saldoRepository.saveAndFlush(saldo3);
		saldo4 = new Saldo(saldo4_idColaborador, saldo4_diasDisponiveisDeFerias, LocalDate.now());
		saldo4 = saldoRepository.saveAndFlush(saldo4);
		
//		requerimento1 = new Requerimento(saldo1, requerimento1_idGestor,);
		
	}

}
