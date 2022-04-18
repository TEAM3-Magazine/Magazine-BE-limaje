package com.pbl2.pbl2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Pbl2Application {

	public static void main(String[] args) {
		SpringApplication.run(Pbl2Application.class, args);
	}

}
