package com.jsm.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jsm.error.Erro;
import com.jsm.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler({ObjectNotFoundException.class})
	public ResponseEntity<Erro> objectNotFoundExceptionHandler(ObjectNotFoundException ex, HttpServletRequest request){
		Erro erro = new Erro(HttpStatus.NOT_FOUND.value(),ex.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}
	
	
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Erro> constraintViolationExceptionHandler(ConstraintViolationException ex, HttpServletRequest request){
		Erro erro = new Erro(HttpStatus.BAD_REQUEST.value(),ex.getCause()!=null?ex.getCause().getMessage():ex.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
	}

}
