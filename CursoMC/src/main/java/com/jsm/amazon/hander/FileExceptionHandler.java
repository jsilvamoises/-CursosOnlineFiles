package com.jsm.amazon.hander;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.jsm.amazon.exception.FileException;
import com.jsm.error.Erro;

@ControllerAdvice
public class FileExceptionHandler {

	@ExceptionHandler({ FileException.class })
	public ResponseEntity<Erro> fileException(FileException ex, HttpServletRequest request) {
		Erro erro = new Erro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Requisição Inválida!", ex.getMessage(), request.getRequestURI());		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

	}

	@ExceptionHandler({ AmazonServiceException.class })
	public ResponseEntity<Erro> amazonServiceException(AmazonServiceException ex, HttpServletRequest request) {
		HttpStatus code = HttpStatus.valueOf(ex.getErrorCode());
		Erro erro = new Erro(System.currentTimeMillis(), code.value(), "Amazon Service Exception!", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(code).body(erro);

	}

	@ExceptionHandler({ AmazonClientException.class })
	public ResponseEntity<Erro> amazonClientException(AmazonClientException ex, HttpServletRequest request) {
		Erro erro = new Erro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Amazon Cliente Exception!", ex.getMessage(), request.getRequestURI());		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

	}


	@ExceptionHandler({ AmazonS3Exception.class })
	public ResponseEntity<Erro> amazonS3Exception(AmazonS3Exception ex, HttpServletRequest request) {
		Erro erro = new Erro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Amazon S3 Exception!", ex.getMessage(), request.getRequestURI());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

	}
}
