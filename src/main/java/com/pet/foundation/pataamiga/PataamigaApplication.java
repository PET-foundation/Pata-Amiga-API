package com.pet.foundation.pataamiga;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default server url")})
public class PataamigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PataamigaApplication.class, args);
	}

}
