package com.jsm.email.interfaces;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService<T> {

	void sendTextPlainEmail(T object);

	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(T object);

	void sendHtmlEmail(MimeMessage mm);
	
	String htmlFromTemplate(String uri,String objVariable, T obj, Object...objects );

}
