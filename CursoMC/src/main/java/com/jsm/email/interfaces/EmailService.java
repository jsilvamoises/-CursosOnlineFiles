package com.jsm.email.interfaces;

import org.springframework.mail.SimpleMailMessage;

import com.jsm.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);
	
	
}
