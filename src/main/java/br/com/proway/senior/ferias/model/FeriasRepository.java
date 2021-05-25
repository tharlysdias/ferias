package br.com.proway.senior.ferias.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.proway.senior.ferias.model.enums.EstadoFerias;

@EnableJpaRepositories
public interface FeriasRepository extends JpaRepository<Ferias, Long> {

	public List<Ferias> findAllByIdColaborador(Long id);
	
	public List<Ferias> findByIdGestorAndEstado(Long id, EstadoFerias estado);
}