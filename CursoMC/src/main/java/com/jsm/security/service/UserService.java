package com.jsm.security.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jsm.security.UserSS;

public class UserService {

	
	public static UserSS autheticated() {
		try {
			return (UserSS)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
