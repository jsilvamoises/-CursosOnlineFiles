package com.jsm.email.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.jsm.email.EmailService;
import com.jsm.email.service.impl.MockEmailServiceImpl;

@Component
public class MockEmailBean {
	
	@Bean(name="mockEmailService")
	public EmailService emailService() {
		return new MockEmailServiceImpl();
	}

}
