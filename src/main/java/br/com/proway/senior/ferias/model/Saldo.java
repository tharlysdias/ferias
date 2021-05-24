package br.com.proway.senior.ferias.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Saldo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int idColaborador;

	private int diasDisponiveisDeFerias;
	
	private LocalDate dataAdmissao;

	public Saldo() {
	}
}
