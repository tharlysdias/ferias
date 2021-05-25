package br.com.proway.senior.ferias.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "requerimento", path="requerimento")
public interface RequerimentoRepository extends JpaRepository<Requerimento, Long> {
	
} 