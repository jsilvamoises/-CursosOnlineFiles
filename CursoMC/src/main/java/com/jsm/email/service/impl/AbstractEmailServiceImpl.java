package com.jsm.email.service.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.jsm.email.EmailService;

public abstract class AbstractEmailServiceImpl<T> implements EmailService<T> {
	private static Logger LOG = LoggerFactory.getLogger(AbstractEmailServiceImpl.class);

	@Autowired
	MailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaSender;

	@Value("${app.default.sender}")
	private String defaultSender;

	@Value("${app.default.recipient}")
	private String defaultRecipient;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("#### INICIANDO ENVIO DE EMAIL ####");
		LOG.info("#### ENVIANDO EMAIL ####"+msg.toString());
		mailSender.send(msg);		
		LOG.info("#### EMAIL ENVIADO ####");
	}
	
	
	

	@Override
	public String htmlFromTemplate(String uri,String objVariable, T obj,Object...objects) {
		Context context = new Context();
		context.setVariable(objVariable, obj);		
		return templateEngine.process(uri, context);
	}





	@Override
	public void sendHtmlEmail(MimeMessage mm) {
		LOG.info("#### INICIANDO ENVIO DE EMAIL HTML ####");
		LOG.info("#### ENVIANDO EMAIL ####"+mm.toString());
		javaSender.send(mm);		
		LOG.info("#### EMAIL ENVIADO ####");
		
	}




	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getDefaultSender() {
		return defaultSender;
	}

	public void setDefaultSender(String defaultSender) {
		this.defaultSender = defaultSender;
	}

	public String getDefaultRecipient() {
		return defaultRecipient;
	}

	public void setDefaultRecipient(String defaultRecipient) {
		this.defaultRecipient = defaultRecipient;
	}

}
