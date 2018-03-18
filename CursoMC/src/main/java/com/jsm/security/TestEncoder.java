package com.jsm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncoder {

	@Autowired
	private BCryptPasswordEncoder encoder;

	public static void main(String[] args) {
		new TestEncoder().teste();
	}

	private void teste() {
		System.out.println(encoder.encode("123456"));
		
	}
}
