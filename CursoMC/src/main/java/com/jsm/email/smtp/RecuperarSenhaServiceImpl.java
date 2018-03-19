package com.jsm.email.smtp;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import com.jsm.domain.Cliente;
import com.jsm.email.service.impl.AbstractEmailServiceImpl;

public class RecuperarSenhaServiceImpl extends AbstractEmailServiceImpl<Cliente> {

	@Override
	public void sendTextPlainEmail(Cliente object,Object...objects) {
		SimpleMailMessage sm = new SimpleMailMessage();
		process(object, sm);
		sendEmail(sm);
		
	}

	private void process(Cliente object, SimpleMailMessage sm) {
		sm.setTo(object.getEmail());
		sm.setFrom(getDefaultSender());
		sm.setSubject("Redefinicão de senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		
		String texto= "Sua senha foi reiniciada. Para acessar o sistema utilize a senha: "+object.getPassword();
		sm.setText(texto);
	}

	@Override
	public void sendHtmlEmail(Cliente object,Object...objects) {
		// TODO Auto-generated method stub
		
	}
	
	

}
