package com.jsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	BCryptPasswordEncoder encoder;
	

	
	
	public static void main(String[] args) {
		
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 System.out.println(encoder.encode("123456"));
	
	}
	
	 
}
