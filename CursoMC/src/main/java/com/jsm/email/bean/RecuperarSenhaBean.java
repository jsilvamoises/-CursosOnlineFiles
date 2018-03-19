package com.jsm.email.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.jsm.email.EmailService;
import com.jsm.email.smtp.PedidoEmailService;
import com.jsm.email.smtp.RecuperarSenhaServiceImpl;


@Component
public class RecuperarSenhaBean {
	@Bean(name="recuperarSenha")
	public EmailService emailService() {
		return new RecuperarSenhaServiceImpl();
	}

}
