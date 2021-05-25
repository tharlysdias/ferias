package br.com.proway.senior.ferias;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySpringConfig {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
