package com.pet.foundation.pataamiga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PataamigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PataamigaApplication.class, args);
	}

}
