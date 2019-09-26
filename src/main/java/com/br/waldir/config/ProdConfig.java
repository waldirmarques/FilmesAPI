package com.br.waldir.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.waldir.services.DBService;

@Configuration
@Profile("prod")
public class ProdConfig {
	private final String CREATE = "create";
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() {
		return (strategy.equals(CREATE)) ? dbService.instantiateDatabase() : false;
	}
}