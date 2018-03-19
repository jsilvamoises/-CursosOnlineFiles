package com.jsm.security.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsm.domain.Cliente;
import com.jsm.email.EmailService;
import com.jsm.repositories.ClienteRepository;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Random rand = new Random();

	@Autowired
	@Qualifier("recuperarSenha")
	private EmailService<Cliente> emailService;

	public void sendNewPassord(String email) {
		Optional<Cliente> cliente = repository.findByEmail(email);
		if (!cliente.isPresent()) {
			throw new ObjectNotFoundException("Email não cadastrado");
		}

		String newPass = newPassword();
		String encodedPass = encoder.encode(newPass);
		encoder.encode(newPass);
		System.out.println("New Pass:"+newPass);
		cliente.get().setPassword(encodedPass);
        repository.save(cliente.get());
		
        cliente.get().setPassword(newPass);
        emailService.sendTextPlainEmail(cliente.get());
        

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = ramdomChar();
		}
        
		return new String(vet);
	}
    // https://unicode-table.com/pt/
	private char ramdomChar() {
		int opt = rand.nextInt(3);
		
//		if(opt == 0) {
//			return (char)(rand.nextInt(10)+48);
//		}else if(opt == 1) {
//			return (char)(rand.nextInt(26)+65);
//		}else {
//			return (char)(rand.nextInt(26)+97);
//		}
			
		switch (opt) {
		case 0:
			return (char)(rand.nextInt(10)+48);							
		case 1:
			return (char)(rand.nextInt(26)+65);					
		case 2:
			return (char)(rand.nextInt(26)+97);				
		default:
			return (char)(rand.nextInt((26)+97));	
		}

		
	}
}
