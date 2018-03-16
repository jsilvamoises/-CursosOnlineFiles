package com.jsm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Value("${spring.datasource.url}")
	private String databaseUrl;
	
	private static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private static final String MESSAGE = ANSI_RED + "RUN MODE: [ >>>>>> O Sistema está em modo de teste <<<<<<< ]" + ANSI_RESET;

	@Bean
	public String infoTest() {
		LOG.warn(MESSAGE);
		LOG.warn(ANSI_GREEN+"DATABASE URL: "+databaseUrl+ANSI_RESET);
		return MESSAGE;
	}
}
