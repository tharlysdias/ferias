package br.com.proway.senior.ferias.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {
	
	public Saldo findByIdColaborador(Long idColaborador);
}