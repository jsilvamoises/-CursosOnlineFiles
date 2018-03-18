package com.jsm.email.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.jsm.email.EmailService;
import com.jsm.email.smtp.PedidoEmailService;

@Component
public class PedidoEmailBean<T> {
	
	@Bean(name="pedidoEmailService")
	public EmailService emailService() {
		return new PedidoEmailService();
	}

}
