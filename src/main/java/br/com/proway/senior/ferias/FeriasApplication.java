package br.com.proway.senior.ferias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FeriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeriasApplication.class, args);
	}

}
