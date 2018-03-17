package com.jsm.email.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.jsm.domain.Pedido;
import com.jsm.email.interfaces.EmailService;

public abstract class AbstractEmailServiceImpl implements EmailService {

	@Value("${app.default.sender}")
	private String defaultSender;

	@Value("${app.default.recipient}")
	private String defaultRecipient;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailFromPedido(pedido);
		sendEmail(sm);

	}

	private SimpleMailMessage prepareSimpleMailFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(defaultSender);
		sm.setSubject("Confirmação de Pedido: "+pedido.getId());
		sm.setSentDate(Calendar.getInstance().getTime());
		sm.setText(pedido.toString());
		
		return sm;
	}

	

}
