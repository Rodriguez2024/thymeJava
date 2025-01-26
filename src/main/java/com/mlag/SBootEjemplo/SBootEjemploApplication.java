package com.mlag.SBootEjemplo;
/*
consultando en esta liga https://www.youtube.com/watch?v=MlFupKPyGf4

kill task
netstat -ano | findstr :8081
taskkill /PID <PID> /F
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SBootEjemploApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBootEjemploApplication.class, args);
	}
}
