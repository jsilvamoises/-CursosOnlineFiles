package com.jsm.security.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jsm.error.Erro;
import com.jsm.security.service.exception.AuthorizationException;

@ControllerAdvice
public class SecurityHandler {
	@ExceptionHandler({AuthorizationException.class})
	public ResponseEntity<Erro> objectNotFoundExceptionHandler(AuthorizationException ex, HttpServletRequest request){
		Erro erro = new Erro(HttpStatus.FORBIDDEN.value(),ex.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
		
	}
	
}
