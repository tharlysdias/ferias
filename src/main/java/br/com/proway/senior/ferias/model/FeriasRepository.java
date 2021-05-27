package br.com.proway.senior.ferias.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.proway.senior.ferias.model.enums.EstadoFerias;

@EnableJpaRepositories
public interface FeriasRepository extends JpaRepository<Ferias, Long> {

	public Ferias findByRequerimento(Requerimento requerimento);
	
	public List<Ferias> findByRequerimentoAndEstado(Requerimento requerimento, EstadoFerias estado);
	
}