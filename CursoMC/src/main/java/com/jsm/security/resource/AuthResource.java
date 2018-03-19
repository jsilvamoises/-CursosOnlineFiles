package com.jsm.security.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsm.security.UserSS;
import com.jsm.security.dto.EmailDTO;
import com.jsm.security.service.AuthService;
import com.jsm.security.service.UserService;
import com.jsm.security.util.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.autheticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		service.sendNewPassord(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
