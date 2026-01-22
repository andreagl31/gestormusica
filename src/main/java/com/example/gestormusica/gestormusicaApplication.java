package com.example.gestormusica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.gestormusica")
public class gestormusicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(gestormusicaApplication.class, args);
	}

}
