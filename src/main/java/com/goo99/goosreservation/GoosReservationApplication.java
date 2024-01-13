package com.goo99.goosreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoosReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoosReservationApplication.class, args);
	}

}
