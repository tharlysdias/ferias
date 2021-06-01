package br.com.proway.senior.ferias.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

/**
 * Repository do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse	<guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias			<tharlys.dias@senior.com.br>
 */

//@RepositoryRestResource(collectionResourceRel = "requerimento", path = "requerimento")
@EnableJpaRepositories
public interface RequerimentoRepository extends JpaRepository<Requerimento, Long> {

	public List<Requerimento> findAllByIdGestor(Long idGestor);
  
	public List<Requerimento> findBySaldo(Saldo saldo);
	
	public List<Requerimento> findAllByEstadoAndSaldo(EstadosRequerimento estado, Saldo saldo);
	
}