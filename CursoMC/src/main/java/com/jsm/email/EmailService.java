package com.jsm.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService<T> {

	void sendTextPlainEmail(T object,Object...objects);

	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(T object,Object...objects);

	void sendHtmlEmail(MimeMessage mm);
	
	String htmlFromTemplate(String uri,String objVariable, T obj, Object...objects );

}
