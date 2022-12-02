package br.com.devdojo.awesome.devdojospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class DevdojoSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevdojoSpringbootApplication.class, args);
	}
}


