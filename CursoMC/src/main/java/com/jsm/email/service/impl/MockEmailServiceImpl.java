package com.jsm.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServiceImpl extends AbstractEmailServiceImpl {

	private static Logger LOG = LoggerFactory.getLogger(MockEmailServiceImpl.class);
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("#### INICIANDO ENVIO DE EMAIL ####");
		LOG.info("#### ENVIANDO EMAIL ####");
		LOG.info("#### EMAIL ENVIADO ####");
		
	}

}
