package com.tommy.ypi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YpiApplication.class, args);
	}
}
