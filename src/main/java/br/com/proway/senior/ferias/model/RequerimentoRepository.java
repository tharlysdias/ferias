package br.com.proway.senior.ferias.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse	<guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias			<tharlys.dias@senior.com.br>
 */

@RepositoryRestResource(collectionResourceRel = "requerimento", path = "requerimento")
public interface RequerimentoRepository extends JpaRepository<Requerimento, Long> {

}